/* Copyright Urban Airship and Contributors */

package com.urbanairship.android.framework.proxy.events

import com.urbanairship.android.framework.proxy.Event
import com.urbanairship.android.framework.proxy.EventType
import com.urbanairship.android.framework.proxy.Utils
import com.urbanairship.push.NotificationInfo
import com.urbanairship.push.PushMessage

/**
 * Push received event.
 */
internal class PushReceivedEvent : Event {

    override val body: Map<String, Any>
    override val type: EventType

    constructor(body: Map<String, Any>, isForeground: Boolean) {
        this.body = body

        this.type = if (isForeground) {
            EventType.FOREGROUND_PUSH_RECEIVED
        } else {
            EventType.BACKGROUND_PUSH_RECEIVED
        }
    }
    /**
     * Default constructor.
     *
     * @param message The push message.
     * @param isForeground If received in the foreground or not.
     */
    constructor(message: PushMessage, isForeground: Boolean) : this(
        mapOf(
            "pushPayload" to Utils.notificationMap(message)
        ),
        isForeground
    )

    /**
     * Default constructor.
     *
     * @param notificationInfo The posted notification info.
     * @param isForeground If received in the foreground or not.
     */
    constructor(notificationInfo: NotificationInfo, isForeground: Boolean) : this(
        mapOf(
            "pushPayload" to Utils.notificationMap(
                notificationInfo.message,
                notificationInfo.notificationId,
                notificationInfo.notificationTag
            )
        ),
        isForeground
    )

}
