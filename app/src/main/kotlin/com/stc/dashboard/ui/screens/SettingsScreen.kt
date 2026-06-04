package com.stc.dashboard.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.theme.Accent
import com.stc.dashboard.theme.Accents
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.BoardDensity
import com.stc.dashboard.theme.Densities

/**
 * 시안 SettingsScreen — 동기화·입력·표시 섹션.
 * 1차 구현: 표시(테마·강조색·밀도) 라이브 적용.
 * 동기화·카톡은 placeholder — sync 인프라 붙으면 채움.
 */
@Composable
fun SettingsScreen(
    isDark: Boolean,
    onDarkChange: (Boolean) -> Unit,
    currentAccent: Accent,
    onAccentChange: (Accent) -> Unit,
    currentDensity: BoardDensity,
    onDensityChange: (BoardDensity) -> Unit,
) {
    val c = Board.colors
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item { SectionLabel("동기화") }
        item {
            CardBox {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("클라우드 동기화", color = c.textHi,
                             fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("아직 안 연결됨", color = c.sub,
                             fontSize = 12.5.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Text("준비 중", color = c.sub, fontSize = 11.5.sp,
                         fontWeight = FontWeight.SemiBold,
                         modifier = Modifier
                             .clip(RoundedCornerShape(99.dp))
                             .background(c.cardHi)
                             .padding(horizontal = 10.dp, vertical = 5.dp))
                }
            }
        }
        item { SectionLabel("입력") }
        item {
            CardBox {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(Modifier.size(40.dp).clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFFEE500)),
                        contentAlignment = Alignment.Center) {
                        Text("💬", fontSize = 22.sp)
                    }
                    Column(Modifier.weight(1f)) {
                        Text("카톡으로 할 일 추가", color = c.textHi,
                             fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("대화를 공유하면 AI가 할 일을 뽑아 STATUS.md에 추가",
                             color = c.sub, fontSize = 12.5.sp,
                             fontWeight = FontWeight.SemiBold)
                    }
                    Text("준비 중", color = c.sub, fontSize = 11.5.sp,
                         fontWeight = FontWeight.SemiBold,
                         modifier = Modifier
                             .clip(RoundedCornerShape(99.dp))
                             .background(c.cardHi)
                             .padding(horizontal = 10.dp, vertical = 5.dp))
                }
            }
        }
        item { SectionLabel("표시") }
        item {
            CardBox {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("테마", color = c.textHi, fontSize = 13.sp,
                         fontWeight = FontWeight.Bold)
                    SegmentRow(
                        options = listOf("다크" to true, "라이트" to false),
                        selected = isDark,
                        onSelect = onDarkChange,
                    )
                    Text("강조색", color = c.textHi, fontSize = 13.sp,
                         fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Accents.forEach { a ->
                            val sw = if (isDark) a.dark else a.light
                            Box(
                                Modifier
                                    .size(44.dp)
                                    .clickable { onAccentChange(a) },
                                contentAlignment = Alignment.Center,
                            ) {
                                Box(
                                    Modifier
                                        .size(34.dp)
                                        .clip(CircleShape)
                                        .background(sw)
                                        .border(
                                            width = if (currentAccent.key == a.key) 3.dp else 0.dp,
                                            color = c.textHi,
                                            shape = CircleShape,
                                        ),
                                )
                            }
                        }
                    }
                    Text("카드 밀도", color = c.textHi, fontSize = 13.sp,
                         fontWeight = FontWeight.Bold)
                    SegmentRow(
                        options = Densities.map { it.label to it },
                        selected = currentDensity,
                        onSelect = onDensityChange,
                    )
                }
            }
        }
        item {
            Text(
                text = "프로젝트 대시보드 · Android MVP",
                color = c.subDim,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        color = Board.colors.sub,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.4.sp,
        modifier = Modifier.padding(top = 14.dp, bottom = 2.dp, start = 4.dp),
    )
}

@Composable
private fun CardBox(content: @Composable () -> Unit) {
    val c = Board.colors
    val density = Board.density
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(density.radius))
            .background(c.card)
            .border(1.dp, c.line, RoundedCornerShape(density.radius))
            .padding(density.pad),
    ) {
        content()
    }
}

@Composable
private fun <T> SegmentRow(
    options: List<Pair<String, T>>,
    selected: T,
    onSelect: (T) -> Unit,
) {
    val c = Board.colors
    val accent = Board.accent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(c.surface)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        options.forEach { (label, value) ->
            val active = value == selected
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (active) accent else Color.Transparent)
                    .clickable { onSelect(value) }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    label,
                    color = if (active) c.onAccent else c.sub,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
