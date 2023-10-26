# 팀소개
'개발천국'

저희의 팀명은 개발천국입니다. 

저희가 있는 지금 이세상은 개발을 위한 아이디어들로 가득 차 있기에 다양한곳에서 개발 아이디어를 얻자해서 개발천국으로 짓게 되었습니다.

저희는 팀구성원 개인들의 아이디어와 의견들을 자유롭게 나누고 소통하며, 팀으로써 하나의 목표달성을 위해 열심히 노력합니다.

앞으로도 꾸준히 성장하는 개발천국이 되겠습니다.

# 팀원소개

- Back-end Developer 송승환 (팀장)
  - 일정등록 / 친구추가 기능 담당
- Back-end Developer 강경구
  - 게시판/채팅 기능 담당
- Back-end Developer 전원식
- Back-end Developer 김현정
  - 회원가입 / 로그인 기능 담당
  - 노션 관리


# 프로젝트 소개
'YouAndMe Schedule'
'유앤미 스케줄'

  헬스, 필라테스, 수영 등 여러 운동 종목의 트레이너분들이 회원과 함께 사용할 수 있는 스케쥴링 서비스 이다.<br/> 
'유앤미 스케줄'을 통해 트레이너와 회원 간의 수업 일정을 편리하게 관리할 수 있다.

 또한 '유앤미 스케줄'을 통해 직접 센터를 방문하지 않아도 수업에 대한 정보를 확인할 수 있고 수업 등록까지 가능하다.<br/> 
이를 통해 트레이너는 자신의 수업에 대한 정보를 노출 시켜 홍보 효과를 얻을 수 있다. 


# 프로젝트 기획 배경

- 트레이너가 수많은 회원들의 수업 시간을 편리하게 관리할 수 있도록 하기 위해서 서비스를 기획하였다.
- 개인적으로 연락을 주고 받아야 하는 경우가 많으며 수업이 많은 경우 쉬는 날까지도 연락을 통해 일종의 ‘업무’를 해야한다.
- 이에 대해 좀 더 편리함을 제공하기 위해 서비스를 기획하였다.

# 프로젝트 목표

센터,회원,강사간의 운동 스케줄링 시스템을 제공하고 수업을 등록할 수 있는 서버를 구축합니다.

# 사용 기술스택

- Language : Java11
- Framework : SpringBoot 2.7.16
- Database : Mariadb, Redis
- Docker, AWS
- IDE : IntelliJ
- Build: Gradle

# 프로젝트 기능

- 회원가입
    - 회원가입 시 강사, 트레이너 / 일반 중 선택하여 가입을 진행한다.
    - 강사, 트레이너
        - 자신에 대한 소개글을 작성한다.
- 로그인
    - id와 패스워드를 이용해 로그인 합니다.
    - 로그인 시 가입되어 있지 않은 id이거나 id와 패스워드가 일치하지 않을 때 에러를 발생한다.
- 수업 등록 기능
    - 사용자는 등록되어 있는 여러 트레이너의 수업 정보를 확인할 수 있다.
    - 마음에 드는 수업에 대해 온라인으로 등록이 가능하다.
    - 수업료에 대한 결제는 서비스상의 ‘지갑’으로 이루어 진다.
- 예약 기능
    - 트레이너는
        - 수업이 가능한 시간대를 설정해둔다.
    - 회원은
        - 수업을 원하는 시간대를 선택해 수업 신청을 한다.
    - 트레이너는 해당 신청에 대해 수락 / 거절을 할 수 있다.
    - 수락 시 트레이너와 회원의 캘린더에 해당 수업 시간이 자동으로 등록된다.
- 친구 추가 기능
    - 트레이너 - 회원 또는 모든 사용자들 간에 친구 추가를 할 수 있다.
    - id, 닉네임을 통해 검색할 수 있다.
- 알림 전송 기능
    - 트레이너가 수업 신청을 수락 / 거절 시 회원에게 푸쉬 알림이 발송 된다.
    - 수업 하루 전날에 수업에 대한 리마인드 알림이 자동으로 전송된다.
        - 예) 9/30 토요일 수업인 회원에게 9/29일 알림이 발송된다.
        알림 내용 : ’내일 18:00 PT수업 일정이 등록되어 있음을 알려드립니다 ! “

- 채팅 기능(추후 구현 예정)
    - 친구로 등록되어 있을 경우 1:1 채팅이 가능하다.
      
- 게시판 기능
    - 파일 첨부를 포함한 기본 글 작성/조회/수정 삭제
    - 게시물 페이징 처리 및 최신 순으로 정렬
    - 검색 기능

# ERD  

![image](https://github.com/DevSanctuary/youandme_scheduling/assets/127717572/5b9a0fff-fafb-4222-9182-a39adad5d842)
