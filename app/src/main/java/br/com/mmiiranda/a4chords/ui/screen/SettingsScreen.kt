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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    // OBSERVE o estado do tema
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    // VERIFIQUE se o lembrete já está agendado
    LaunchedEffect(Unit) {
        isNotificationEnabled = notificationViewModel.isReminderScheduled(context)
    }

    // Lançador para permissão de notificação (Android 13+)
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permissão concedida, agenda o lembrete
            notificationViewModel.scheduleDailyReminder(
                context,
                selectedTime.hour,
                selectedTime.minute
            )
            isNotificationEnabled = true
        } else {
            // Permissão negada
            isNotificationEnabled = false
            showPermissionDialog = true
        }
    }

    // Verifica se precisa solicitar permissão
    val needsPermission = PermissionUtils.shouldRequestNotificationPermission() &&
            !PermissionUtils.hasNotificationPermission(context)

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Permissão necessária") },
            text = {
                Column {
                    Text("Para enviar lembretes de prática, o app precisa da permissão para mostrar notificações.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Vá em:")
                    Text("• Configurações do dispositivo")
                    Text("• Aplicativos")
                    Text("• 4Chords")
                    Text("• Notificações")
                    Text("• Permitir notificações")
                }
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
                        Text(
                            "Voltar"
                        )
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
                                checked = isDarkTheme,
                                onCheckedChange = { themeViewModel.toggleTheme() },
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
                                if (needsPermission && !isNotificationEnabled) {
                                    Text(
                                        "Toque para ativar",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                } else if (needsPermission) {
                                    Text(
                                        "Permissão necessária",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            Switch(
                                checked = isNotificationEnabled,
                                onCheckedChange = { enabled ->
                                    if (enabled) {
                                        if (needsPermission) {
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
                                        // Ciclo entre horários pré-definidos
                                        selectedTime = when (selectedTime.hour) {
                                            9 -> LocalTime.of(12, 0)
                                            12 -> LocalTime.of(15, 0)
                                            15 -> LocalTime.of(18, 0)
                                            18 -> LocalTime.of(21, 0)
                                            21 -> LocalTime.of(9, 0)
                                            else -> LocalTime.of(9, 0)
                                        }

                                        // Reagenda com novo horário
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

                if (isNotificationEnabled && !needsPermission) {
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
                                "Para praticar violão todos os dias às ${selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"))}.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (needsPermission && !isNotificationEnabled) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f)
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
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
                                    "Permissão de notificação necessária",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }

                            Text(
                                "Para receber lembretes, você precisa permitir notificações. Toque no interruptor acima para solicitar a permissão.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                    }
                }
            }
        }
    }
}