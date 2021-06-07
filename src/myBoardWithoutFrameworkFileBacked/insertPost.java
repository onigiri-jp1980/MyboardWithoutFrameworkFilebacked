package myBoardWithoutFrameworkFileBacked;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertPost
 */
@WebServlet("/insert")
public class insertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath()+"/top");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		MyBoardWithoutFrameworkFileBacked mb = new MyBoardWithoutFrameworkFileBacked();
		mb.setName(request.getParameter("name"));
		mb.setMsg(request.getParameter("msg"));
		mb.setEmail(request.getParameter("email"));
		ServletContext application = this.getServletContext();
		MyBoardWithoutFrameworkFileBackedDAO mbDAO = new MyBoardWithoutFrameworkFileBackedDAO("" + application.getRealPath("/WEB-INF/data"));
		mbDAO.insertPost(mb);

		response.sendRedirect(request.getContextPath()+"/");
	}

}
