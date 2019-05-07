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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.active.entity.Document;
import com.active.util.CurrentPath;

@WebServlet("/UploadDoc")
public class UploadDocServlet extends HttpServlet {

	private static final long serialVersionUID = 2319709306843952010L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		// �ļ��ϴ�·��
		String filePath = "/res/file";
		String savePath = CurrentPath.getCurrentPath() + filePath;
		File file = new File(savePath);
		System.out.println(savePath);
		// �ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
		if (!file.exists() && !file.isDirectory()) {
			// ����Ŀ¼
			file.mkdir();
		}
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			// �����ϴ�����
			List<FileItem> list = upload.parseRequest(request);
			Document document = new Document();
			for (FileItem item : list) {
				// �������ͨ�����������
				String name = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString("UTF-8");
					if (name.endsWith("doc-name")) {
						document.setDocName(value);
					}
					if (name.endsWith("doc-type")) {
						document.setDocType(value);
					}
					if (name.endsWith("tno")) {
						document.setTno(value);
					}
					if (name.endsWith("chano3")) {
						document.setChano(Integer.parseInt(value));
					}
				} else {
					// ������ϴ��ļ����õ��ϴ����ļ�����
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					InputStream in = item.getInputStream();
					FileOutputStream out = null;

					if (name.endsWith("document")) {
						document.setDocPath("res/file/" + filename);
						out = new FileOutputStream(savePath + "\\" + filename);
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
			request.setAttribute("doc", document);
			request.getRequestDispatcher("/addDoc").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}