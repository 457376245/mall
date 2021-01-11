package com.jh.mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public static class Builder{
        private Integer id;

        private String username;

        private String password;

        private String email;

        private String phone;

        private String question;

        private String answer;

        private Integer role;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

        public Builder username(String username){
            this.username=username;
            return this;
        }
        public Builder password(String password){
            this.password=password;
            return this;
        }
        public Builder email(String email){
            this.email=email;
            return this;
        }
        public Builder phone(String phone){
            this.phone=phone;
            return this;
        }
        public Builder question(String question){
            this.question=question;
            return this;
        }
        public Builder answer(String  answer){
            this.answer=answer;
            return this;
        }
        public Builder role(int role){
            this.role=role;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }

    public User(Builder builder) {
        username=builder.username;
        password=builder.password;
        email=builder.email;
        phone=builder.phone;
        question=builder.question;
        answer=builder.answer;
        role=builder.role;
        updateTime=LocalDateTime.now();
        createTime=LocalDateTime.now();
    }
    public User(){
        updateTime=LocalDateTime.now();
        createTime=LocalDateTime.now();
    }
}