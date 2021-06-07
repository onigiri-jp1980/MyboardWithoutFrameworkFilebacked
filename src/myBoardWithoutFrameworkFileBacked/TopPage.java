package myBoardWithoutFrameworkFileBacked;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class topPage
 */
@WebServlet({ "/","/top" })
public class TopPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ServletContext application = this.getServletContext();
		MyBoardWithoutFrameworkFileBackedDAO mbDAO = new MyBoardWithoutFrameworkFileBackedDAO("" + application.getRealPath("/WEB-INF/data"));

		List<MyBoardWithoutFrameworkFileBacked> Post = mbDAO.selectAllPosts();

		request.setAttribute("list", Post);

		RequestDispatcher rDist = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		rDist.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
