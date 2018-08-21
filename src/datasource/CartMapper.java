package datasource;

import domain.Cart;
import domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CartMapper {
    public static int insert(Cart cart) {
        String sql = "insert into cart(PRODUCT_ID,SALE_COUNT,USER_ID) values(?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs  = null;
        int generatedKey = -1;

        try {
            ps = DBConnection.prepareReturnKeys(sql);
            ps.setInt(1, cart.getProductId());
            ps.setInt(2, cart.getSaleCount());
            ps.setInt(3, cart.getUserId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.release(ps, null, rs);
        }
        return generatedKey;
    }

    public static Map<Cart, Product> GetAllCartInfoByUserID(int userId) {
        String sql = "select count(*)from cart where user_id=" + userId;

        String sqlCart = "SELECT CART_ID cartId,PRODUCT_ID productId,SALE_COUNT saleCount FROM cart WHERE USER_ID=?;";
        String sqlProduct = "SELECT PRODUCT_STATUS productStatus,PRODUCT_ID productId,PRODUCT_NAME productName,PRODUCT_PRICE productPrice,PRODUCT_IMAGE_PATH productImagePath,STORE_NUM storeNum FROM product WHERE PRODUCT_ID=?;";

        return DBHelper.getMapHandler(sqlCart, Cart.class, sqlProduct, Product.class, "PRODUCT_ID",
                userId);
    }

    public static int getCartCount(int userId){
        String sql = "select count(*)from cart where user_id=" + userId;
        return Integer.parseInt(DBHelper.getValue(sql).toString());
    }

    public static void deleteCartById(int cartId) {
        String sql = "DELETE FROM cart WHERE CART_ID=?";
        DBHelper.update(sql, cartId);
    }

    public static void deleteCartByUser(int userId) {
        String sql = "DELETE FROM cart WHERE USER_ID=?";
        DBHelper.update(sql, userId);
    }

    public static Cart getCart(int cartId) {
        String sql = "SELECT CART_ID cartId,PRODUCT_ID productId,SALE_COUNT saleCount,USER_ID userId FROM cart WHERE CART_ID=?";
        return DBHelper.getObject(Cart.class, sql, cartId);
    }

    public static void updateCart(Cart cart) {
        String sql = "UPDATE cart SET USER_ID=?,PRODUCT_ID=?,SALE_COUNT=? WHERE CART_ID=?";
        DBHelper.update(sql, cart.getUserId(), cart.getProductId(), cart.getSaleCount(), cart.getCartId());
    }

    public static Map<Cart, Product> getCartProductMap(int userId, String[] cartIds) {
        String strCartIds = "";
        for (int i = 0; i < cartIds.length - 1; i++) {
            strCartIds += "?,";
        }
        String sqlCart = "SELECT CART_ID cartId,PRODUCT_ID productId,SALE_COUNT saleCount "
                + "FROM cart WHERE CART_ID IN (" + strCartIds + "?) AND USER_ID=?";
        String sqlProduct = "SELECT PRODUCT_STATUS productStatus,PRODUCT_ID productId,PRODUCT_NAME productName,PRODUCT_PRICE "
                + "productPrice,PRODUCT_IMAGE_PATH productImagePath,STORE_NUM storeNum FROM "
                + "product WHERE PRODUCT_ID=?";
        Object[] sqlArray = new Object[cartIds.length + 1];
        for (int i = 0; i < cartIds.length; i++) {
            sqlArray[i] = cartIds[i];
        }
        sqlArray[sqlArray.length - 1] = userId;
        return DBHelper.getMapHandler(sqlCart, Cart.class, sqlProduct, Product.class, "PRODUCT_ID", sqlArray);
    }

    public static void deleteCartByUserCart(int userId, String[] cartIds) {
        String strCartIds = "";
        for (int i = 0; i < cartIds.length - 1; i++) {
            strCartIds += "?,";
        }
        String sql = "DELETE FROM cart WHERE CART_ID IN (" + strCartIds + "?) AND USER_ID=?";
        Object[] sqlArray = new Object[cartIds.length + 1];
        for (int i = 0; i < cartIds.length; i++) {
            sqlArray[i] = cartIds[i];
        }
        sqlArray[sqlArray.length - 1] = userId;
        DBHelper.update(sql, sqlArray);
    }

    public static List<Cart> getCartsByOrderNum(String orderNum) {
        String sql = "SELECT PRODUCT_ID productId,SALE_COUNT saleCount FROM orders WHERE ORDER_NUM=?";
        return DBHelper.getObjectForList(Cart.class, sql, orderNum);
    }
}
