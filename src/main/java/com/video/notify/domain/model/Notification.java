package com.video.notify.domain.model;


public record Notification(String userId, String videoId, String status, String message, String subject, String email) {
}
