package edu.poly.admin.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Response;
import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDao;
import edu.poly.model.Video;

/**
 * Servlet implementation class VideoManagementServlet
 */
@WebServlet({"/admin/VideosManagement", "/admin/VideosManagement/create","/admin/VideosManagement/update",
	            "/admin/VideosManagement/delete",
				"/admin/VideosManagement/reset",
				"/admin/VideosManagement/edit",})
@MultipartConfig 
public class VideoManagementServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url = request.getRequestURL().toString();
	if (url.contains("edit")) {
		edit(request, response);
		return ;
	}
	if (url.contains("delete")) {
		delete(request, response);
		return ;
	}
	if (url.contains("reset")) {
		reset(request, response);
		return ;
	}
	Video video = new Video();
	video.setPoster("images/desktop.jpg");
	
	findAll(request, response);
	request.setAttribute("video", video);
		
		
     PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		
		try {
			BeanUtils.populate(video, request.getParameterMap());
			
			VideoDao dao = new VideoDao();
			Video oldVideo = dao.findById(video.getVideoId());
			
			
			if(request.getPart("cover").getSize()==0){
				video.setPoster(oldVideo.getPoster());
				
			}else {
				video.setPoster("uploads/"+UploadUtils.processUploadField("cover", request, 
						"/uploads",video.getVideoId()));
				
			}
			
			dao.update(video);
			
			request.setAttribute("video", video);
			request.setAttribute("message", "Video is updated!");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error" + e.getMessage());
		}
		
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
		
	}
	//reset
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = 	new Video();
		video.setPoster("images/desktop.jpg");
		request.setAttribute("video", video);
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
		
	}
	//delete
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");
		
		if (id == null) {
			request.setAttribute("error", "Video id is required!");
			PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			return ;
		}
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			if (video == null ) {
				request.setAttribute("error", "Video is not found!");
				PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);
			request.setAttribute("message", "video is deleted!");
			request.setAttribute("video", new Video());
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	//fill lai du lieu
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
			
			VideoDao dao = new VideoDao();
			
			List<Video> list = dao.findAll();	
			
			request.setAttribute("videos", list);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error" + e.getMessage());
		}
		
		
	}
	//chinh sua
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("videoId");
		
		if (id == null ) {
			request.setAttribute("error", "Video id is required!");
			 PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
			 return;
		}
		try {
			VideoDao dao = new VideoDao();
			Video video = dao.findById(id);
			
			request.setAttribute("video", video);
			findAll(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Error" + e.getMessage());
		}
		 PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}	
	//taomoi
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			video.setPoster("uploads/" + UploadUtils.processUploadField("cover", request, "/uploads",video.getVideoId()));
			
			VideoDao dao = new VideoDao();
			dao.insert(video);
			
			request.setAttribute("video", video);
			request.setAttribute("message", "Video is inserted!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error","error: "+ e.getMessage());
		}
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
		
	}
	


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		
		if (url.contains("create")) {
			create(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("update")) {
			update(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
	}

	
	
	

}





