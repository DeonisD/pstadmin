package com.lwo.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author drozdov_d
 */
@WebServlet( name = "DownloadFileServlet", urlPatterns = {"/public/download/*"}, loadOnStartup = 1 )
public class DownloadFileServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ( null == request.getPathInfo() ){
            response.setStatus( 2020 );
                return;
        }

        String catalina         = System.getProperty( "catalina.base", null );
        String relativePath     = null == catalina ? getServletContext().getRealPath("/") : catalina ;
               relativePath     = relativePath + File.separator + "pstdata" + File.separator;
        File folder             = new File( relativePath );
        
        if ( !folder.exists() ){
            response.sendError( 2021 );
                return;
        }
        
        File file = new File( folder, request.getPathInfo()  );
        
        if ( !file.exists() ){
            response.sendError( 2022 );
                return;
        }
        
        OutputStream outStream;
        try (FileInputStream inStream = new FileInputStream(file)) {
            ServletContext context = getServletContext();
            String mimeType        = context.getMimeType( file.getAbsolutePath() );
            if (mimeType == null) mimeType = "application/octet-stream";

                response.setContentType(mimeType);
                response.setContentLength((int) file.length() );
            
            
            String headerKey        = "Content-Disposition";
            String headerValue      = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);
                response.setDateHeader("Last-Modified", file.lastModified() );
            
            outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inStream.read(buffer)) != -1) outStream.write(buffer, 0, bytesRead);
        }
        outStream.close();

    }
}