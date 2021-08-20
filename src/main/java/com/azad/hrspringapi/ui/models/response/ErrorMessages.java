package com.azad.hrspringapi.ui.models.response;

public enum ErrorMessages {

    RESOURCE_NOT_FOUND("Resource not found."),
    RESOURCE_NOT_FOUND_WITH_ID("Resource with provided ID is not found."),
    RESOURCE_NOT_AVAILABLE("No resource available."),
    MISSING_REQUIRED_FIELDS("Missing required fields. Please check documentation for required fields."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    AUTHENTICATION_FAILED("Authentication failed."),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
