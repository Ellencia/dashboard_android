package com.stc.dashboard.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * 시안의 density token — compact / cozy / roomy.
 * pad = 카드 내부 패딩, radius = 카드 모서리, cardGap = 카드 사이 간격, compact = 일부 부가정보 숨김 플래그.
 */
data class BoardDensity(
    val key: String,
    val label: String,
    val pad: Dp,
    val radius: Dp,
    val cardGap: Dp,
    val compact: Boolean,
)

val Densities: List<BoardDensity> = listOf(
    BoardDensity("compact", "촘촘", 14.dp, 14.dp, 9.dp, true),
    BoardDensity("cozy",    "보통", 16.dp, 16.dp, 12.dp, false),  // default
    BoardDensity("roomy",   "여유", 19.dp, 18.dp, 15.dp, false),
)
