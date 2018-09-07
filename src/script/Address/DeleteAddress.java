package script.Address;

import service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/deleteaddress")
public class DeleteAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AddressService addressService = null;

    public DeleteAddress() {
        super();
        addressService = AddressService.getInstance();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = -1;
        try {
            userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        } catch (Exception e) {}
        if(userId == -1){
            response.sendRedirect("login.jsp?responseMsg=userIsNotLogin");
            return;
        }

        try {
            int addId = Integer.parseInt(request.getParameter("addId").toString());
            addressService.deleteAddress(addId, userId);
            request.getRequestDispatcher("viewalladdress").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.doGet(request, response);
	}

}
