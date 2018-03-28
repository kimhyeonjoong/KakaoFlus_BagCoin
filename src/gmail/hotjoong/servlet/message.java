package gmail.hotjoong.servlet;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@WebServlet("/message")
public class message extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null){ 
			jb.append(line);
		}
		 
		String jsonString = jb.toString();
		String text ="";
		Object obj = JSONValue.parse(jsonString);
		JSONObject json = (JSONObject)obj;
		if(json != null){
			text = json.get("content").toString();
		}
		text = "카카오계정 로그인\n" + 
				"\n" + 
				"- 일시 : 3/25 (Sun) 16:24 (한국 표준시/UTC+9)\n" + 
				"- 기기 : Windows 7 / IE\n" + 
				"- 앱/서비스 : web\n" + 
				"- IP : 211.200.104.71\n" + 
				"\n" + 
				"카카오계정이 새로운 환경에서 로그인하였습니다.\n" + 
				"직접 로그인 한 것이 아니면 지금 비밀번호를 변경해주세요.";
		
		getData(text);
       
		RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp"); 
		dispatcher.forward(request, response);
		
	}
	
	public StringBuffer getData(String text) {
		StringBuffer Data = new StringBuffer();
		Data.append(text.substring(text.indexOf("일시")+5,text.indexOf("(")-1));
		Data.append(text.substring(text.indexOf(")")+1,text.indexOf("(한")-1));
		Data.append(",");
		Data.append(text.substring(text.indexOf("IP")+5,text.indexOf("카카오계정이")-2));
		return Data;
	}
	
}
