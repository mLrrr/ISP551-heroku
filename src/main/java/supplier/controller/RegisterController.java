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
import supplier.dao.ProductDAO;
import supplier.dao.UserDAO;
import supplier.model.User;

import java.io.IOException;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO dao;   
	private RequestDispatcher view;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		dao = new UserDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		view = request.getRequestDispatcher("staff/register.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		//retrieve input and set
		user.setEmail(request.getParameter("email"));		
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));

		user = UserDAO.getUser(user);
		
		//check if user exists
		if(!user.isValid()){
			try {
				//if user not exist, add/register the user
				dao.add(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//redirect to listAll.jsp
			request.setAttribute("products", ProductDAO.getSupplierProducts());
			RequestDispatcher view = request.getRequestDispatcher("staff/listAll.jsp"); // staff page
			view.forward(request, response);
		}        
	}
}
