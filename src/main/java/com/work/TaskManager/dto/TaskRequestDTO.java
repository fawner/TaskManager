package com.work.TaskManager.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class TaskRequestDTO {
    @NotNull
    private String title;
    private String description;
    @NotNull
    private Long userId;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
}
