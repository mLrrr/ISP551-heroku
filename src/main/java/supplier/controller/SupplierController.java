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
import supplier.model.Product;
import supplier.model.Supplier;
import supplier.model.User;

import java.io.IOException;

/**
 * Servlet implementation class SupplierController
 */
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher view;
	private static SupplierDAO dao;  
	private static UserDAO udao;
	private int sid;
	private String action="", forward="";
	private static String LIST = "staff/listSupplier.jsp";
	private static String UPDATE = "staff/updateSupplier.jsp";
	private static String VIEW = "staff/viewSupplier.jsp";	
	private static String VIEW_PROFILE = "supplier/viewProfile.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupplierController() {
		super();
        dao = new SupplierDAO();
        udao = new UserDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
        
		if(action.equalsIgnoreCase("listSupplier")) {
			forward = LIST;
	        request.setAttribute("users", UserDAO.getUserSupplier());        
		}		
		if(action.equalsIgnoreCase("viewSupplier")) {    
			forward = VIEW;
			sid = Integer.parseInt(request.getParameter("sid"));
			request.setAttribute("supplier", SupplierDAO.getSupplierById(sid));
		}		
		if(action.equalsIgnoreCase("updateSupplier")) { 
			forward = UPDATE;
			sid = Integer.parseInt(request.getParameter("sid"));
	        request.setAttribute("supplier", SupplierDAO.getSupplierById(sid));	        
		}
		if(action.equalsIgnoreCase("deleteSupplier")) {
			forward = LIST;
	        int id = Integer.parseInt(request.getParameter("id"));
		    udao.deleteUser(id);		    
		    request.setAttribute("users", UserDAO.getUserSupplier());        
		}
		if(action.equalsIgnoreCase("viewProfile")) {
			forward = VIEW_PROFILE;
			int id = Integer.parseInt(request.getParameter("id"));
	        request.setAttribute("supplier", SupplierDAO.getSupplierId(id));      
		}
         
        view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier();
		supplier.setSid(Integer.parseInt(request.getParameter("sid")));
		supplier.setName(request.getParameter("name"));
		supplier.setAddress(request.getParameter("address"));
		supplier.setCity(request.getParameter("city"));
		supplier.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		supplier.setState(request.getParameter("state"));
		supplier.setCountry(request.getParameter("country"));		
		supplier.setPhoneno(request.getParameter("phoneno"));		

	    dao.updateSupplier(supplier);
	   
	    request.setAttribute("users", UserDAO.getUserSupplier());
	    view = request.getRequestDispatcher("staff/listSupplier.jsp");
        view.forward(request, response);
	}
}