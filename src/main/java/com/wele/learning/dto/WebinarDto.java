package com.wele.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebinarDto {

    private String id;
    private String webinarCode;
    private String title;
    private String subtitle;
    private String description;
    private String level;
    private String durationMinutes;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Boolean isPaid;
    private String timezone;
    private Boolean isLiveNow;
    private String thumbnailImageUrl;
    private ObjectId primarySpeaker;
    private List<ObjectId> secondarySpeakers;
    private List<String> categories;
    private List<String> tags;
    private String meetingLink;
    private boolean isRecording;
    private String recordingUrl;
    private Boolean registrationRequired;
    private LocalDateTime registrationOpenDate;
    private LocalDateTime registrationCloseDate;
    private Integer maxRegistrations;
    private String cancelReason;
    private LocalDateTime publishedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebinarCode() {
        return webinarCode;
    }

    public void setWebinarCode(String webinarCode) {
        this.webinarCode = webinarCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(String durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }


    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getLiveNow() {
        return isLiveNow;
    }

    public void setLiveNow(Boolean liveNow) {
        isLiveNow = liveNow;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public ObjectId getPrimarySpeaker() {
        return primarySpeaker;
    }

    public void setPrimarySpeaker(ObjectId primarySpeaker) {
        this.primarySpeaker = primarySpeaker;
    }

    public List<ObjectId> getSecondarySpeakers() {
        return secondarySpeakers;
    }

    public void setSecondarySpeakers(List<ObjectId> secondarySpeakers) {
        this.secondarySpeakers = secondarySpeakers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }

    public Boolean getRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(Boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public LocalDateTime getRegistrationOpenDate() {
        return registrationOpenDate;
    }

    public void setRegistrationOpenDate(LocalDateTime registrationOpenDate) {
        this.registrationOpenDate = registrationOpenDate;
    }

    public LocalDateTime getRegistrationCloseDate() {
        return registrationCloseDate;
    }

    public void setRegistrationCloseDate(LocalDateTime registrationCloseDate) {
        this.registrationCloseDate = registrationCloseDate;
    }

    public Integer getMaxRegistrations() {
        return maxRegistrations;
    }

    public void setMaxRegistrations(Integer maxRegistrations) {
        this.maxRegistrations = maxRegistrations;
    }


    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
    }
}
