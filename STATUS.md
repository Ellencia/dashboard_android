# 프로젝트 보드 (안드로이드)

> Jetpack Compose 기반 모바일 클라이언트. PC 위젯과 STATUS.md/update.md 공유.

- [x] 빌드 환경 셋업 (AGP 8.7 / Kotlin 2.1 / Compose BOM 2024.12)
- [x] Tokyo-Night 테마 토큰 포팅 (Theme.kt, Density.kt, TagPalette.kt)
- [x] 데이터 모델 (Project, Todo, UpdateEntry + 파생 헬퍼)
- [x] Seed 데이터 (시안 data.js 포팅)
- [x] ProgressRing / ProgressBar / TagChip / SyncBadge 컴포넌트
- [x] ProjectCard ring variant
- [x] ProjectListScreen + FAB
- [x] AllTodosScreen (태그 필터, 프로젝트별 그룹)
- [x] SettingsScreen (테마·강조색·밀도 라이브 적용)
- [x] BoardApp 셸 (헤더 + 본문 + 하단 탭바)
- [ ] ProjectCard bar / donut variants
- [ ] ProjectDetail overlay (시안 detail.jsx)
- [ ] Timeline overlay (update.md 시각화)
- [ ] EditSheet 바텀시트 (할 일/프로젝트 추가·편집)
- [ ] SyncSheet + ConflictResolver
- [ ] KakaoSheet (placeholder)
- [ ] Onboarding flow
- [ ] DataStore 선호 저장 (테마/강조색/밀도 영구)
- [ ] NotificationListenerService (카톡 알림 캐치)
- [ ] LLM 추출 (Gemini Flash) → JSON
- [ ] Inbox 큐 + 사용자 컨펌 UI
- [ ] Firestore sync (또는 동등 클라우드)
- [ ] PC ↔ 폰 sync 동작 (PC 의 `_drop/` JSON 포맷과 일치)
- [ ] 홈스크린 위젯 4종 (시안 widget-views.jsx)
- [ ] Launcher 통합 (시안 u-launcher.jsx — Android 에선 실제 launcher 가 아니라 widget provider)
</content>
