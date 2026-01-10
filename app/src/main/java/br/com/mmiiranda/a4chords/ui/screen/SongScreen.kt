package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SongScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("← Voltar")
        }

        Text(
            text = "Somewhere Over the Rainbow",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Israel Kamakawiwo'ole",
            fontSize = 18.sp,
            color = Color(0xFF2196F3),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Intro: C - G - Am - F\n\n" +
                        "[C]Somewhere over the rainbow\n" +
                        "[G]Way up high\n" +
                        "[Am]And the dreams that you dream of\n" +
                        "[F]Once in a lullaby\n\n" +
                        "[C]Somewhere over the rainbow\n" +
                        "[G]Bluebirds fly\n" +
                        "[Am]And the dreams that you dream of\n" +
                        "[F]Dreams really do come true",
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* FALTA FAZER ISSO AQ */ },
            ) {
                Text("❤️ Favoritar")
            }

            Button(
                onClick = { /* E ISSO AQ TMB */ },
            ) {
                Text("📤 Compartilhar")
            }
        }
    }
}