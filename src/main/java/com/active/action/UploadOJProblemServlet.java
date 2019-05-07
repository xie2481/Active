package com.active.action;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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


import com.active.entity.OJProblem;
import com.active.util.CurrentPath;

/**
 * Servlet implementation class UploadOJProblemServlet
 */
@WebServlet("/uploadOJ")
public class UploadOJProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadOJProblemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String path = "/res/Problem";
		String savePath = CurrentPath.getCurrentPath() + path;
		File file = new File(savePath);
		if(!file.exists() && !file.isDirectory()) {
			if(!file.mkdir()) {
				System.out.println("创建失败");
			}
		}
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if(!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			List<FileItem> list = upload.parseRequest(request);
			OJProblem oj = new OJProblem();
			for(FileItem item : list) {
				String name = item.getFieldName();
				//普通输入项
				if(item.isFormField()) {
					String  value = item.getString("UTF-8");
					if(name.equals("title")) {
						oj.setTitle(value);
						File newFile = new File(savePath + "\\" + value);
						if(!newFile.exists() && !newFile.isDirectory()) {
							newFile.mkdir();
						}
					}
					if(name.equals("difficult")) {
						oj.setDifficulty(Integer.parseInt(value));
					}
					if(name.equals("chano3")) {
						oj.setChaNo(Integer.parseInt(value));
					}
					if(name.equals("knowledge")) {
						oj.setKnowledgeId(Integer.parseInt(value));
					}
				}
				else {
					String filename = item.getName();//得到文件的名字
					if(filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename.substring(filename.indexOf("\\") + 1);
					InputStream in = item.getInputStream();
					FileOutputStream out = null;
					if(name.equals("mdfile")) {
						File tmp = new File(savePath + "\\" + oj.getTitle());
						if(!tmp.exists() && !tmp.isDirectory()) {
							tmp.mkdir();
						}
						out = new FileOutputStream(savePath + "\\" + oj.getTitle() + "\\" + oj.getTitle() + ".md");
					}
					if(name.equals("inputfile")) {
						File tmp = new File(savePath + "\\" + oj.getTitle());
						if(!tmp.exists() && !tmp.isDirectory()) {
							tmp.mkdir();
						}
						out = new FileOutputStream(savePath + "\\" + oj.getTitle() +"\\in.txt");
					}
					if(name.equals("answerfile")) {
						out = new FileOutputStream(savePath + "\\" + oj.getTitle() + "\\out.txt");
					}
					if(name.equals("mainjava") || name.equals("interfacejava")) {
						File tmp = new File(savePath + "\\" + oj.getTitle() + "\\java");
						if(!tmp.exists() && !tmp.isDirectory()) {
							tmp.mkdir();
						}
						out = new FileOutputStream(savePath + "\\" + oj.getTitle() + "\\java\\" + filename);
					}
					byte buffer[] = new byte[1024];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
				}
			}
			request.setAttribute("oj", oj);
			request.getRequestDispatcher("/addOJ").forward(request, response);
			return;
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
