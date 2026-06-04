package com.stc.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class SyncState { Synced, Syncing, Offline }

/**
 * 시안 SyncBadge — 헤더 우측의 동기화 상태 칩.
 */
@Composable
fun SyncBadge(state: SyncState, modifier: Modifier = Modifier) {
    val (label, icon, bg, fg) = when (state) {
        SyncState.Synced  -> Quad("동기화됨", Icons.Outlined.CheckCircle, Color(0xFF9ECE6A), Color(0xFF0B0D18))
        SyncState.Syncing -> Quad("동기화 중", Icons.Outlined.Sync,        Color(0xFFE0AF68), Color(0xFF0B0D18))
        SyncState.Offline -> Quad("오프라인",  Icons.Outlined.CloudOff,    Color(0xFFF7768E), Color(0xFF0B0D18))
    }
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(bg.copy(alpha = 0.18f))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(icon, contentDescription = label, tint = bg, modifier = Modifier.padding(0.dp))
        Text(label, color = bg, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
    }
}

private data class Quad(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val bg: Color,
    val fg: Color,
)
