package com.active.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.active.util.CurrentPath;

import jxl.Workbook;
import jxl.read.biff.BiffException;




/**
 * Servlet implementation class UploadStudentInfo
 */
@WebServlet("/uploadStudentInfo")
public class UploadStudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadStudentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String path = "/res/temp";
		String savePath = CurrentPath.getCurrentPath() + path;
		File file = new File(savePath);
		if(!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if(!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			List<FileItem> list;
			try {
				list = upload.parseRequest(request);
				for(FileItem item : list) {
					InputStream in = item.getInputStream();
					try {
						Workbook workbook = Workbook.getWorkbook(in);
						request.setAttribute("workbook", workbook);
						break;
					} catch (BiffException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.getRequestDispatcher("/addStudentInfo").forward(request, response);
			return;
	}
	
	/**
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		// TODO Auto-generated method stub
		try {
			doGet(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
