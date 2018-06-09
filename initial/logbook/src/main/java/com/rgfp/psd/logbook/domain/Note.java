package com.rgfp.psd.logbook.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @CreationTimestamp
    private Timestamp timestamp;
    @Column(length=10000)
    private String content;
    private LocalDateTime datetime;

    public String getSummary() {
        return this.content.substring(0, 239);
    }

}
