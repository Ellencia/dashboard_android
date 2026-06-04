package com.stc.dashboard.data

/**
 * 시안 data.js 의 SEED PROJECTS — 처음 실행 시 표시할 샘플.
 * 추후 PC 워크스페이스 sync 가 붙으면 이걸 대체.
 */
val SeedProjects: List<Project> = listOf(
    Project(
        id = "buyflow",
        name = "Buyflow · 구매요청 워크플로",
        note = "사내 구매요청–승인–발주 한 흐름으로. 베타 배포 준비 중.",
        todos = listOf(
            Todo("승인 단계 알림 푸시 연동", listOf("기능")),
            Todo("발주서 PDF 자동 생성 템플릿", listOf("발주")),
            Todo("반려 사유 필수 입력 검증", listOf("UI")),
            Todo("발주 요청자 권한 점검", listOf("기능"), done = true),
            Todo("Slack 채널 알림", listOf("기능"), done = true),
            Todo("승인자 캐시 무효화", listOf("기능"), done = true),
        ),
        updates = listOf(
            UpdateEntry("2026-05-28", listOf("승인 라인 다단계 분기 로직 완성")),
            UpdateEntry("2026-05-22", listOf("PR 분리 발주 명세 작성")),
        ),
    ),
    Project(
        id = "pps",
        name = "PPS · 공공조달 자문 RAG",
        note = "나라장터 공고/규정 문서를 근거로 답하는 자문 봇.",
        todos = listOf(
            Todo("답변 캐싱 (자주 묻는 질문)", listOf("API")),
            Todo("근거 문서 하이라이트 표시", listOf("UI")),
            Todo("오래된 공고 자동 필터", listOf("기능")),
            // done
            Todo("벡터 인덱스 v2 마이그레이션", listOf("API"), done = true),
            Todo("기관별 필터", listOf("기능"), done = true),
            Todo("응답 시간 평균 30% 단축", listOf("API"), done = true),
            Todo("관리자 대시보드 통계", listOf("UI"), done = true),
            Todo("프롬프트 인젝션 방어", listOf("API"), done = true),
            Todo("RAG 평가셋 구축", listOf("API"), done = true),
            Todo("토큰 사용량 알림", listOf("API"), done = true),
            Todo("DOC/HWP 파서 보강", listOf("기능"), done = true),
        ),
        updates = listOf(
            UpdateEntry("2026-05-27", listOf("근거 문서 하이라이트 시안 검토")),
        ),
    ),
    Project(
        id = "board",
        name = "프로젝트 대시보드",
        note = "위젯·트레이 모드 가동 중. 카톡 공유 → 자동 추가가 다음 단계.",
        todos = listOf(
            Todo("카톡 공유 → LLM 추출 → 컨펌 → 자동 추가", listOf("다음단계")),
            // done
            Todo("STATUS.md 파싱 + 진행률", done = true),
            Todo("위젯 모드 UI", done = true),
            Todo("자동 새로고침", done = true),
            Todo("update.md 변경 이력", done = true),
            Todo("프로젝트 단위 표시/숨김", done = true),
            Todo("트레이 아이콘 모드", done = true),
        ),
        updates = listOf(
            UpdateEntry("2026-05-30", listOf("안드로이드 연동 MVP UI 설계 착수")),
        ),
    ),
    Project(
        id = "cad",
        name = "MCP-cad · 도면 검수",
        note = "DWG/DXF 도면에서 BOM·치수 자동 추출.",
        todos = listOf(
            Todo("범례 자동 인식", listOf("기능")),
            Todo("치수 단위 통일", listOf("기능")),
            Todo("BOM 엑셀 export 포맷", listOf("UI")),
            // done
            Todo("도면 파서 v1", done = true),
            Todo("타이틀블록 추출", done = true),
        ),
        updates = listOf(
            UpdateEntry("2026-05-20", listOf("범례 라이브러리 초안")),
        ),
    ),
    Project(
        id = "downloads",
        name = "Downloads 정리",
        note = "월별 자동 분류 + 중복 제거.",
        todos = listOf(
            Todo("월별 폴더 자동 생성", listOf("기능")),
            Todo("중복 파일 해시 비교", listOf("기능")),
            Todo("스크린샷 별도 폴더", listOf("UI")),
            Todo("실행 결과 토스트", listOf("UI"), done = true),
        ),
        updates = emptyList(),
    ),
    Project(
        id = "buyflow2",
        name = "구매팀 정산 자동화",
        note = "월말 마감 자동화.",
        todos = listOf(
            Todo("엑셀 양식 파싱", listOf("기능")),
            Todo("입금 매칭 룰", listOf("기능")),
        ),
        updates = emptyList(),
    ),
)
