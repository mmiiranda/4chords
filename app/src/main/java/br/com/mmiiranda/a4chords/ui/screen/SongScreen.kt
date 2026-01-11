package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mmiiranda.a4chords.ui.components.ActionButton
import br.com.mmiiranda.a4chords.utils.extractChords

@Composable
fun SongScreen(
    songTitle: String,
    artistName: String,
    chordsText: String,
    onBackClick: () -> Unit,
    onArtistClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(
                text = "←",
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(end = 12.dp)
            )

            Text(
                text = "4Chords",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Text(
            text = songTitle,
            fontSize = 26.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Text(
            text = artistName,
            fontSize = 16.sp,
            color = Color(0xFF2196F3),
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { onArtistClick() }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            ActionButton("♡ Curtir")
            ActionButton("↗ Compartilhar")
            ActionButton("✎ Sugerir edição")
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Acordes",
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = extractChords(chordsText),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = chordsText,
                modifier = Modifier.padding(16.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enviado por usuário • 2026",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
