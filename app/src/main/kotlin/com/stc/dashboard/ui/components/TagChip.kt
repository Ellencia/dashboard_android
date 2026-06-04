package com.stc.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.soft
import com.stc.dashboard.theme.tagColor

/**
 * 시안 TagChip — 태그 이름 + 선택적 카운트.
 * 배경은 태그 색의 14% alpha (Tokyo-Night accent soft 와 동일 톤).
 */
@Composable
fun TagChip(
    tag: String,
    count: Int? = null,
    modifier: Modifier = Modifier,
) {
    val color = tagColor(tag)
    val label = if (count != null) "# $tag · $count" else "# $tag"
    Text(
        text = label,
        color = color,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(color.soft(Board.isDark))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}
