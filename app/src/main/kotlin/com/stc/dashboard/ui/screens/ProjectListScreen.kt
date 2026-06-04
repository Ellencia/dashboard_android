package com.stc.dashboard.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.data.Project
import com.stc.dashboard.data.open
import com.stc.dashboard.theme.Board
import com.stc.dashboard.ui.cards.ProjectCard

/**
 * 시안 ProjectListScreen — 한 줄 요약 + 카드 스택 + FAB.
 */
@Composable
fun ProjectListScreen(
    projects: List<Project>,
    onProjectClick: (Project) -> Unit = {},
    onAddProject: () -> Unit = {},
) {
    val c = Board.colors
    val accent = Board.accent
    val density = Board.density
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp, end = 16.dp,
                top = 6.dp, bottom = 92.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(density.cardGap),
        ) {
            item {
                val totalOpen = projects.sumOf { it.open }
                Text(
                    text = buildAnnotatedString {
                        append("프로젝트 ")
                        withStyle(SpanStyle(color = c.textHi, fontWeight = FontWeight.Bold)) {
                            append("${projects.size}")
                        }
                        append("  ·  남은 할 일 ")
                        withStyle(SpanStyle(color = c.textHi, fontWeight = FontWeight.Bold)) {
                            append("$totalOpen")
                        }
                    },
                    color = c.sub,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
            items(projects, key = { it.id }) { project ->
                ProjectCard(project = project, onClick = { onProjectClick(project) })
            }
        }
        // FAB
        FloatingActionButton(
            onClick = onAddProject,
            containerColor = accent,
            contentColor = c.onAccent,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(54.dp),
        ) {
            Icon(Icons.Outlined.Add, contentDescription = "새 프로젝트")
        }
    }
}
