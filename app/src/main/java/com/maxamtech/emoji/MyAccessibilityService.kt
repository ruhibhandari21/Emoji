package com.maxamtech.emoji

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService : AccessibilityService() {


    override fun onServiceConnected() {
        super.onServiceConnected()
        Toast.makeText(this, "onServiceConnected", Toast.LENGTH_SHORT).show()
    }

    /**
     * Callback for interrupting the accessibility feedback.
     */
    override fun onInterrupt() {
        Toast.makeText(this, "onInterrupt", Toast.LENGTH_SHORT).show()
    }

    /**
     * Callback for [android.view.accessibility.AccessibilityEvent]s.
     *
     * @param event The new event. This event is owned by the caller and cannot be used after
     * this method returns. Services wishing to use the event after this method returns should
     * make a copy.
     */
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        startService(Intent(applicationContext, HUD::class.java))
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

}
