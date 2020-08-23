package source;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;
    private Statement stmt;
    private String jsp ="/list_search.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/works","root","password");
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation").toString();
		
		FormData fd = new FormData();
		
		if(operation.equals("add")){
			fd.setName(request.getParameter("name").toString());
			fd.setPassword(request.getParameter("password").toString());
			fd.setMarried(request.getParameter("married").toString());
			fd.setQualification(request.getParameter("qualification").toString());
			fd.setDob(request.getParameter("dob").toString());
			
			saveFormData(fd);
			request.setAttribute("status", "New Form Data Successfully Inserted");
			jsp ="/user-form.jsp";
		} else if(operation.equals("update-form")){
			jsp ="/user-update-form.jsp";
		} else if(operation.equals("update")){
			
			fd.setId(request.getParameter("id").toString());
			fd.setName(request.getParameter("name").toString());
			fd.setPassword(request.getParameter("password").toString());
			fd.setMarried(request.getParameter("married").toString());
			fd.setQualification(request.getParameter("qualification").toString());
			fd.setDob(request.getParameter("dob").toString());
			
			updateFormData(fd);
			request.setAttribute("status", "Form Data  With ID "+fd.getId()+" Successfully Updated");
			jsp ="/user-form.jsp";
		}else if(operation.equals("delete")){
			fd.setId(request.getParameter("id").toString());
			deleteFormData(fd);
			request.setAttribute("status", "Form Data With ID "+fd.getId()+" Successfully Deleted");
			jsp ="/list_search.jsp";
		} 
		
		if(operation.equals("search") || operation.equals("delete") ){
			fd.setName(request.getParameter("name") != null ? request.getParameter("name").toString() : "");
			List<FormData> results = searchFormData(fd);
			request.setAttribute("searchformresults", results);
			jsp ="/list_search.jsp";
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
     	dispatcher.forward(request,response);
	}
	
	
	private void saveFormData(FormData fd) {
		try {
			stmt = con.createStatement();
			String sql = "INSERT INTO details (name,password,dob,married,qualification) " +
	                   " VALUES ('"+fd.getName()+"','"+fd.getPassword()+"','"+fd.getDob()+"','"+fd.getMarried()+"','"+fd.getQualification()+"')";
			System.out.println("insert query:  "+sql);
	        stmt.executeUpdate(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateFormData(FormData fd) {
	
		try {
			stmt = con.createStatement();
			String sql = "UPDATE details set name='" +fd.getName()+"', password='"+fd.getPassword()+"', dob='"+
						fd.getDob()+"', married='"+fd.getMarried()+"', qualification='"+fd.getQualification()+"'"
						+ "  where id="+fd.getId();
			System.out.println("update query:  "+sql);
	        stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteFormData(FormData fd) {
		try {
			stmt = con.createStatement();
			String sql = "Delete from details where id="+fd.getId();
			System.out.println("update query:  "+sql);
	        stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<FormData> searchFormData(FormData fd) {
		
		List<FormData> results = new ArrayList();
		try {
			stmt = con.createStatement();
			String sql = "select * from details where name like '%" +fd.getName()+"%'";
			System.out.println("search query:  "+sql);
			ResultSet rs =  stmt.executeQuery(sql);
	        
			while( rs.next()) {
				FormData sfd = new FormData();
				
				sfd.setId(rs.getString(1));
				sfd.setName(rs.getString(2));
				sfd.setPassword(rs.getString(3));
				sfd.setDob(rs.getString(4));
				sfd.setMarried(rs.getString(5));
				sfd.setQualification(rs.getString(6));
				results.add(sfd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
		
	}
}
