package com.stc.dashboard.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * 시안의 theme.js / theme.dark · theme.light · ACCENTS 1:1 포팅.
 * Tokyo-Night 톤 — UnifiedApp.theme + accent + density 와 매핑.
 */

data class BoardColors(
    val bg: Color,
    val surface: Color,
    val card: Color,
    val cardHi: Color,
    val line: Color,
    val text: Color,
    val textHi: Color,
    val sub: Color,
    val subDim: Color,
    val onAccent: Color,
    val sheetScrim: Color,
)

private val DarkColors = BoardColors(
    bg = Color(0xFF16161E),
    surface = Color(0xFF1A1B26),
    card = Color(0xFF1F2335),
    cardHi = Color(0xFF24283B),
    line = Color(0xFF2A2E43),
    text = Color(0xFFC0CAF5),
    textHi = Color(0xFFDFE4FF),
    sub = Color(0xFF787E9B),
    subDim = Color(0xFF565F89),
    onAccent = Color(0xFF0B0D18),
    sheetScrim = Color(0x9E08090F),  // 0x9E = 62% alpha
)

private val LightColors = BoardColors(
    bg = Color(0xFFE1E2E9),
    surface = Color(0xFFEAEBF1),
    card = Color(0xFFFFFFFF),
    cardHi = Color(0xFFF3F4FA),
    line = Color(0xFFD3D6E4),
    text = Color(0xFF343B58),
    textHi = Color(0xFF1F2335),
    sub = Color(0xFF737A9B),
    subDim = Color(0xFF9AA0BD),
    onAccent = Color(0xFFFFFFFF),
    sheetScrim = Color(0x6B282A3C),  // 0x6B = 42% alpha
)

/** 사용자 선택 강조색 — 다크/라이트별 다른 값. */
data class Accent(val key: String, val label: String, val dark: Color, val light: Color)

val Accents: List<Accent> = listOf(
    Accent("blue", "블루", Color(0xFF7AA2F7), Color(0xFF2E7DE9)),
    Accent("purple", "퍼플", Color(0xFFBB9AF7), Color(0xFF7847D6)),
    Accent("teal", "틸", Color(0xFF73DACA), Color(0xFF1F8F7E)),
    Accent("orange", "오렌지", Color(0xFFFF9E64), Color(0xFFC85A2A)),
)

/** ProgressRing/Bar 색 — 시안 barColor(): 진행률 따라 결정. */
fun barColor(percent: Int): Color = when {
    percent >= 100 -> Color(0xFF9ECE6A)  // green — 완료
    percent >= 60  -> Color(0xFF7AA2F7)  // blue  — 순항
    percent >= 30  -> Color(0xFFE0AF68)  // yellow — 진행 중
    else           -> Color(0xFFF7768E)  // pink  — 초기
}

/** 진행률 톤 라벨. */
fun progressTone(percent: Int): String = when {
    percent >= 100 -> "완료"
    percent >= 60  -> "순항"
    percent >= 30  -> "진행 중"
    else           -> "초기"
}

/** 강조색에 alpha 14% (dark) / 12% (light) — accentSoft. */
fun Color.soft(isDark: Boolean): Color =
    copy(alpha = if (isDark) 0.14f else 0.12f)

/** Composable에서 토큰을 꺼내 쓰는 방식 — CompositionLocal. */
val LocalBoardColors = staticCompositionLocalOf { DarkColors }
val LocalAccent = staticCompositionLocalOf { Accents[0].dark }
val LocalDensity = staticCompositionLocalOf { Densities[1] }   // cozy default
val LocalIsDarkTheme = staticCompositionLocalOf { true }

object Board {
    val colors: BoardColors
        @Composable get() = LocalBoardColors.current
    val accent: Color
        @Composable get() = LocalAccent.current
    val density: BoardDensity
        @Composable get() = LocalDensity.current
    val isDark: Boolean
        @Composable get() = LocalIsDarkTheme.current
}

@Composable
fun BoardTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    accent: Accent = Accents[0],
    density: BoardDensity = Densities[1],
    content: @Composable () -> Unit,
) {
    val colors = if (isDark) DarkColors else LightColors
    val accentColor = if (isDark) accent.dark else accent.light
    // Material3 ColorScheme 도 우리 토큰으로 채움 — 표준 컴포넌트(Switch 등)에 반영
    val cs = if (isDark) {
        darkColorScheme(
            primary = accentColor,
            onPrimary = colors.onAccent,
            background = colors.bg,
            surface = colors.surface,
            onBackground = colors.text,
            onSurface = colors.text,
        )
    } else {
        lightColorScheme(
            primary = accentColor,
            onPrimary = colors.onAccent,
            background = colors.bg,
            surface = colors.surface,
            onBackground = colors.text,
            onSurface = colors.text,
        )
    }
    CompositionLocalProvider(
        LocalBoardColors provides colors,
        LocalAccent provides accentColor,
        LocalDensity provides density,
        LocalIsDarkTheme provides isDark,
    ) {
        MaterialTheme(colorScheme = cs, content = content)
    }
}
