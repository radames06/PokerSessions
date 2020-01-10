package com.jd.pokerSessions.controllers;

import com.jd.pokerSessions.boostrap.Utils;
import com.jd.pokerSessions.commands.SessionCommand;
import com.jd.pokerSessions.commands.SessionUploadForm;
import com.jd.pokerSessions.model.Hand;
import com.jd.pokerSessions.model.Session;
import com.jd.pokerSessions.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Set;

@Controller
public class SessionController {


    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    @RequestMapping({"/", "index"})
    public String getSessionPage(Model model) {
        model.addAttribute("sessions", sessionService.findAll());
        return "sessions";
    }

    @GetMapping
    @RequestMapping("/session/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("pokersession", sessionService.findById(Long.valueOf(id)));
        return "hands";
    }

    @GetMapping
    @RequestMapping("/session/new")
    public String newSession(Model model) {
        model.addAttribute("sessionUploadForm", new SessionUploadForm());

        return "sessionform";
    }

    @PostMapping
    @RequestMapping("/session/new/upload")
    public String uploadSessionFile(HttpServletRequest request, Model model, @ModelAttribute("sessionUploadForm")SessionUploadForm sessionUploadForm) {
        return this.doUpload(request, model, sessionUploadForm);
    }

    private String doUpload(HttpServletRequest request, Model model, //
                            SessionUploadForm sessionUploadForm) {

        String room = sessionUploadForm.getRoom();
        System.out.println("Room: " + room);

        // Root Directory.
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        // Create directory if it not exists.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        MultipartFile fileData = sessionUploadForm.getSessionFile();
        //
        File uploadedFile;
        String failedFile;

        //for (MultipartFile fileData : fileDatas) {

            // Client File Name
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    // Create the file at server
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    //
                    uploadedFile = serverFile;
                    System.out.println("Write file: " + serverFile);
                    model.addAttribute("uploadedFile", uploadedFile);

                    Session session1 = new Session();
                    session1.setOwner("Julien");
                    session1.setRoom(room);
                    session1.setDate(LocalDate.now());
                    Set<Hand> session1Hands = Utils.tmpReaderCsv(uploadRootDir.getAbsolutePath() + File.separator + name);
                    session1Hands.forEach((hand -> hand.setSession(session1)));
                    session1.setSessionHands(session1Hands);
                    sessionService.save(session1);

                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                    System.out.println(e.getMessage());
                    failedFile=name;
                    model.addAttribute("failedFile", failedFile);
                }
            }
        //}

        // TODO : Finir l'upload
        // TODO : Prendre en compte le gain de la main et de la session
        // TODO : Sauvegarde en BDD
        // TODO : Stats de base sur l'ensemble des sessions, gagnantes vs perdantes (graphes points)
        // TODO : Supprimer le fichier après chargement
        model.addAttribute("room", room);
        return getSessionPage(model);
    }
}
