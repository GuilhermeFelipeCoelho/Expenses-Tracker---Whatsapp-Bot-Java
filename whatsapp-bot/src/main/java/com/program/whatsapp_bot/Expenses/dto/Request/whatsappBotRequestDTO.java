package com.program.whatsapp_bot.Expenses.dto.Request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class whatsappBotRequestDTO {

    @JsonProperty("object")
    private String object;
    private List<Entry> entry;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Entry {

        private String id;
        private List<Change> changes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Change {

        private Value value;
        private String field;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {

        @JsonProperty("messaging_product")
        private String messagingProduct;

        private Metadata metadata;
        private List<Contact> contacts;
        private List<Message> messages;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {

        @JsonProperty("display_phone_number")
        private String displayPhoneNumber;

        @JsonProperty("phone_number_id")
        private String phoneNumberId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contact {

        private Profile profile;

        @JsonProperty("wa_id")
        private String waId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Profile {

        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        private String from;
        private String id;
        private String timestamp;
        private String type;
        private Text text;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Text {

        private String body;
    }
}
