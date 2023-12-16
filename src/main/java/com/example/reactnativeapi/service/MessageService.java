package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.MessageEntity;
import com.example.reactnativeapi.response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    public ListResponse getMessage() {
        List<MessageEntity> listMessage = new ArrayList<>();


        return new ListResponse(listMessage);
    }
}
