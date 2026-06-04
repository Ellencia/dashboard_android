package com.stc.dashboard.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.data.Project
import com.stc.dashboard.data.done
import com.stc.dashboard.data.lastUpdate
import com.stc.dashboard.data.open
import com.stc.dashboard.data.percent
import com.stc.dashboard.data.tagCounts
import com.stc.dashboard.data.total
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.barColor
import com.stc.dashboard.theme.progressTone
import com.stc.dashboard.ui.components.ProgressRing
import com.stc.dashboard.ui.components.TagChip

/**
 * 시안 ProjectCard — ring variant 기본.
 *
 * 구조: [ProgressRing 50] [이름 · 완료/전체 · 남은] | 메모 | 태그 칩들 | 최근 변경.
 */
@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val density = Board.density
    val c = Board.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(density.radius))
            .clip(RoundedCornerShape(density.radius))
            .background(c.card)
            .border(1.dp, c.line, RoundedCornerShape(density.radius))
            .clickable(onClick = onClick)
            .padding(density.pad),
        verticalArrangement = Arrangement.spacedBy(density.pad - 6.dp),
    ) {
        // 윗줄: ring + 제목 + 완료/전체
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(density.pad - 4.dp),
        ) {
            ProgressRing(
                percent = project.percent,
                size = if (density.compact) 50.dp else 58.dp,
                stroke = 6.dp,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = project.name,
                    color = c.textHi,
                    fontSize = if (density.compact) 15.5.sp else 16.5.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Box(
                        Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(barColor(project.percent)),
                    )
                    Text(
                        text = "${progressTone(project.percent)} · 완료",
                        color = c.sub,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "${project.done}/${project.total}",
                        color = c.text,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " · 남은 ${project.open}",
                        color = c.sub,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
        // 메모 (compact 모드 아닐 때만)
        if (!density.compact && project.note.isNotEmpty()) {
            Text(
                text = project.note,
                color = c.sub,
                fontSize = 13.sp,
            )
        }
        // 태그 칩들 (최대 4개)
        val tagCounts = project.tagCounts()
        if (tagCounts.isNotEmpty()) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                tagCounts.entries.take(4).forEach { (tag, count) ->
                    TagChip(tag = tag, count = count)
                }
            }
        }
        // 최근 변경
        project.lastUpdate?.let { upd ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Icon(
                    Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = c.subDim,
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = upd.date,
                    color = c.subDim,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = upd.lines.firstOrNull() ?: "",
                    color = c.sub,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
