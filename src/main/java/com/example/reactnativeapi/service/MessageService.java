package com.example.reactnativeapi.service;

import com.example.reactnativeapi.entity.MessageEntity;
import com.example.reactnativeapi.exception.BadRequestException;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.request.CreateMessageRequest;
import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    public ListResponse getAllMessage() {
        List<MessageEntity> listMessage = createListMessage();

        return new ListResponse(listMessage);
    }

    public MessageEntity getOneMessage(String idFromPathVariable) {
        int id = MessageUtil.checkRequest(idFromPathVariable);
        MessageEntity response = new MessageEntity();
        List<MessageEntity> listMessage = createListMessage();

        if (id <= 0) {
            throw new BadRequestException("Id must be greater than 0");
        }
        if (id > listMessage.size()) {
            throw new NotFoundException("Can't not found message with " + id);
        }

        for (MessageEntity loopMessage : listMessage) {
            if (loopMessage.getId().equals(id)) {
                response = loopMessage;
            }
        }

        return response;
    }

    public ListResponse createMessage(CreateMessageRequest createMessageRequest) {
        List<MessageEntity> listMessage = createListMessage();

        listMessage.add(new MessageEntity(
                6,
                createMessageRequest.getMessage(),
                createMessageRequest.getLocation(),
                createMessageRequest.getLastUpdate()
        ));

        return new ListResponse(listMessage);
    }

    public ListResponse updateMessage(MessageEntity messageEntity) {
        List<MessageEntity> listMessage = createListMessage();

        if (messageEntity.getId() <= 0) {
            throw new BadRequestException("Id must be greater than 0");
        }
        if (messageEntity.getId() > listMessage.size()) {
            throw new NotFoundException("Can't not found message with " + messageEntity.getId());
        }

        for (MessageEntity message : listMessage) {
            if (message.getId().equals(messageEntity.getId())) {
                message.setMessage(messageEntity.getMessage());
                message.setLocation(messageEntity.getLocation());
                message.setLastUpdate(messageEntity.getLastUpdate());
            }
        }

        return new ListResponse(listMessage);
    }

    public ListResponse deleteMessage(String idFromPathVariable) {
        int id = MessageUtil.checkRequest(idFromPathVariable);
        List<MessageEntity> listMessage = createListMessage();

        if (id <= 0) {
            throw new BadRequestException("Id must be greater than 0");
        }
        if (id > listMessage.size()) {
            throw new NotFoundException("Can't not found message with " + id);
        }

        listMessage.removeIf(loop -> loop.getId().equals(id));

        return new ListResponse(listMessage);
    }

    private List<MessageEntity> createListMessage() {
        List<MessageEntity> listMessage = new ArrayList<>();
        MessageEntity entity1 = new MessageEntity(1, "Hello, world!", "Office", LocalDateTime.of(2023, 1, 15, 8, 30));
        MessageEntity entity2 = new MessageEntity(2, "Meeting at 2 PM", "Conference Room A", LocalDateTime.of(2023, 1, 16, 14, 0));
        MessageEntity entity3 = new MessageEntity(3, "Check out the new product", "Store", LocalDateTime.of(2023, 1, 17, 18, 45));
        MessageEntity entity4 = new MessageEntity(4, "Reminder: Pay bills", "Home", LocalDateTime.of(2023, 1, 18, 20, 0));
        MessageEntity entity5 = new MessageEntity(5, "Happy Birthday!", "Party Venue", LocalDateTime.of(2023, 1, 19, 12, 15));

        listMessage.add(entity1);
        listMessage.add(entity2);
        listMessage.add(entity3);
        listMessage.add(entity4);
        listMessage.add(entity5);

        return listMessage;
    }
}
