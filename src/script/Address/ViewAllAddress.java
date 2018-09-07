package script.Address;

import domain.Address;
import domain.Pager;
import service.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/viewalladdress")
public class ViewAllAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AddressService addressService = null;

    public ViewAllAddress() {
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
        String currPage = request.getParameter("currPage");
        if (currPage == null) {
            currPage = "1";
        }
        Pager<Address> pager = addressService.viewAllAddress(Integer.parseInt(currPage), userId);
        request.setAttribute("pager", pager);
        request.getRequestDispatcher("addressManage.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.doGet(request, response);
	}

}
