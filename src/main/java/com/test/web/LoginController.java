package com.test.web;

import com.test.bean.User;
import com.test.server.IUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller  
//@RequestMapping("/loginController")
public class LoginController  
{  
	@Resource(name="server")
    IUserServer server;  
    
    @RequestMapping("/init/login.json")  
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception  
    {  
        // 创建ModelAndView对象，login为返回的jsp页面的名称，全路径是根据在springMVC配置文件中配置的前缀与后缀拼接而成  
        ModelAndView mode = new ModelAndView("login");  
        //User user = server.testMethod("aa");  
        // 将对象加入mode返回到前台页面  
       // mode.addObject("user", user);  
        return mode;  
    } 
  
    // 根据访问连接调用控制器，此控制器的调用连接为localhost:8080/SpringMVC-Mybatis-Memcached/loginController/login  
    @RequestMapping("/login.json")  
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 创建ModelAndView对象，login为返回的jsp页面的名称，全路径是根据在springMVC配置文件中配置的前缀与后缀拼接而成  
        ModelAndView mode = new ModelAndView("login");  
       // User user = server.testMethod("aa");
        User user=server.queryUser();
        // 将对象加入mode返回到前台页面  
        mode.addObject("user", user);  
        return mode;  
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        server.upUser("aa","aaa");
       // return mode;
    }

    @RequestMapping("/removeCache.json")
    public void removeCache(){
    	server.clearAllCache();
    	System.out.println("清除缓存成功");
    }

    @RequestMapping("/getAll.json")
    public void getAll(){
        server.cacheList();
    }

    @RequestMapping("/flushAll.json")
    public void flushAll(){
        server.fluahAll();
       // server.flu();
    }

    @RequestMapping("/testTrans.json")
    public void testTrans(){
        server.testTrans(1,2,100);
    }
  
    public IUserServer getServer()  
    {  
        return server;  
    } // 依赖注入，根据属性名自动注入  
    @Autowired  
    public void setServer(IUserServer server)  
    {  
        this.server = server;  
    }  
}  