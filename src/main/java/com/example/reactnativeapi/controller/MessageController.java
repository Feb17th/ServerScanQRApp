package com.example.reactnativeapi.controller;

import com.example.reactnativeapi.entity.MessageEntity;
import com.example.reactnativeapi.request.CreateMessageRequest;
import com.example.reactnativeapi.response.ListResponse;
import com.example.reactnativeapi.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Operation(summary = "Get All Message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Message Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ListResponse.class))
                    })
    })
    @GetMapping("")
    public ResponseEntity<ListResponse> getAllMessage() {
        return ResponseEntity.ok(messageService.getAllMessage());
    }


    @Operation(summary = "Get One Message By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get One Message Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MessageEntity.class))
                    })
    })
    @GetMapping("{id}")
    public ResponseEntity<MessageEntity> getAllMessage(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(messageService.getOneMessage(id));
    }


    @Operation(summary = "Create Message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create One Message Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ListResponse.class))
                    })
    })
    @PostMapping("")
    public ResponseEntity<ListResponse> createMessage(
            @RequestBody CreateMessageRequest createMessageRequest
    ) {
        return ResponseEntity.ok(messageService.createMessage(createMessageRequest));
    }

    @Operation(summary = "Update Message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Message Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ListResponse.class))
                    })
    })
    @PutMapping("")
    public ResponseEntity<ListResponse> updateMessage(
            @RequestBody MessageEntity messageEntity
    ) {
        return ResponseEntity.ok(messageService.updateMessage(messageEntity));
    }

    @Operation(summary = "Delete One Message By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete One Message Successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ListResponse.class))
                    })
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ListResponse> deleteMessage(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }
}
