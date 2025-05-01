package com.work.TaskManager.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class TaskResponseDTO {
    private Long id;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }

    public String toString() {
        return String.format(
                "{\"id\": %d,\"title\":\" %s\",\"description\":\" %s\",\"userId\": %d}",
                getId(), getTitle(), getDescription(), getUserId()
        );
    }
}
