package org.zerock.b1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.b1.domain.Board;
import org.zerock.b1.repository.search.BoardSearch;


// Spring Data JPA를 이용할 때는 JpaRepository 인터페이스를 이용해서 인터페이스 선언만으로
// 데이터베이스 관련 작업(CRUD와 페이징)을 어느 정도 처리할 수 있다. (마치 MyBatis를 이용할 때 매퍼 인퍼페이스만을
// 선언하는 것과 유사), JpaRepository 인터페이스를 상속할 때는 엔티티 타입과 @Id(주키) 타입을 지정해 주어야 함
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    // 관리할 도메인 유형과 해당 도메인 유형의 ID 유형을 캡처합니다. 일반적인 목적은 유형 정보를 보유할 뿐만 아니라 쉬운 Spring 빈 생성을 위해
    // 클래스 경로 검색 중에 이를 확장하는 인터페이스를 검색할 수 있는 것입니다.
    //이 인터페이스를 확장하는 도메인 저장소는 CrudRepository에 선언된 것과 동일한 서명의 메서드를 선언함으로써 CRUD 메서드를 선택적으로 노출할 수 있습니다.




}
