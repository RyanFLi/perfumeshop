package script.order;

import datasource.AddressMapper;
import datasource.CartMapper;
import datasource.OrderMapper;
import domain.Address;
import domain.Cart;
import domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/deleteorder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DeleteOrder() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * delete order from order history
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderNum = request.getParameter("orderNum").toString();
		OrderMapper.changeOrderVisible(orderNum, 0);
		request.getRequestDispatcher("viewmyorder").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.doGet(request, response);
	}

}
