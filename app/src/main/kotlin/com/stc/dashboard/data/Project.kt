package com.stc.dashboard.data

/**
 * 시안 data.js 의 Project / Todo / Update 모델.
 * PC 위젯의 STATUS.md / update.md 와 1:1 호환되는 shape.
 */

data class Todo(
    val text: String,
    val tags: List<String> = emptyList(),
    val done: Boolean = false,
    /** ISO YYYY-MM-DD — 없으면 빈 문자열. */
    val due: String = "",
)

data class UpdateEntry(
    val date: String,        // ISO YYYY-MM-DD
    val lines: List<String>, // bullet 한 줄씩
)

data class Project(
    val id: String,
    val name: String,
    val note: String = "",
    val todos: List<Todo> = emptyList(),
    val updates: List<UpdateEntry> = emptyList(),
)

// ---- 파생 헬퍼 (시안 projDone/projTotal/projPercent/projOpen/projLastUpdate) ----

val Project.total: Int get() = todos.size
val Project.done: Int get() = todos.count { it.done }
val Project.open: Int get() = todos.count { !it.done }
val Project.percent: Int
    get() = if (total == 0) 0 else (done * 100 / total)
val Project.lastUpdate: UpdateEntry? get() = updates.firstOrNull()

/** 미완료 할 일 중 가장 임박한 마감일. 없으면 빈 문자열. */
val Project.nearestDue: String
    get() = todos
        .filter { !it.done && it.due.isNotEmpty() }
        .minOfOrNull { it.due }
        ?: ""

/** 이 프로젝트의 모든 태그 → 발생 횟수. */
fun Project.tagCounts(): Map<String, Int> {
    val counts = LinkedHashMap<String, Int>()
    for (todo in todos) {
        if (!todo.done) for (tag in todo.tags) {
            counts[tag] = (counts[tag] ?: 0) + 1
        }
    }
    return counts
}
