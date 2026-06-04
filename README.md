# 프로젝트 보드 (Android)

PC 대시보드의 모바일 클라이언트. Jetpack Compose + Tokyo-Night 테마.
`design_handoff_project_board/` 시안의 React 컴포넌트를 Compose 로 1:1 포팅.

## 빌드/실행

### 필요한 것
- **Android Studio** (Hedgehog 이상 권장 — Iguana / Koala / Ladybug 모두 호환)
  - https://developer.android.com/studio
- **JDK 17** (Android Studio가 번들로 같이 깔아 줌)
- **Android SDK** API 35 (compileSdk), API 26 (minSdk)
  - Android Studio 의 SDK Manager 에서 자동 설치

### 처음 열 때
1. Android Studio → **Open** → 이 폴더(`projectboard_android`) 선택
2. Gradle sync 가 자동 시작됨 (인터넷에서 의존성 받음, 첫 sync 는 5~10분 걸릴 수 있음)
3. sync 완료 후 우측 상단 ▶ 버튼 → 폰(USB 디버깅 켠) 또는 에뮬레이터 선택 → 실행

### 폰 에뮬레이터 빠른 셋업
- Android Studio → **Tools → Device Manager → Create Device**
- Phone → Pixel 7 → Next → System Image (API 35) → Download → Finish

## 구조

```
app/src/main/kotlin/com/stc/projectboard/
├── MainActivity.kt                  — entry
├── theme/
│   ├── Theme.kt                     — BoardColors, BoardTheme, Accent, barColor, progressTone
│   ├── Density.kt                   — compact/cozy/roomy
│   └── TagPalette.kt                — tagColor() hash 매핑
├── data/
│   ├── Project.kt                   — Project / Todo / UpdateEntry + 파생 헬퍼
│   └── SeedData.kt                  — 시안 SEED PROJECTS 포팅
└── ui/
    ├── BoardApp.kt                  — 헤더 + 본문 + 탭바 셸 (단일 상태)
    ├── components/
    │   ├── ProgressRing.kt
    │   ├── ProgressBar.kt
    │   ├── TagChip.kt
    │   └── SyncBadge.kt
    ├── cards/
    │   ├── ProjectCard.kt           — ring variant
    │   └── TodoRow.kt
    └── screens/
        ├── ProjectListScreen.kt
        ├── AllTodosScreen.kt
        └── SettingsScreen.kt
```

## 현재 상태 (MVP)

**작동하는 것**
- 시안 디자인 토큰 100% 포팅 (Tokyo-Night 다크/라이트, 4 강조색, 3 밀도)
- Project / Todo 데이터 모델 + Seed 6 프로젝트
- 3 탭 셸: 프로젝트 / 할 일 / 설정 — 라이브 recolor 작동
- 프로젝트 리스트 (ring 카드) + 요약 + FAB
- 모든 할 일 (가로 태그 필터 + 프로젝트별 묶음, 체크/언체크 토글)
- 설정 (다크/라이트 토글, 4색 swatch, 3밀도 — 모두 라이브)

**다음 단계**
- ProjectDetail 오버레이 (카드 탭 → 상세, 시안 detail.jsx)
- 바텀시트 (할 일/프로젝트 추가·편집, 시안 sheets.jsx)
- DataStore 로 선호 영구 저장
- 카톡 NotificationListener + LLM 통합
- PC 대시보드와 sync (PC 의 `_drop/*.json` 포맷)

자세한 진행 상황은 [STATUS.md](STATUS.md), 변경 이력은 [update.md](update.md).
</content>
