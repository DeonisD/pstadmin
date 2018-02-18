package com.lwo.entity;

/**
 *
 * @author drozdov_d
 */
public class Request {
    private final TerminalInfo terminal;

    public Request() {
        this.terminal = new TerminalInfo();
    }

    public TerminalInfo getTerminal() {
        return terminal;
    }
}
