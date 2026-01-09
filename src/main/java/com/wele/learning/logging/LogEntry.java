package com.wele.learning.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document("log_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LogEntry {

    @Id
    private String id;
    private Instant ts;
    private String source;
    private String level;
    private String message;
    private Map<String,Object> ctx;
    private String user;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Instant getTs() {
        return ts;
    }
    public void setTs(Instant ts) {
        this.ts = ts;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, Object> getCtx() {
        return ctx;
    }
    public void setCtx(Map<String, Object> ctx) {
        this.ctx = ctx;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    private String path;
    private String method;
}
