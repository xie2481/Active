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

import com.active.entity.Video;
import com.active.util.CurrentPath;

@WebServlet("/UploadVideo")
public class UploadVideoServlet extends HttpServlet {

	private static final long serialVersionUID = 2319709306843952010L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		// 文件上传路径
		String filePath = "/res/video";
		String savePath = CurrentPath.getCurrentPath() + filePath;
		File file = new File(savePath);
		System.out.println(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			// 创建目录
			file.mkdir();
		}
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			// 解析上传数据
			List<FileItem> list = upload.parseRequest(request);
			Video video = new Video();
			for (FileItem item : list) {
				// 如果是普通输入项的数据
				String name = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString("UTF-8");
					if (name.endsWith("v-name")) {
						video.setVname(value);
					}
					if (name.endsWith("v-introduce")) {
						video.setVintroduce(value);
					}
					if (name.endsWith("v-type")) {
						video.setVtype(Integer.parseInt(value));
					}
					if (name.endsWith("chano3")) {
						video.setChano(Integer.parseInt(value));
					}
					if (name.endsWith("tno")) {
						video.setTno(value);
					}
				} else {
					// 如果是上传文件，得到上传的文件名称
					String filename = item.getName();
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					filename = filename
							.substring(filename.lastIndexOf("\\") + 1);
					InputStream in = item.getInputStream();
					FileOutputStream out = null;

					if (name.endsWith("video")) {
						video.setVaddress("res/video/" + filename);
						out = new FileOutputStream(savePath + "\\" + filename);
					}
					if (name.endsWith("img")) {
						File tmp = new File(savePath + "\\img");
						if(!tmp.exists()&&!tmp.isDirectory()) {
							tmp.mkdir();
						}
						video.setVimg("res/video/img/" + filename);
						out = new FileOutputStream(savePath + "\\img\\"
								+ filename);
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
			request.setAttribute("video", video);
			request.getRequestDispatcher("/addVideo")
					.forward(request, response);
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