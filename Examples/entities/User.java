package com.project.TalonMillwork.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 123L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "uname", nullable = false, unique = true)
    private String uname;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "f_name", nullable = false)
    private String fName;

    @Column(name = "l_name", nullable = false)
    private String lName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "role")
    private String role;

    @Column(name = "recoveryAnswer", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String recoveryAnswer;

    @Column(name = "recoveryQuestion")
    private String recoveryQuestion;

    public int getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder(10).encode(password);
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = roles;
    }

    public String getRecoveryAnswer() {
        return recoveryAnswer;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String question) {
        this.recoveryQuestion = question;
    }

    public void setRecoveryAnswer(String answer) {
        this.recoveryAnswer = new BCryptPasswordEncoder(10).encode(answer);
    }
}
