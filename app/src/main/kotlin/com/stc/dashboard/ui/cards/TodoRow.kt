package com.stc.dashboard.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.data.Todo
import com.stc.dashboard.theme.Board
import com.stc.dashboard.ui.components.TagChip

/**
 * 시안 TodoRow — 체크박스 23x23 r8, 텍스트, 태그 칩들.
 * 완료: 체크박스 #9ECE6A 채움 + 텍스트 strikethrough + 흐림.
 */
@Composable
fun TodoRow(
    todo: Todo,
    modifier: Modifier = Modifier,
    onToggle: () -> Unit = {},
) {
    val c = Board.colors
    Row(
        modifier = modifier
            .clickable(onClick = onToggle)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        // checkbox
        Box(
            modifier = Modifier
                .size(23.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (todo.done) Color(0xFF9ECE6A) else Color.Transparent)
                .border(
                    width = if (todo.done) 0.dp else 1.5.dp,
                    color = if (todo.done) Color.Transparent else c.line,
                    shape = RoundedCornerShape(8.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (todo.done) {
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = "완료",
                    tint = c.bg,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
        // text + tags
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = todo.text,
                color = if (todo.done) c.subDim else c.text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                style = if (todo.done) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle.Default,
            )
            if (todo.tags.isNotEmpty()) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    todo.tags.forEach { TagChip(it) }
                }
            }
        }
    }
}
