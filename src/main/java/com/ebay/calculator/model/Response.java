package com.ebay.calculator.model;

public class Response {
    private String code;
    private String message;
    private Object data;

    // Private constructor to enforce builder usage
    private Response(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    // Builder static inner class
    public static class Builder {
        private String code;
        private String message;
        private Object data;

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    // Optional static method to start building
    public static Builder builder() {
        return new Builder();
    }
}
