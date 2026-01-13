package br.com.mmiiranda.a4chords.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.mmiiranda.a4chords.MainActivity
import br.com.mmiiranda.a4chords.R
import br.com.mmiiranda.a4chords.utils.PermissionUtils

class PracticeNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        return if (showNotification()) {
            Result.success()
        } else {
            Result.failure()
        }
    }

    private fun showNotification(): Boolean {
        val context = applicationContext

        if (!PermissionUtils.hasNotificationPermission(context)) {
            return false
        }

        try {
            createNotificationChannel(context)

            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Hora de praticar! 🎸")
                .setContentText("Que tal treinar algumas músicas hoje?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Não deixe a prática de lado! Abra o 4Chords e toque algumas músicas hoje. \n\n🎶 A prática constante é o segredo do progresso!")
                )
                .build()

            if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                with(NotificationManagerCompat.from(context)) {
                    notify(NOTIFICATION_ID, notification)
                }
                return true
            }
            return false
        } catch (e: SecurityException) {
            e.printStackTrace()
            return false
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    companion object {
        const val CHANNEL_ID = "practice_reminder_channel"
        const val NOTIFICATION_ID = 1
        const val WORK_NAME = "daily_practice_reminder"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Lembretes de Prática"
                val description = "Notificações para lembrar de praticar violão"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    this.description = description
                }

                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}