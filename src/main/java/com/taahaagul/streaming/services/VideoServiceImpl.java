package com.taahaagul.streaming.services;

import com.taahaagul.streaming.entities.Video;
import com.taahaagul.streaming.exceptions.VideoAlreadyExistsException;
import com.taahaagul.streaming.exceptions.VideoNotFoundException;
import com.taahaagul.streaming.repos.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private VideoRepository videoRepository;

    @Override
    public Video getVideo(String name) {
        if(!videoRepository.existsByName(name))
            throw new VideoNotFoundException();

        return videoRepository.findByName(name);
    }

    @Override
    public void saveVideo(MultipartFile file, String name) throws IOException {
        if(videoRepository.existsByName(name)) {
            throw new VideoAlreadyExistsException();
        }

        Video newVideo = new Video(name, file.getBytes());
        videoRepository.save(newVideo);
    }

    @Override
    public List<String> getAllVideoNames() {
        return videoRepository.getAllEntryNames();
    }
}
