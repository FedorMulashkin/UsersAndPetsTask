package com.fedormulashkin.usersandpetstask.exceptionhandling;

abstract class ApiSubError {

}


class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiValidationError that = (ApiValidationError) o;

        if (getObject() != null ? !getObject().equals(that.getObject()) : that.getObject() != null) return false;
        if (getField() != null ? !getField().equals(that.getField()) : that.getField() != null) return false;
        if (getRejectedValue() != null ? !getRejectedValue().equals(that.getRejectedValue()) : that.getRejectedValue() != null)
            return false;
        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getObject() != null ? getObject().hashCode() : 0;
        result = 31 * result + (getField() != null ? getField().hashCode() : 0);
        result = 31 * result + (getRejectedValue() != null ? getRejectedValue().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}