package com.rgfp.psd.logbook.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @CreationTimestamp
    private Timestamp timestamp;
    @Column(length=10000)
    private String content;

    public String getSummary() {
        return this.content.length() > 239 ? this.content.substring(0, 239) : this.content;
    }

}
