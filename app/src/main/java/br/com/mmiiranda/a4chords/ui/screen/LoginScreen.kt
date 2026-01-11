package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onSkipLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo/Título
        Text(
            text = "4Chords",
            fontSize = 48.sp,
            color = Color(0xFF2196F3),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Cifras de Ukulele",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Card do formulário
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tabs Login/Registro
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(
                        onClick = { isLogin = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "ENTRAR",
                            color = if (isLogin) Color(0xFF2196F3) else Color.Gray,
                            fontWeight = if (isLogin) FontWeight.Bold else FontWeight.Normal
                        )
                    }

                    TextButton(
                        onClick = { isLogin = false },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "CADASTRAR",
                            color = if (!isLogin) Color(0xFF2196F3) else Color.Gray,
                            fontWeight = if (!isLogin) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                // Campos do formulário
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                )

                if (!isLogin) {
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        label = { Text("Confirmar Senha") },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão principal
                Button(
                    onClick = { onLoginSuccess() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Text(
                        text = if (isLogin) "ENTRAR" else "CRIAR CONTA",
                        fontSize = 16.sp
                    )
                }

                // Botão esqueci senha
                if (isLogin) {
                    TextButton(
                        onClick = { /* TODO: Esqueci senha */ },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Esqueci minha senha",
                            color = Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botão pular login
        TextButton(onClick = onSkipLogin) {
            Text(
                text = "Continuar sem login →",
                color = Color(0xFF2196F3)
            )
        }

    }
}