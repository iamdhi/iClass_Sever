package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuaryTeacherID
 */
@WebServlet("/QuaryTeacherID")
public class QuaryTeacherID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuaryTeacherID() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subject_id = request.getParameter("subject_id");
		System.out.println("课程id：	" + subject_id);
		PrintWriter out = response.getWriter();
		String quary_sql = "select teacher_id from subject where subject_id='" + subject_id + "'";
		try {
			// 连接数据库
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iclass?useSSL=false",
					"root", "407031");
			Statement statement = conn.createStatement(); // 创建Statement对象
			// 执行SQL语句，获取结果
			ResultSet resultset = statement.executeQuery(quary_sql);
			String teacher_id = "";
			if (resultset.next()) {
				teacher_id = resultset.getString("teacher_id");
			}
			// 输出结果
			System.out.println("teacher_id:" + teacher_id);
			out.println(URLEncoder.encode(String.valueOf(teacher_id), "UTF-8"));
			// 关闭连接
			resultset.close();
			conn.close();
			statement.close();
		} catch (SQLException se) {
			System.out.println("quary failed");
			out.println("quary failed");
			System.out.println("SQLException: " + se.getMessage());
		}
	}
}