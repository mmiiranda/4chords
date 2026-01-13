package br.com.mmiiranda.a4chords.ui.screen

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import br.com.mmiiranda.a4chords.ui.viewmodel.ThemeViewModel
import br.com.mmiiranda.a4chords.ui.theme.SwitchColors
import br.com.mmiiranda.a4chords.ui.viewmodel.NotificationViewModel
import br.com.mmiiranda.a4chords.utils.PermissionUtils
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    themeViewModel: ThemeViewModel,
    notificationViewModel: NotificationViewModel,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    var isNotificationEnabled by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf(LocalTime.of(18, 0)) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            notificationViewModel.scheduleDailyReminder(
                context,
                selectedTime.hour,
                selectedTime.minute
            )
            isNotificationEnabled = true
        } else {
            isNotificationEnabled = false
            showPermissionDialog = true
        }
    }

    LaunchedEffect(Unit) {
        isNotificationEnabled = notificationViewModel.isReminderScheduled(context)
    }

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Permissão necessária") },
            text = {
                Text("Para enviar lembretes de prática, o app precisa da permissão para mostrar notificações. Você pode ativar essa permissão nas configurações do app.")
            },
            confirmButton = {
                Button(
                    onClick = { showPermissionDialog = false }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configurações") },
                navigationIcon = {
                    Button(onClick = onBackClick) {
                        Text("Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Aparência",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Modo Escuro",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Switch(
                                checked = themeViewModel.isDarkTheme,
                                onCheckedChange = { themeViewModel.toggleTheme() },
                                colors = SwitchColors
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Notificações",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    "Lembretes Diários",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                                    !PermissionUtils.hasNotificationPermission(context)) {
                                    Text(
                                        "Permissão necessária",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            Switch(
                                checked = isNotificationEnabled,
                                onCheckedChange = {
                                    if (it) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                            notificationPermissionLauncher.launch(
                                                Manifest.permission.POST_NOTIFICATIONS
                                            )
                                        } else {
                                            notificationViewModel.scheduleDailyReminder(
                                                context,
                                                selectedTime.hour,
                                                selectedTime.minute
                                            )
                                            isNotificationEnabled = true
                                        }
                                    } else {
                                        notificationViewModel.cancelDailyReminder(context)
                                        isNotificationEnabled = false
                                    }
                                }
                            )
                        }

                        if (isNotificationEnabled) {
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                "Horário do lembrete:",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                                    style = MaterialTheme.typography.headlineSmall
                                )

                                Button(
                                    onClick = {
                                        selectedTime = when (selectedTime.hour) {
                                            9 -> LocalTime.of(12, 0)
                                            12 -> LocalTime.of(15, 0)
                                            15 -> LocalTime.of(18, 0)
                                            18 -> LocalTime.of(21, 0)
                                            else -> LocalTime.of(9, 0)
                                        }

                                        notificationViewModel.scheduleDailyReminder(
                                            context,
                                            selectedTime.hour,
                                            selectedTime.minute
                                        )
                                    },
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text("Alterar")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isNotificationEnabled) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.2f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 8.dp)
                            ) {
                                Text(
                                    "🎸",
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    "Você receberá um lembrete diário",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Text(
                                "Para praticar violão todos os dias no horário escolhido.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}