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
import supplier.model.Product;

import java.io.IOException;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher view;
	private static ProductDAO dao;
	private int pid;
	private String action="", forward="";
	private static String LIST = "staff/listProduct.jsp";
	private static String VIEW = "staff/viewProduct.jsp";	
	private static String UPDATE = "staff/updateProduct.jsp";
	private static String ADD = "staff/addProduct.jsp";	
	private static String LISTALL = "staff/listAll.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        dao = new ProductDAO();     
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		action = request.getParameter("action");
        
		if(action.equalsIgnoreCase("listProduct")) {
			forward = LIST;
	        request.setAttribute("products", ProductDAO.getAllProduct());       
		}		
		if(action.equalsIgnoreCase("listAll")) {
			forward = LISTALL;
			request.setAttribute("products", ProductDAO.getSupplierProducts());
		}
		if(action.equalsIgnoreCase("viewProduct")) { 
			forward = VIEW;
			pid = Integer.parseInt(request.getParameter("pid"));  
	        request.setAttribute("product", ProductDAO.getProductById(pid));
		}
		if(action.equalsIgnoreCase("updateProduct")) {
			forward = UPDATE;
			Product product = new Product();			
			pid = Integer.parseInt(request.getParameter("pid"));    	        
	        product = ProductDAO.getProductById(pid);	        	       
	        request.setAttribute("selectedSupplier", product.getSid()); 	      	       	                
	        request.setAttribute("product", product); //ProductDAO.getProductById(pid)
			request.setAttribute("suppliers", SupplierDAO.getAllSupplier());	        
		}
		if(action.equalsIgnoreCase("deleteProduct")) {
			forward = LIST;
			pid = Integer.parseInt(request.getParameter("pid"));  
			dao.deleteProduct(pid);
			request.setAttribute("products", ProductDAO.getAllProduct());    
		}		
		if(action.equalsIgnoreCase("addProduct")) {
			forward = ADD;			
			request.setAttribute("sups", SupplierDAO.getAllSupplier());	   
		}
         
        view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product product = new Product();
		product.setProductname(request.getParameter("productname"));
		product.setPrice(Double.parseDouble(request.getParameter("price")));
		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		product.setSid(Integer.parseInt(request.getParameter("sid")));
		    
		String pid = request.getParameter("pid");
	    
	    if(pid ==null || pid.isEmpty()) {
	    	dao.add(product);
		}
		else {
			product.setPid(Integer.parseInt(pid));
			dao.updateProduct(product);
		}
	    
	    request.setAttribute("products", ProductDAO.getAllProduct());
	    view = request.getRequestDispatcher("staff/listProduct.jsp");
        view.forward(request, response);
	}

}
