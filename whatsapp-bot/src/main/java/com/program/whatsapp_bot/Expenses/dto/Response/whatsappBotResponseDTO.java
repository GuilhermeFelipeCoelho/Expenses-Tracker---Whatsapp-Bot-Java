package com.program.whatsapp_bot.Expenses.dto.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class whatsappBotResponseDTO {

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
        private List<Status> statuses;
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
    public static class Status {

        private String id;
        private String status;
        private String timestamp;

        @JsonProperty("recipient_id")
        private String recipientId;

        private Conversation conversation;
        private Pricing pricing;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Conversation {

        private String id;
        private Origin origin;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Origin {

        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pricing {

        private boolean billable;

        @JsonProperty("pricing_model")
        private String pricingModel;

        private String category;
    }
}
