package com.taahaagul.streaming.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The video is not exists")
public class VideoNotFoundException extends RuntimeException{
}
