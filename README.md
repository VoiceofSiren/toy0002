<h1 align="center">Toy Project - Bulletin Board</h1>
<h3 align="left"></h3>

<h3 align="left">Index</h3>
<p align="left">
  <ol>
    <li>Topic</li>
    <li>Summary</li>
    <li>Languages and Tools</li>
    <li>Architecture</li>
    <li>ER Diagram</li>
    <li>Problems and Solutions</li>
  </ol>
</p>

<h3 align="left">1. Topic</h3>
<p align="left">
  JPA를 사용하여 연관 관계에 있는 여러 테이블의 데이터를 객체로 묶어서 CRUD 연산을 수행하는 간단한 Spring boot 프로젝트입니다.
</p>

<h3 align="left">2. Summary</h3>
<p align="left">
  <ul>
    <li>Front-end: Bootstrap을 사용하여 간단한 레이아웃을 구현하였습니다.</li>
    <li>Spring Data JPA, JPQL, MyBatis를 이용하여 DBMS로 CRUD 연산을 수행하였습니다.</li>
    <li>개발 시에는 로컬 DB에 엑세스하였으며, 배포 단계에서는 AWS EC2 인스턴스에서 RDS에 엑세스하였습니다.</li>
  </ul>
</p>

<h3 align="left">3. Languages and Tools</h3>
<p align="left">
  <a href="https://aws.amazon.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/amazonwebservices/amazonwebservices-original-wordmark.svg" alt="aws" width="25" height="25"/> </a> <span>&nbsp;- EC2 (Ubuntu), RDS (MariaDB)</span><br/>
  <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="25" height="25"/> </a> <span>&nbsp;- Bootstrap v5.1.3</span><br/>
  <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="25" height="25"/> </a> <span>&nbsp;- Github</span><br/>
  <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="25" height="25"/> </a> <span>&nbsp;- OpenJDK 17</span><br/>
  <a href="https://mariadb.org/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/mariadb/mariadb-icon.svg" alt="mariadb" width="25" height="25"/> </a> <span>&nbsp;- MariaDB v11.3.2</span><br/>
  <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="25" height="25"/> </a> <span>&nbsp;- Postman</span><br/>
  <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="25" height="25"/> </a> <span>&nbsp;- Spring boot v3.2.3</span><br/>
</p>

<h3 align="left">4. Architecture</h3>
<p align="left">
  <ol>
    <li>Presentation Layer</li>
      <ul>
        <li>MVC 패턴 기반 Controller, View, Static resources</li>
      </ul>
    <li>Application Layer</li>
      <ul>
        <li>Service Layer</li>
        <li>Data Access Layer</li>
          <ol>
            <li>ORM Framework</li>
              <ul>
                <li>Spring Data JPA (Repository, Entity)</li>
                <li>MyBatis (Mapper, SQL)</li>
              </ul>
            <li>DBMS Connection</li>
              <ul>
                <li>MaraDB RDS</li>
              </ul>
          </ol>
      </ul>
    <li>Support Layer</li>
      <ul>
        <li>Security</li>
          <ul>
            <li>Spring Security 6</li>
          </ul>
      </ul>
  </ol>
</p>
