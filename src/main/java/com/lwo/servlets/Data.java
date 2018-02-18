package com.lwo.servlets;

import com.google.gson.Gson;
import com.lwo.entity.FileStatus;
import com.lwo.entity.RemoteFile;
import com.lwo.entity.Request;
import com.lwo.entity.Response;
import com.lwo.entity.UpdateStatus;
import com.lwo.entity.terminal.TerminalService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author drozdov_d
 */
@WebServlet( name = "DownloadFileServlet", urlPatterns = {"/public/pst/*"}, loadOnStartup = 1 )
public class Data extends HttpServlet {

    @Inject TerminalService terminal_service;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collect = request.getReader().lines().collect(Collectors.joining());
        
        try {
            
            System.out.println("terminal service -> " + (terminal_service == null ) );
            
            Request termimalRequest = ((Request)new Gson().fromJson(collect, Request.class));
            
            terminal_service.updateTerminalInfo( termimalRequest.getTerminal() );
            
            returnUpdateData( response );
            

        } catch ( Exception e ){
            response.getWriter().write( new Gson().toJson( new Response("999", e.getMessage()) ) );
        }    
        
    }
    
    private void returnUpdateData( HttpServletResponse response ) throws IOException{
        String catalina    = System.getProperty( "catalina.base", null );
               catalina    = null == catalina ? getServletContext().getRealPath("/") : catalina ;
        final String       relativePath     = catalina + File.separator + "pstdata";
        final List<RemoteFile> files        = new ArrayList<>();
        
        Files.walk(Paths.get( relativePath )).filter(Files::isRegularFile).forEach((path)->{
            String filePath = path.getParent().toString().substring( relativePath.length() );
            if ( filePath.startsWith( File.separator )) filePath = filePath.substring(1);
                files.add( new RemoteFile( filePath, path.getFileName().toString(), path.toFile().lastModified() ) );
        });

            FileStatus status = new FileStatus();
                       status.setFiles(files);
                   
            response.setContentType("application/json");
            response.getWriter().write( new Gson().toJson( new UpdateStatus(files) ) );
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write( new Gson().toJson( new Response("999", "only post") ) );
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
}
