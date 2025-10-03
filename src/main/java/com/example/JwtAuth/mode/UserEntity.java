package com.example.JwtAuth.mode;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    @Column( nullable = false)
    private String  firstName;

    @Column(nullable = true)
    private String middleName;

    @Column(nullable = false)
    private String lastName;


    //father Name
    @Column(nullable = false)
    private String fatherName;

//    //User Aadhar No.
//    @Column(nullable = false,unique = true)
//    private Long age;

    private int age;

    //User Mobile number
    @Column(nullable = false,unique = true)
    private Long mobileNo;

    //User Email
    @Column( unique = true,nullable = false)
    private String email;

   // User Password
    @Column(nullable = false)
    private String password;


    private  boolean enable;

    @Column(name = "verification_code")
    private  String verificationCode;

    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExporesAt;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    public UserEntity(Integer id, String firstName, String middleName, String lastName, String fatherName, int age,Long mobileNo, String email, String password, boolean enable, String verificationCode, LocalDateTime verificationCodeExporesAt) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        this.fatherName = fatherName;
        this.age = age;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.verificationCode = verificationCode;
        this.verificationCodeExporesAt = verificationCodeExporesAt;
    }

    public UserEntity() {
    }


    public boolean isPresent() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getVerificationCodeExporesAt() {
        return verificationCodeExporesAt;
    }

    public void setVerificationCodeExporesAt(LocalDateTime verificationCodeExporesAt) {
        this.verificationCodeExporesAt = verificationCodeExporesAt;
    }

    public UserEntity(String firstName, String middleName, String lastName, String fatherName,  String email, int age, Long mobileNo, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.email = email;
        this.age = age;
        this.mobileNo = mobileNo;
        this.password = password;
    }

}
