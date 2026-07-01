# SevMerge - IT 외주 매칭 플랫폼

IT 프로젝트를 진행하고 싶은 의뢰인과, 그 프로젝트를 수행할 전문가를 연결해주는 외주 매칭 플랫폼입니다.
단순히 프로필과 연락처를 나열하는 수준을 넘어, 프로젝트 등록부터 제안서 제출, 계약, 에스크로 결제, 작업 진행, 정산, 리뷰까지 거래의 전 과정을 하나의 서비스 안에서 처리할 수 있도록 설계했습니다.
실시간 채팅과 알림으로 의뢰인과 전문가가 원활하게 소통할 수 있게 했고, AI 의뢰 작성 도우미를 붙여 프로젝트 설명을 작성하는 진입장벽도 낮췄습니다.

## 프로젝트 개요

| 항목 | 내용 |
|---|---|
| 프로젝트 유형 | 개인 프로젝트 (백엔드 중심 풀스택) |
| 개발 기간 | 2026.05 ~ 2026.06 (약 4주) |
| 핵심 가치 | 안전한 외주 거래 · 에스크로 결제 · 실시간 소통 · AI 작성 보조 |
| 아키텍처 | Spring Boot 모놀리식 + Mustache 서버사이드 렌더링(SSR) |
| Repository | https://github.com/bin1998-git/SevMerge |

### 해결하고자 하는 문제

기존 외주 플랫폼에서는 다음과 같은 문제가 발생합니다.

- 의뢰인이 수많은 전문가를 직접 탐색·연락해야 하는 비효율적 매칭 구조
- 선불 결제 후 작업 미완료 시 환불이 어려운 거래 신뢰 문제
- 신규 전문가의 실력 파악이 어려운 정보 비대칭 문제

SevMerge는 프로젝트 등록, 제안서 비교, 계약, 결제, 작업 진행, 정산까지 이어지는 흐름을 하나의 시스템으로 통합하고, 플랫폼이 대금을 보관했다가 작업 완료 시 정산하는 에스크로 구조를 통해 위 문제를 해결합니다.


### 제공하는 가치

- **의뢰인**: 제안서 비교를 통한 합리적 전문가 선택, 에스크로 결제를 통한 거래 안심
- **전문가**: 등급 인증을 통한 실력 어필, 광고 입찰을 통한 노출 확대
- **플랫폼**: 수수료 기반 안정적 수익 구조, 통계 기반 운영 관리

## 기술 스택

### Backend
- Spring Boot
- Java
- Spring Data JPA
- Spring Security (BCrypt)
- Spring WebSocket
- Spring AI (Google GenAI / Gemini)
- MySQL
- Gradle

### Frontend
- Mustache
- CSS (Custom, Glassmorphism)
- JavaScript (Vanilla)
- Chart.js

### External API
- Toss Payments (결제)
- SolAPI (SMS)
- Gmail SMTP (이메일)
- Google Gemini (AI 의뢰 작성 보조)

### Tools
- IntelliJ IDEA
- Git

## 프로젝트 구조

### 도메인 기반 패키지 구조 (Domain-Driven Package Structure)

본 프로젝트는 유지보수성과 확장성을 극대화하기 위해 도메인 기반 패키지 구조를 채택하였습니다.

- 도메인 중심 응집: 각 비즈니스 기능(제안서, 결제, 회원 등)별로 관련 코드를 모아 응집도를 높였습니다.
- 관심사 분리: 기술적 공통 설정(core)과 비즈니스 로직을 분리하여 코드의 가독성을 확보했습니다.

```
📦com.example.SevMerge
 ┣ 📂admin              # 관리자 기능 (회원, 신고, 시스템 관리 및 통계그래프)
 ┣ 📂advertisement       # 광고 관리 (플랫폼 내 배너 광고 및 마케팅 관리)
 ┣ 📂ai                  # AI 연동 기능 (AI 추천 알고리즘 및 자동화 처리)
 ┣ 📂bid                 # 입찰 시스템 (전문가와 프로젝트 매칭을 위한 비딩 기능)
 ┣ 📂board               # 자유 게시판 및 커뮤니티 기능
 ┣ 📂bookmark            # 북마크/즐겨찾기 (관심 전문가 또는 프로젝트 저장)
 ┣ 📂charge              # 포인트 충전 및 결제 시스템 관련 처리
 ┣ 📂chatMessage         # 실시간 채팅 메시지 송수신 데이터 처리
 ┣ 📂chatRoom            # 채팅방 생성, 유지 및 목록 관리
 ┣ 📂comment             # 게시글 내 댓글 관리
 ┣ 📂core                # 공통 핵심 로직 (보안 설정, 예외 처리, 글로벌 유틸리티, 인터셉터)
 ┣ 📂expertprofile       # 전문가 프로필 관리 (기술 스택, 포트폴리오, 이력)
 ┣ 📂expertwish          # 전문가 찜하기 및 선호 전문가 관리
 ┣ 📂faq                 # 자주 묻는 질문(FAQ) 및 고객 지원 센터
 ┣ 📂footer              # 하단 영역 정보 관리 (약관, 사업자 정보 등)
 ┣ 📂member              # 일반 회원 관리 (회원가입, 로그인, 마이페이지)
 ┣ 📂message             # 시스템 쪽지 및 다이렉트 메시지(DM) 기능
 ┣ 📂notification        # 알림 서비스 (SSE, 푸시 알림, 문자메시지, 이메일 발송)
 ┣ 📂partnership         # 제휴 및 비즈니스 협력 관련 관리
 ┣ 📂payment             # 결제 API 연동 및 결제 내역 검증
 ┣ 📂portfolio           # 전문가 포트폴리오 등록 및 조회
 ┣ 📂project             # 프로젝트/외주 의뢰 등록 및 매칭 관리
 ┣ 📂refund              # 취소 및 환불 요청 처리 로직
 ┣ 📂Report              # 신고 시스템 (악성 유저, 불법 게시글 필터링)
 ┣ 📂review              # 서비스 이용 후기 및 별점 평점 관리
 ┣ 📂withdrawal          # 회원 탈퇴 및 데이터 파기/비활성화 처리
 ┣ 📂main                # 메인 페이지 및 엔트리 포인트 제어
 ┗ 📜SevMergeApplication # 스프링 부트(Spring Boot) 애플리케이션 메인 실행 클래스
```

리소스 및 빌드 구성은 다음과 같습니다.

```
📦SevMerge
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃   ┗ 📂example
 ┃ ┃ ┃     ┗ 📂SevMerge          # 위 도메인 패키지
 ┃ ┃ ┣ 📂resources
 ┃ ┃ ┃ ┣ 📂db                    # 초기 데이터 (data.sql)
 ┃ ┃ ┃ ┣ 📂static                # 정적 리소스 (CSS, JS, 이미지)
 ┃ ┃ ┃ ┣ 📂templates             # Mustache 템플릿
 ┃ ┃ ┃ ┗ 📜application*.yml
 ┃ ┗ 📂test
 ┣ 📜.env                        # 환경 변수 파일 (프로젝트 루트에 생성 필요)
 ┣ 📜build.gradle
 ┣ 📜gradlew.bat
 ┗ 📜gradlew
```

## 기능 구분

### 의뢰인 (Client)
- 프로젝트 등록 (AI 작성 보조), 프로젝트 수정/삭제/임시저장
- 제안서 비교 및 낙찰 처리
- 에스크로 결제, 잔액 충전 (Toss), 정산 승인
- 환불 신청, 리뷰 작성
- 실시간 채팅, 알림, 쪽지함
- 전문가 북마크, 찜하기

### 전문가 (Expert)
- 관리자 심사를 거친 인증 전문가 활동
- 베이지안 평균 기반 등급 산정 (NORMAL / SKILLED / MASTER)
- 프로젝트 탐색 및 제안서 제출
- 포트폴리오 등록/관리
- 작업 진행 상태 관리, 산출물 제출
- 정산금 출금, 광고 슬롯 입찰
- 실시간 채팅, 알림, 쪽지함

### 관리자 (Admin)
- 대시보드 통계 (Chart.js 기반 월별 매출, 카테고리별 프로젝트, 등급 비율)
- 전문가 심사 및 승인
- 회원 관리, 신고 처리, 블랙리스트 관리 (누적 3회 자동 정지)
- 환불 분쟁 처리
- 광고 승인, 광고 경매 관리
- 공지사항, FAQ, 제휴 문의 관리

## ERD 및 도메인 모델

| 엔티티 | 설명 | 핵심 상태/필드 |
|---|---|---|
| Member | 회원 | role(CLIENT/EXPERT/ADMIN), status(ACTIVE/PENDING/REJECTED/SUSPENDED/BLACKLISTED), balance |
| ExpertProfile | 전문가 프로필 | isCertified, grade(NORMAL/SKILLED/MASTER) |
| Project | 의뢰 | status(OPEN/IN_PROGRESS/DONE/CANCELLED 등), category, bidFilter |
| Bid | 제안서 | status(PENDING/SELECTED/HOLD/REJECTED), 제안 금액/기간 |
| Payment | 에스크로 | status(PAID/SETTLED/REFUNDED), platformFee, netAmount |
| Review / Notification | 리뷰 / 알림 | 별점, 읽음 여부 (30일 후 자동 정리) |
| Report / BlackList | 신고 / 제재 | 누적 3회 자동 정지 |

## 핵심 플로우

- 외주 매칭 + 에스크로 흐름: 프로젝트 등록 → 전문가 제안서 제출 → 의뢰인 낙찰 선택 → 에스크로 결제 → 작업 진행 → 산출물 제출 → 검수 완료 → 정산
- 상태 머신: Bid(PENDING → SELECTED/HOLD/REJECTED), Payment(PAID → SETTLED/REFUNDED)

## 기술적 하이라이트

### 1. 에스크로 - 동시성을 고려한 원자적 잔액 처리
낙찰 시 의뢰인 잔액을 에스크로로 묶을 때, 조건부 UPDATE 한 방으로 잔액 검증과 차감을 원자적으로 처리해 동시 요청에서의 이중 차감을 방지합니다.

```java
// PaymentService.createEscrow — 잔액이 충분할 때만 차감되는 원자적 UPDATE
int updated = em.createQuery(
    "UPDATE Member m SET m.balance = m.balance - :amount " +
    "WHERE m.id = :id AND m.balance >= :amount")
    .setParameter("amount", amount)
    .setParameter("id", clientId)
    .executeUpdate();

if (updated == 0) throw new BadRequestException("잔액 차감에 실패했습니다.");
```

### 2. 결제 보안 - 서버 사이드 Toss 승인
클라이언트가 아닌 서버에서 Toss confirm API를 직접 호출해 결제 금액 위변조를 방지합니다.

### 3. 전문가 등급 - 베이지안 평균으로 별점 왜곡 보정
리뷰가 적은 신규 전문가의 별점 왜곡을 완화하기 위해 전체 평균을 사전(prior)으로 활용하는 베이지안 평균을 적용합니다.

### 4. 다단 인터셉터 기반 인가 및 Rate Limiting
Session / Login / Admin / Project / Bid 5종 인터셉터와 RateLimitFilter를 계층적으로 적용해 권한과 요청 빈도를 제어합니다.

### 5. 알림 및 외부연동의 트랜잭션 분리
SMS, 이메일, SSE 알림 등 외부 연동을 트랜잭션 커밋 이후에 실행해 롤백 시 불필요한 외부 호출을 방지합니다.

## 실행 방법

### 사전 요구사항
- JDK 21 이상
- MySQL 8.0 이상
- Gradle 8.x 이상

### 환경 변수 설정

프로젝트 루트에 `.env` 파일을 생성하고 다음 환경 변수를 설정하세요.

```env
DB_USERNAME=root
DB_PASSWORD=

GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=

KAKAO_CLIENT_ID=
KAKAO_CLIENT_SECRET=

TOSS_CLIENT_KEY=
TOSS_SECRET_KEY=

MAIL_USERNAME=
MAIL_PASSWORD=

SOLAPI_KEY=
SOLAPI_SECRET_KEY=
SOLAPI_SENDER_NUMBER=

GEMINI_API_KEY=
```

### 빌드 및 실행

```bash
# 프로젝트 클론
git clone https://github.com/bin1998-git/SevMerge.git
cd SevMerge

# MySQL DB 생성
# CREATE DATABASE sevmerge DEFAULT CHARACTER SET utf8mb4;

# 애플리케이션 실행 (Windows: gradlew.bat bootRun)
./gradlew bootRun
```

실행 후 http://localhost:8080 접속 (초기 구동 시 `db/data.sql` 시드 데이터 자동 적재)

## URL 매핑

### 제안서 관리

| HTTP Method | URL | 설명 | 권한 | 비고 |
|---|---|---|---|---|
| GET | `/bids/save-form` | 제안서 등록 폼 | 전문가 | - |
| POST | `/bids` | 제안서 등록 | 전문가 | - |
| GET | `/bids` | 제안서 목록 조회 (의뢰인) | 로그인 필요 | - |
| GET | `/bids/my-list` | 내 제안서 목록 | 전문가 | - |
| GET | `/bids/my-orders` | 주문 관리 | 전문가 | - |
| GET | `/bids/{id}/edit` | 제안서 수정 폼 | 전문가 | - |
| PUT | `/bids/{id}` | 제안서 수정 (API) | 전문가 | - |
| DELETE | `/bids/{id}` | 제안서 취소/삭제 (API) | 전문가 | - |
| POST | `/bids/{id}/select` | 낙찰 처리 (API) | 의뢰인 | - |
| POST | `/bids/{id}/hold` | 제안서 보류 처리 (API) | 의뢰인 | - |
| DELETE | `/bids/{id}/reject` | 제안서 거절 (API) | 의뢰인 | - |
| POST | `/bids/{id}/complete` | 작업 완료 신고 (API) | 전문가 | - |
| PATCH | `/bids/{id}/work-status` | 작업 상태 변경 (API) | 전문가 | - |
| POST | `/bids/{id}/submit-work` | 작업물 파일 제출 (API) | 전문가 | - |

### 프로젝트 관리

| HTTP Method | URL | 설명 | 권한 | 비고 |
|---|---|---|---|---|
| GET | `/projects/save-form` | 프로젝트 등록 폼 | 의뢰인 | - |
| POST | `/projects/save` | 프로젝트 등록 | 의뢰인 | - |
| GET | `/projects` | 프로젝트 목록 조회 | 전체 | 필터/검색/페이징 |
| GET | `/projects/{id}` | 프로젝트 상세 조회 | 전체 | - |
| GET | `/projects/{id}/edit` | 프로젝트 수정 폼 | 의뢰인 | - |
| PUT | `/projects/{id}` | 프로젝트 수정 (API) | 의뢰인 | - |
| DELETE | `/projects/{id}` | 프로젝트 삭제 (API) | 의뢰인 | - |
| POST | `/projects/{id}/done` | 검토 확인 및 정산 승인 | 의뢰인 | - |
| POST | `/projects/draft` | 프로젝트 임시저장 (API) | 의뢰인 | - |
| GET | `/projects/draft` | 임시저장 데이터 조회 (API) | 의뢰인 | - |
| POST | `/projects/{id}/skip-review` | 리뷰 건너뛰기 | 의뢰인 | - |
| GET | `/admin/projects` | 관리자 프로젝트 목록 | 관리자 | - |
| POST | `/admin/projects/{id}/delete` | 관리자 전용 삭제 | 관리자 | - |

### 북마크

| HTTP Method | URL | 설명 | 권한 | 비고 |
|---|---|---|---|---|
| POST | `/bookmarks/toggle/{expertId}` | 북마크 토글 (API) | 로그인 필요 | - |
| POST | `/bookmarks/{expertId}` | 북마크 삭제 | 로그인 필요 | - |

## 주요 기술 특징

### 보안
- Spring Security 기반 세션 인증
- 인터셉터를 통한 접근 제어 (Session / Login / Admin / Project / Bid)
- CSRF 토큰 자동 주입 (form 기반 요청)
- 역할별 기능 분리 (CLIENT / EXPERT / ADMIN)

### 예외 처리
- 커스텀 예외 클래스 (BadRequestException 등)
- 전역 예외 처리
- API 요청과 페이지 요청을 구분한 응답 형식 처리 필요성 확인 및 개선

### 데이터베이스
- JPA를 통한 ORM
- `JOIN FETCH`를 통한 `LazyInitializationException` 해결
- 트랜잭션 관리
- 조건부 UPDATE를 통한 동시성 제어 (에스크로 잔액 차감)

### 파일 업로드
- 이미지 파일 업로드 지원 (포트폴리오, 작업 산출물)
- MultipartFile → UUID 파일명 변환 → 디스크 저장
- WebMvcConfig를 통한 정적 리소스 노출

## 커밋 컨벤션

```
<타입>(<스코프>): <제목>
```

- feat: 새 기능
- fix: 버그 수정
- refactor: 리팩터링
- docs: 문서
- style: 스타일
- test: 테스트
- chore: 빌드/기타

## 개발자

**준현**
- Portfolio 도메인 전체 구현 (CRUD, 파일 업로드, 이미지 URL 정규화)
- Review 도메인 구현 (엔티티, 리포지토리, 평점 재계산 로직)
- 북마크 토글 기능 (fetch 기반 비동기 처리)
- 1:1 문의 게시판 (범위 기반 필터링)
- 헤더/푸터 등 공통 레이아웃 및 글래스모피즘 디자인 시스템 구축
- Mustache 템플릿 다수 페이지 작업 (FAQ, 지원, 회사소개 등)

---

SevMerge © 2026
