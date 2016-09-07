package com.servlettest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.imageio.ReadColorTest;

/**
 * Servlet implementation class Servlet3Demo
 */
@WebServlet(name="Servlet3Demo", urlPatterns={"/servlet3demo","/servlet3demo2"})
public class Servlet3Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Servlet3Demo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if(servletPath.equals("/servlet3demo")){
			getColors(request, response);
		}
	}
	
	private void getColors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReadColorTest rc = new ReadColorTest();
		HashMap<String, Object> colors = null;
		try {
			colors = (HashMap<String, Object>)rc.getImagePixel("E:\\5799b3176a219.png");
		} catch (Exception e) {
			colors = new HashMap<String, Object>();
		}
		colors.put("status", "ok");
		JSONObject jsonObject = JSONObject.fromObject(colors);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(jsonObject.toString());
	}
}
