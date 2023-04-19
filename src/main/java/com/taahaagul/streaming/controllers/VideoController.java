package com.taahaagul.streaming.controllers;

import com.taahaagul.streaming.services.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/video")
@AllArgsConstructor
public class VideoController {
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<String> saveVideo(@RequestParam("file") MultipartFile file,
                                            @RequestParam("name") String name) throws IOException {
        videoService.saveVideo(file, name);
        return ResponseEntity.ok("Video saved successfully");
    }

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getVideoByName(@PathVariable("name") String name){
        byte[] data = videoService.getVideo(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(data.length);
        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllVideoNames() {
        return ResponseEntity.ok(videoService.getAllVideoNames());
    }
}
