package supplier.controller;

/**
 * Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
 * Date: 12 January 2023
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import supplier.dao.ProductDAO;
import supplier.dao.SupplierDAO;
import supplier.dao.UserDAO;
import supplier.model.User;

import java.io.IOException;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO dao;	
	private HttpSession session;
	private RequestDispatcher view;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        dao = new UserDAO();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			User user = new User();
			//retrieve and set email and password
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));

			user = UserDAO.login(user);
			//set user session if user is valid
			if(user.isValid()){
				session = request.getSession(true);
				session.setAttribute("sessionId", user.getId());
				session.setAttribute("sessionEmail", user.getEmail());  				//set current session based on email
				session.setAttribute("sessionRole", user.getRole()); 

				if(user.getRole().equalsIgnoreCase("staff")) {
					request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail()));   					
					request.setAttribute("products", ProductDAO.getSupplierProducts());
					view = request.getRequestDispatcher("staff/listAll.jsp"); 			// staff page
					view.forward(request, response);	
				}
				else {
					request.setAttribute("user", UserDAO.getUserByEmail(user.getEmail()));					
					request.setAttribute("supplier", SupplierDAO.getSupplierId(user.getId()));					
					view = request.getRequestDispatcher("supplier/viewProfile.jsp"); 	 // supplier page
					view.forward(request, response);	
				}											
			}
			//redirect to invalidLoggin.jsp if user is not valid
			else{
				response.sendRedirect("invalidLogin.jsp");
			}		
		}

		catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

}
