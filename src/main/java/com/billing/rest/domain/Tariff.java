package com.billing.rest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@ToString(of = {"id", "title", "price", "calls", "sms", "internet"})
@EqualsAndHashCode(of = {"id"})
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdInfo.class)
    private String title;

    @JsonView(Views.IdInfo.class)
    private String price;
    @JsonView(Views.IdInfo.class)
    private String calls;
    @JsonView(Views.IdInfo.class)
    private String sms;
    @JsonView(Views.IdInfo.class)
    private String internet;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCalls() {
        return calls;
    }

    public void setCalls(String calls) {
        this.calls = calls;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
