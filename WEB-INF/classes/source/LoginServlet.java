package source;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;
    private Statement stmt;
    private String jsp ="/user-form.jsp";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		
		if( username == null && password == null   || 
				username.length()==0 && password.length()==0 ) {
			
			 request.setAttribute("status", "Username or Password is NOT Valid");
			   
			 jsp="/login.jsp";
			    
		} else {
			ResultSet rs = null;
			
				try {
					stmt = con.createStatement();
					String sql = "select * from details where name='" +username+"' and password='"+password+"'";
					System.out.println("login query:  "+sql);
					rs =  stmt.executeQuery(sql);
			        
					boolean status = rs.next();  
					
					if ( status ) {
						request.setAttribute("status", "UserName:  "+username+"     Login is Successful ! ");
						jsp ="/list_search.jsp";
					} else {
						 request.setAttribute("status", "Username or Password is NOT Valid");
						 jsp="/login.jsp";
					}
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
        dispatcher.forward(request,response);

	}
}
