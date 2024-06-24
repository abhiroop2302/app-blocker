package com.example.myapplication.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationService: NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Handle notification posted
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Handle notification removed
    }
}