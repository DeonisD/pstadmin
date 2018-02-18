package com.lwo.pst.bean;

import com.lwo.entity.TerminalInfo;
import com.lwo.entity.terminal.TerminalService;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author drozdov_d
 */
@Named
@RequestScoped
public class TerminalsMB implements Serializable {
    
    @Inject TerminalService terminal_service;
    private List<TerminalInfo> terminals;

    public TerminalsMB() {
    }
    
    @PostConstruct
    public void onInit(){
        this.terminals = terminal_service.getListTerminals();
        System.out.println("" + this.terminals.size() );
                
    }

    public List<TerminalInfo> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<TerminalInfo> terminals) {
        this.terminals = terminals;
    }
}
