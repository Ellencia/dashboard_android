package com.stc.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.barColor

/**
 * 시안 ProgressBar — 가로 막대 진행률.
 */
@Composable
fun ProgressBar(
    percent: Int,
    modifier: Modifier = Modifier,
    height: Dp = 6.dp,
) {
    val clamped = percent.coerceIn(0, 100)
    val color = barColor(clamped)
    val track = Board.colors.line
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(height / 2))
            .background(track),
    ) {
        if (clamped > 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(clamped / 100f)
                    .height(height)
                    .clip(RoundedCornerShape(height / 2))
                    .background(color),
            )
        }
    }
}
