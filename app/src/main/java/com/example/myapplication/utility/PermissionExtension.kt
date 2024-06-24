package com.example.myapplication.utility

import android.app.AlarmManager
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.recycler_view_data_modals.PermissionModal


/**
 *  function is used to add the permission to the list
 *  and it will only add the permission if it is needed and it is not
 *  granted yet by he user.
 */
fun ArrayList<PermissionModal>.addDefaultPermission(
    context: Context,
    isAllPermissionGranted: (Boolean) -> Unit
) {
    runOnBackgroundThread {
        clear()
        val permissionTitle = context.resources.getStringArray(R.array.permission_name)
        val permissionDesc = context.resources.getStringArray(R.array.permission_desc)

        addPostNotificationPermissionIfCan(
            context,
            PermissionModal(
                permissionTitle[0],
                permissionDesc[0],
                R.drawable.ic_nav_lock_selected,
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            ) )


        // adding the usage access permission
        addUsageAccessPermissionIfCan(
            context,
            PermissionModal(
                permissionTitle[1],
                permissionDesc[1],
                R.drawable.ic_nav_lock_selected,
                Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            )
        )

        // adding the display over other apps permission
        addDisplayOverOtherAppsPermissionIfCan(
            context,
            PermissionModal(
                permissionTitle[2],
                permissionDesc[2],
                R.drawable.ic_nav_lock_selected,
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            )
        )

        // add the read app notification permission
        addReadAllNotificationPermissionIfCan(
            context,
            PermissionModal(
                permissionTitle[3],
                permissionDesc[3],
                R.drawable.ic_nav_lock_selected,
                Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            )
        )

        // add the accessibility permission
        addAccessibilityPermissionIfCan(
            context,
            PermissionModal(
                permissionTitle[4],
                permissionDesc[4],
                R.drawable.ic_nav_lock_selected,
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        )


        // add the exact alarm permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            addExactAlarmPermissionIfCan(
                context,
                PermissionModal(
                    permissionTitle[5],
                    permissionDesc[5],
                    R.drawable.ic_nav_lock_selected,
                    Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                )
            )
        }

        isAllPermissionGranted(this.isEmpty())
    }


}

fun ArrayList<PermissionModal>.addUsageAccessPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        val packageManager = context.packageManager
        val applicationInfo = packageManager.getApplicationInfo(context.packageName, 0)
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOpsManager.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            applicationInfo.uid,
            applicationInfo.packageName
        )
        if (mode != AppOpsManager.MODE_ALLOWED) {
            add(permissionModal)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ArrayList<PermissionModal>.addDisplayOverOtherAppsPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        if (!Settings.canDrawOverlays(context))
            add(permissionModal)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ArrayList<PermissionModal>.addReadAllNotificationPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        if (!NotificationManagerCompat.getEnabledListenerPackages(context)
                .contains(context.packageName)
        )
            add(permissionModal)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun ArrayList<PermissionModal>.addAccessibilityPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        val accessibilityEnabled = Settings.Secure.getInt(
            context.contentResolver,
            Settings.Secure.ACCESSIBILITY_ENABLED
        ) != 0
        if (!accessibilityEnabled)
            add(permissionModal)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ArrayList<PermissionModal>.addPostNotificationPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            || android.Manifest.permission.POST_NOTIFICATIONS.isPermissionGranted(context)
        )
            return

        add(permissionModal)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ArrayList<PermissionModal>.addExactAlarmPermissionIfCan(
    context: Context,
    permissionModal: PermissionModal
) {
    try {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (alarmManager.canScheduleAlarms())
            return

        add(permissionModal)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 *  extension function which is used to check the status for
 *  normal runtime permission.
 */
fun String.isPermissionGranted(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context, this
    ) == PackageManager.PERMISSION_GRANTED
}

fun AlarmManager.canScheduleAlarms(): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S)
        return true
    if (canScheduleExactAlarms())
        return true
    return false

}
