package org.zerock.b1.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass // 데이터베이스의 거의 모든 테이블에는 데이터가 추가된 시간이나 수정된 시간 등이 칼럼으로 작성됨, 이를 쉽게 처리하고자 @MappedSuperclass를 이용해서 공통으로 사용되는 칼럼을 지정하고 해당 클래스를 상속해서 이를 손쉽게 처리
@EntityListeners(value = { AuditingEntityListener.class })  //콜백 리스너 클래스를 지정, JPA 엔터티 리스너는 엔터티 유지 및 업데이트에 대한 감사 정보를 캡처, AuditingEntityListener를 적용하면 엔티티가 데이터베이스에 추가되거나 변경될 때 자동으로 시간 값을 지정할 수 있습니다.
@Getter
public abstract class BaseEntity {

    @CreatedDate //생성된 날짜를 나타내는 필드로 필드를 선언
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;


    @LastModifiedDate
    @Column(name ="moddate" ) //최근에 수정된 날짜를 나타내는 필드로 필드를 선언
    private LocalDateTime modDate;
}
