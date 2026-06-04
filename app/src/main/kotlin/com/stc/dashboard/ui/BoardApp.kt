package com.stc.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stc.dashboard.data.Project
import com.stc.dashboard.data.SeedProjects
import com.stc.dashboard.data.Todo
import com.stc.dashboard.theme.Accent
import com.stc.dashboard.theme.Accents
import com.stc.dashboard.theme.Board
import com.stc.dashboard.theme.BoardDensity
import com.stc.dashboard.theme.BoardTheme
import com.stc.dashboard.theme.Densities
import com.stc.dashboard.ui.components.SyncBadge
import com.stc.dashboard.ui.components.SyncState
import com.stc.dashboard.ui.screens.AllTodosScreen
import com.stc.dashboard.ui.screens.ProjectListScreen
import com.stc.dashboard.ui.screens.SettingsScreen

/**
 * 시안 BoardApp — 헤더 + 본문 + 탭바.
 * 단일 source of truth (projects + 선호 설정) 로 라이브 recolor 동작.
 */

enum class Tab(val label: String, val icon: ImageVector) {
    Projects("프로젝트", Icons.Outlined.GridView),
    Todos("할 일", Icons.Outlined.Checklist),
    Settings("설정", Icons.Outlined.Settings),
}

@Composable
fun BoardApp() {
    // ----- 전역 상태 -----
    var isDark by remember { mutableStateOf(true) }
    var accent by remember { mutableStateOf(Accents[0]) }
    var density by remember { mutableStateOf(Densities[1]) }
    var projects by remember { mutableStateOf(SeedProjects) }
    var tab by remember { mutableStateOf(Tab.Projects) }

    BoardTheme(isDark = isDark, accent = accent, density = density) {
        val c = Board.colors
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(c.bg)
                .statusBarsPadding(),
        ) {
            // 헤더
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = when (tab) {
                        Tab.Projects -> "프로젝트 보드"
                        Tab.Todos -> "할 일"
                        Tab.Settings -> "설정"
                    },
                    color = c.textHi,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.weight(1f),
                )
                SyncBadge(state = SyncState.Synced)
            }
            // 본문
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                when (tab) {
                    Tab.Projects -> ProjectListScreen(
                        projects = projects,
                        onProjectClick = { /* TODO ProjectDetail */ },
                        onAddProject = { /* TODO EditSheet */ },
                    )
                    Tab.Todos -> AllTodosScreen(
                        projects = projects,
                        onToggleTodo = { proj, idx ->
                            projects = projects.map { p ->
                                if (p.id == proj.id) {
                                    val newTodos = p.todos.toMutableList()
                                    val t = newTodos[idx]
                                    newTodos[idx] = t.copy(done = !t.done)
                                    p.copy(todos = newTodos)
                                } else p
                            }
                        },
                    )
                    Tab.Settings -> SettingsScreen(
                        isDark = isDark,
                        onDarkChange = { isDark = it },
                        currentAccent = accent,
                        onAccentChange = { accent = it },
                        currentDensity = density,
                        onDensityChange = { density = it },
                    )
                }
            }
            // 탭바
            BottomTabBar(current = tab, onTabSelected = { tab = it })
        }
    }
}

@Composable
private fun BottomTabBar(current: Tab, onTabSelected: (Tab) -> Unit) {
    val c = Board.colors
    val accent = Board.accent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(c.surface)
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Tab.values().forEach { tab ->
            val active = tab == current
            Column(
                modifier = Modifier
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = 22.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                Icon(
                    tab.icon,
                    contentDescription = tab.label,
                    tint = if (active) accent else c.subDim,
                    modifier = Modifier.size(22.dp),
                )
                Text(
                    text = tab.label,
                    color = if (active) accent else c.subDim,
                    fontSize = 11.sp,
                    fontWeight = if (active) FontWeight.Bold else FontWeight.SemiBold,
                )
            }
        }
    }
}
