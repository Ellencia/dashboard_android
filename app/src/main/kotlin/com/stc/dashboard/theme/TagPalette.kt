package com.stc.dashboard.theme

import androidx.compose.ui.graphics.Color

/**
 * 시안의 TAG_PALETTE — 태그 문자열 해시로 결정적 색 매핑.
 * 우리 PC 위젯의 _TAG_PALETTE 와 동일한 Tokyo-Night 8색.
 */
private val TagPalette = listOf(
    Color(0xFF7AA2F7), Color(0xFF9ECE6A), Color(0xFFE0AF68), Color(0xFFF7768E),
    Color(0xFFBB9AF7), Color(0xFF7DCFFF), Color(0xFFFF9E64), Color(0xFF73DACA),
)

/** 같은 태그는 항상 같은 색 — hash 기반. */
fun tagColor(tag: String): Color {
    val h = tag.hashCode()
    val idx = ((h % TagPalette.size) + TagPalette.size) % TagPalette.size
    return TagPalette[idx]
}
