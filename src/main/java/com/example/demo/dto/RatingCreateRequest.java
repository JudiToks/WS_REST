package com.example.demo.dto;



public class RatingCreateRequest {
    private Integer value;
    private String comment;

    public Integer getValue() {
        return this.value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
