<div align="center">
<h1>Toy Project - Bulletin Board</h1>
<p>
이 프로젝트는 JPA와 Spring Security를 이용한 간단한 게시판 웹 애플리케이션입니다.<br>
JPA를 사용하여 연관 관계에 있는 여러 테이블의 데이터를 객체로 묶어서 CRUD 연산을 수행하는 간단한 Spring boot 프로젝트입니다.<br>
</p>
</div>
<br/>

## Index
- [Project Overview](#Project-Overview)
- [Project Description](#Project-Description)
- [ER Diagram](#ER-Diagram)
- [Security](#Security)
- [Problems and Solutions](#Problems-and-Solutions)
  <br/>

## Project Overview
- 프로젝트명: JPA와 Spring Security를 활용한 기본적인 게시판 웹 애플리케이션 <a href="http://54.180.82.38:8086/" style="font-size: 15px">[해당 프로젝트 바로 가기]</a>
- 프로젝트 기간: 2024.03.03-2024.03.19
- 프로젝트 기술:
    + Backend<br>
      <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a> <span>&nbsp;- OpenJDK 17</span><br/>
      <a href="https://mariadb.org/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/mariadb/mariadb-icon.svg" alt="mariadb" width="20" height="20"/> </a> <span>&nbsp;- MariaDB v11.3.2</span><br/>
  
    + Frontend<br>
      <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="20" height="20"/> </a> <span>&nbsp;- Bootstrap v5.1.3</span><br/>
      <a href="https://jquery.com/" target="_blank" rel="noreferrer"> <img src="https://cdn.worldvectorlogo.com/logos/jquery-4.svg" alt="bootstrap" width="20" height="20"/> </a> <span>&nbsp;- jQuery v3.7.1</span><br/>

  + Environment<br>
     <a href="https://www.jetbrains.com/idea/" target="_blank" rel="noreferrer"><img src="https://blog.jetbrains.com/wp-content/uploads/2019/01/idea_icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- IntelliJ IDEA Ultimate 2023.2.3</span><br/></a>
     <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="20" height="20"/> </a> <span>&nbsp;- Spring boot v3.2.3</span><br/>
     <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="20" height="20"/> </a> <span>&nbsp;- Postman</span><br/>
     <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="20" height="20"/> </a> <span>&nbsp;- Git</span><br/>
     <a href="https://aws.amazon.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/amazonwebservices/amazonwebservices-original-wordmark.svg" alt="aws" width="20" height="20"/> </a> <span>&nbsp;- EC2 (Ubuntu), RDS (MariaDB)</span><br/>


- 팀 멤버:
    + 박영무 (BE/FE)  [@VoiceofSiren](https://github.com/VoiceofSiren) <br><br>
      AJAX (jQuery) / DB / Validation / Security / Deployment<br><br>
      <br/>

## Project Description
- Back-end를 중점적으로 설명 드리겠습니다.

### Architecture
- 3-Tier 아키텍처를 사용하였습니다.

<p align="left">
  <ol>
    <li style="font-size: 20px">Presentation Layer</li>
      <ul>
        <li>Controller, View, Static resources</li>
      </ul>
    <li style="font-size: 20px">Application Layer</li>
      <ul>
        <li>Service Layer</li>
      </ul>
    <li style="font-size: 20px">Data Access Layer</li>
          <ol>
            <li>ORM Framework</li>
              <ul>
                <li>Spring Data JPA (Repository, Entity)</li>
                <li>MyBatis (Mapper, SQL)</li>
              </ul>
            <li>DBMS Connection</li>
              <ul>
                <li>MaraDB (Local DB for Development / RDS for Deployment)</li>
              </ul>
          </ol>
      </ul>
  </ol>
</p>
<br/>



### Front-end
- Bootstrap을 활용하여 간단한 레이아웃을 구현하였습니다.

+ UX/UI

<table>
  <thead>
    <tr>
      <th align="center">홈 화면</th>
      <th align="center">로그인 화면</th>
      <th align="center">게시물 조회</th>
      <th align="center">게시물 입력</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/home.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/sign-in.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/board-list.png" width="300px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/board-form.png" width="300px;" alt=""/></td>
     <tr/>
  </tbody>
</table>

1. Navigation Bar에 홈 화면과 게시물 화면으로 이동할 수 있는 링크를 추가하였습니다.
2. 현재 접속 중인 페이지에 따라 Navigation Bar의 링크를 활성화/비활성화시키도록 설정하였습니다.<br><br>

+ AJAX
  - 로그인 시 입력한 데이터를 검증합니다.
<table>
  <thead>
    <tr>
      <th align="center">OrderDetails</th>
      <th align="center">Cart</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="src/main/resources/static/readme/sign-in-invalid-id.png" width="460px;" alt=""/></td>
      <td align="center"><img src="src/main/resources/static/readme/sing-in-failure.png" width="500px;" alt=""/></td>
    <tr/>
  </tbody>
</table>

1. Thymeleaf의 ${param.error} 변수를 사용하였습니다.


### Back-end
- Spring Data JPA, JPQL, MyBatis를 이용하여 DBMS로 CRUD 연산을 수행하였습니다.
- 개발 시에는 로컬 DB에 엑세스하였으며, 배포 단계에서는 AWS EC2 인스턴스에서 RDS에 엑세스하였습니다.

+ ER Diagram

  <img src="src/main/resources/static/readme/ER-Diagram.png" alt="ER Diagram" width=500>

<br/>

<h2 align="left">6. Security</h2>
<p align="left">
  <ul>
    <li style="font-size: 20px">Spring Security 6 - WebSecurityConfig 클래스</li>
      <ol>
        <li style="list-style-type: decimal">SecurityFilterChain()</li>
          <ol>
            <li style="list-style-type: lower-roman">authorizeHttpRequests()</li>
              <ol style="list-style-type: circle">
                <li>로그인 여부에 따라 특정 경로에 대한 접근 권한을 부여합니다.</li>
              </ol>
            <li style="list-style-type: lower-roman">sessionManagement()</li>
              <ol style="list-style-type: circle">
                <li>세션과 관련하여 다중 로그인을 설정합니다.</li>
                <li>세션 고정 공격으로부터 보호하도록 설정합니다.</li>
              </ol>
          </ol>
    <li style="list-style-type: decimal">bCryptPasswordEncoder()</li>
      <ol>
        <li style="list-style-type: lower-roman">new BCryptPasswordEncoder()</li>
          <ol style="list-style-type: circle">
            <li>단방향 암호화 해시 함수로서 비밀번호를 암호화합니다.</li>
            <li>Bean으로 등록된 해당 객체를 UserService에 주입하여 회원가입 시 호출하여 비밀번호를 암호화합니다.</li>
          </ol>
      </ol>
  </ul>
</p>



+ 상품 관련

  <table>
  <thead>
    <tr>
      <th align="center">상품 등록（Admin）</th>
      <th align="center">상품 상세/수정/삭제（Admin）</th>
      <th align="center">상품 목록 조회（User）</th>
      <th align="center">상품 상세（User）</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/ed3802b5-4154-4600-bb50-f3b1a623ee7b" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/d157e335-693b-4d75-97ef-98ba8efe78f5" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/615c5401-a8ac-428e-ae48-5ef80bd59336" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/5bada663-7fbd-47e7-8e1f-ea348bff3071" width="300px;" alt=""/></td>
     <tr/>
  </tbody>
  </table>


관리자 페이지에서 상품, 사진, 재고 등을 DB, resource 폴더에 등록하고 클라이언트 페이지에서 해당 데이터를 읽는 형태입니다.<br>

+ 주문

  <table>
  <thead>
    <tr>
      <th align="center">회원 주문 페이지</th>
      <th align="center">회원 주문 페이지</th>
      <th align="center">회원 주문 페이지</th>
      <th align="center">회원 주문 페이지</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/c87ca19e-c70c-4eab-abeb-6b3c9467621f" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/62e3c23d-53e0-407b-821b-d5faf677370e" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/df9c0645-97ad-400a-8f44-d4d159dd3616" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/5bada663-7fbd-47e7-8e1f-ea348bff3071" width="300px;" alt=""/></td>
     <tr/>
  </tbody>
  </table>

+ 관리자 페이지

  <table>
  <thead>
    <tr>
      <th align="center">관리자 페이지</th>
      <th align="center">회원 목록</th>
      <th align="center">Q&A</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/6313fc23-a60f-43fd-9ab9-dd20bc4e58bd" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/6326ef40-2862-4ceb-9eac-4c34f761ef54" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/04ab0a49-fd69-492a-9e0d-e4380a47e0aa" width="400px;" alt=""/></td>
     <tr/>
  </tbody>
  </table>

매출은 보류, 비회원 주문 목록은 아직 버그 문제로 소개하지 못했습니다. 아직 원인을 알 수 없어 추후에 해결하도록 하겠습니다.


+ 인증 (Spring Security 6)

  <table>
  <thead>
    <tr>
      <th align="center">User1의 회원정보 페이지</th>
      <th align="center">권한 외 요청</th>
      <th align="center">Log</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/fe5ed579-cf5f-4f72-b6cb-7bdfd5b8cc43" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/daa040e4-beee-4bb7-8499-e37a04a98ed9" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/96f6de73-f59e-4f45-a307-b57586af260d" width="400px;" alt=""/></td>
     <tr/>
  </tbody>
  </table>

미인증 사용자의 요청은 회원 로그인 페이지로, 권한이 있는 회원의 요청은 404 오류 페이지를 반환하여 인증을 강화했습니다.<br>
권한 외 요청이 있을 경우 log4j를 통해 WARN 로그를 남기도록 코드를 작성했습니다.<br>

해결하지 못한 점 : 일부 페이지에서 Interceptor가 적용되지 않는 현상이 있어 향후 개선해보고자 합니다.<br>
<br/>

## 리팩토링
- 일부 페이지에서 Interceptor가 적용되지 않는 문제를 해결하기 위한 대안으로 Spring Security 6를 사용할 예정입니다.
    - 이에 따라 사용자 별 권한 부여를 위한 필드를 추가할 예정입니다.
- AWS에 배포할 예정입니다.
- 페이지 처리 버그 개선 예정입니다.
  <br/>

## 참고자료
- 제품 사진 360장: https://www.coor.kr/
- 인덱스 페이지 이미지: https://unsplash.com/ko
- 로고: https://www.figma.com/
- Kakao Map API, Kakao 주소 API : https://developers.kakao.com/
- Sweetalert2: https://sweetalert2.github.io/
- SVG 및 폰트: https://fonts.google.com/