package com.jd.pokerSessions.commands;

import org.springframework.web.multipart.MultipartFile;

public class SessionUploadForm {

    private String room;
    private MultipartFile sessionFile;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public MultipartFile getSessionFile() {
        return sessionFile;
    }

    public void setSessionFile(MultipartFile sessionFile) {
        this.sessionFile = sessionFile;
    }
}
