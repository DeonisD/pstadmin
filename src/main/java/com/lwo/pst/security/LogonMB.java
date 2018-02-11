package com.lwo.pst.security;

import com.github.adminfaces.template.session.AdminSession;
import static com.lwo.pst.util.Utils.addDetailMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import javax.naming.CommunicationException;




/**
 *
 * @author drozdov_d
 */
@Named
@SessionScoped
@Specializes
public class LogonMB extends AdminSession implements Serializable {
    
    private String  user;
    private String  password;
    private boolean remember;

    public void login() throws IOException {
        UsernamePasswordToken token = new UsernamePasswordToken(user, password);
                              token.setRememberMe(remember);

        Subject currentUser         = SecurityUtils.getSubject();

        //log.log(Level.INFO, "Submitting login with username of {0} and password of {1}", new Object[]{ user, password});
        try {
            currentUser.login(token);
            addDetailMessage("Logged in successfully as <b>" + user + "</b>");
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Faces.redirect("index.xhtml");
       
        } catch ( UnknownAccountException uae ) {
            sendErrorMessage( "Неизвестный пользователь" );
        } catch ( IncorrectCredentialsException ice ) {
            sendErrorMessage( "Неправильный пароль" );
        } catch ( LockedAccountException lae ) {
            sendErrorMessage( "Пользователь заблокирован" );
        } catch (AuthenticationException e) {
            if ( e.getCause() instanceof CommunicationException ){
                sendErrorMessage( "Неизвестный хост : " + e.getCause().getMessage() );
            } else {
                sendErrorMessage( "Неправильные имя пользователя или пароль" );
            }
        } catch (Exception e) {
            Faces.getExternalContext().getFlash().setKeepMessages(true);
            Messages.addFatal(null,"Login Failed:" + e.getMessage());
        }
    }

    private void sendErrorMessage( String message ){
        Faces.getExternalContext().getFlash().setKeepMessages(true);
        Messages.addFatal(null,"Ошибка :" + message );
    }
    
    
    @Override
    public boolean isLoggedIn() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getCurrentUser() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }
    
}
