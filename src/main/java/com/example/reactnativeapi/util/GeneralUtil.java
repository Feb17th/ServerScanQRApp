package com.example.reactnativeapi.util;

import com.example.reactnativeapi.exception.BadRequestException;
import com.example.reactnativeapi.exception.NotFoundException;
import com.example.reactnativeapi.response.EncodedJwtResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.util.Base64;
import java.util.UUID;

public class GeneralUtil {
    public static UUID checkUUID(String idFromPathVariable){
        try {
            return UUID.fromString(idFromPathVariable);
        } catch (Exception e) {
            throw new BadRequestException("Id from path variable is not uuid.");
        }
    }

    public static int checkRequest(String request) {
        if (request == null || request.equals("")) {
            throw new BadRequestException("Request must be a Integer");
        }
        try {
            return Integer.parseInt(request);
        } catch (Exception e) {
            throw new BadRequestException("Request must be a Integer");
        }
    }

    public static EncodedJwtResponse encodedJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadRequestException("header is null or didn't not start with Bearer");
        }
        String accessToken = authHeader.substring(7);

        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);

            UUID userId = UUID.fromString(jsonNode.get("userId").asText());

            JsonNode rolesArray = jsonNode.get("roles");
            String[] roles = new String[rolesArray.size()];
            for (int i = 0; i < rolesArray.size(); i++) {
                roles[i] = rolesArray.get(i).asText();
            }

            return new EncodedJwtResponse(userId, roles);
        } catch (Exception e) {
            throw new NotFoundException("Not found information from access token");
        }
    }
}
