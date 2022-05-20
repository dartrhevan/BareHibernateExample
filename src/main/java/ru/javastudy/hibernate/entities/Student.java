package ru.javastudy.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    private Long id;
    private RecordBook recordBook;
    private Person person;
    private String group;

    @Id// Is also used by Hibernate to decide where to find JPA annotations (on fields or accessors)
    @Column(name = "id") //JPA annotations are usually used with getters
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "record_book_id")
    public RecordBook getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(RecordBook recordBook) {
        this.recordBook = recordBook;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Column(name = "student_group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", recordBook=" + recordBook +
                ", person=" + person +
                ", group='" + group + '\'' +
                '}';
    }
}
