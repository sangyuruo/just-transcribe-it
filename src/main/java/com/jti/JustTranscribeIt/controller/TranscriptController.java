package com.jti.JustTranscribeIt.controller;

import com.jti.JustTranscribeIt.dao.TranscriptDao;
import com.jti.JustTranscribeIt.dao.TranscriptExplicitDao;
import com.jti.JustTranscribeIt.service.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transcribe")
public class TranscriptController {

    private AmazonClientService amazonClientService;

    @Autowired
    private TranscriptDao transcriptDao;

    @Autowired
    private TranscriptExplicitDao transcriptExplicitDao;

    @Autowired
    TranscriptController(AmazonClientService amazonClientService) {
        this.amazonClientService = amazonClientService;
    }

    @PostMapping("/new")
    private String newTranscriptPage(@RequestParam(name = "fileUrl") String fileUrl, Model model) {
        // Amazon Client asynchronously records new transcript in DB when transcription job is complete
        amazonClientService.transcribeFile(fileUrl);
        return "index";
    }

    @GetMapping("/new")
    private String newTranscriptPageGet(Model model) {
        return "transcribe";
    }

}