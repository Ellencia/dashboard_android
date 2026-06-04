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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.layout.Spacer
import com.stc.dashboard.data.Project
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.barColor
import com.stc.dashboard.theme.soft
import com.stc.dashboard.theme.tagColor
import com.stc.dashboard.ui.cards.TodoRow

/**
 * 시안 AllTodosScreen — 카운트 요약 + 가로 태그 필터 + 프로젝트별 묶음.
 * 태그 필터 클릭 시 해당 태그 가진 할 일만 표시.
 */
@Composable
fun AllTodosScreen(
    projects: List<Project>,
    onToggleTodo: (Project, Int) -> Unit = { _, _ -> },
    onProjectClick: (Project) -> Unit = {},
) {
    val c = Board.colors
    val accent = Board.accent
    val density = Board.density
    var filter by remember { mutableStateOf<String?>(null) }

    val openByProject: List<Pair<Project, List<Pair<Int, com.stc.dashboard.data.Todo>>>> =
        projects.map { p ->
            val pairs = p.todos.withIndex()
                .filter { !it.value.done }
                .filter { filter == null || it.value.tags.contains(filter) }
                .map { it.index to it.value }
            p to pairs
        }.filter { it.second.isNotEmpty() }
    val totalOpen = openByProject.sumOf { it.second.size }

    // 모든 태그 카운트 (미완료 기준)
    val tagCounts = projects.flatMap { it.todos.filter { t -> !t.done } }
        .flatMap { it.tags }.groupingBy { it }.eachCount()
        .entries.sortedByDescending { it.value }

    Column(modifier = Modifier.fillMaxSize()) {
        // 요약
        Text(
            text = "남은 할 일 $totalOpen",
            color = c.sub,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 10.dp),
        )
        // 태그 필터 row
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                FilterChip(label = "▽ 전체", color = accent, active = filter == null,
                           onClick = { filter = null })
            }
            items(tagCounts) { (tag, count) ->
                FilterChip(
                    label = "# $tag · $count",
                    color = tagColor(tag),
                    active = filter == tag,
                    onClick = { filter = if (filter == tag) null else tag },
                )
            }
        }
        Spacer(Modifier.size(14.dp))
        // 프로젝트별 묶음
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(density.cardGap),
        ) {
            if (openByProject.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("🎉", fontSize = 38.sp)
                        Spacer(Modifier.size(10.dp))
                        Text("모든 할 일 완료", color = c.text,
                             fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            openByProject.forEach { (project, todos) ->
                item(key = "group-${project.id}") {
                    Row(
                        modifier = Modifier.clickable { onProjectClick(project) }
                            .padding(top = 4.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                        Box(
                            Modifier.size(7.dp).clip(CircleShape)
                                .background(barColor(project.todos.let {
                                    if (it.isEmpty()) 0 else it.count { t -> t.done } * 100 / it.size
                                }))
                        )
                        Text(
                            text = project.name,
                            color = accent,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = "${todos.size} ›",
                            color = c.subDim,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
                item(key = "card-${project.id}") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(density.radius))
                            .background(c.card)
                            .border(1.dp, c.line, RoundedCornerShape(density.radius))
                            .padding(horizontal = density.pad, vertical = 4.dp),
                    ) {
                        todos.forEach { (originalIndex, todo) ->
                            TodoRow(todo = todo, onToggle = { onToggleTodo(project, originalIndex) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterChip(label: String, color: Color, active: Boolean, onClick: () -> Unit) {
    val c = Board.colors
    val bg = if (active) color.soft(Board.isDark) else c.card
    val fg = if (active) color else c.sub
    val border = if (active) color else c.line
    Text(
        text = label,
        color = fg,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .clip(RoundedCornerShape(99.dp))
            .background(bg)
            .border(1.dp, border, RoundedCornerShape(99.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 7.dp),
    )
}
