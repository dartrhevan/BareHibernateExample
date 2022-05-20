package ru.javastudy.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "record_book")
public class RecordBook {
    private Long id;
    private Integer code;

    @Id// Is also used by Hibernate to decide where to find JPA annotations (on fields or accessors)
    @Column(name = "id") //JPA annotations are usually used with getters
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "code", unique = true)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RecordBook{" +
                "id=" + id +
                ", code=" + code +
                '}';
    }
}
