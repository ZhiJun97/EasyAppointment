/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ryanl
 */
@Entity
public class CustomerEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String indentityNo;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String phone;
    private String address;
    private String city;
    @Column(unique = true, nullable = false)
    private String emailAddress;    
    @OneToMany(mappedBy = "customerEntity")
    private List<AppointmentEntity> appointmentEntity;

    public CustomerEntity() {
        this.appointmentEntity = new ArrayList<AppointmentEntity>();
    }

    public CustomerEntity(String indentityNo, String password, String firstName, String lastName, String gender, Integer age, String phone, String address, String city, String emailAddress) {
        this();
        
        this.indentityNo = indentityNo;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.emailAddress = emailAddress;
    }

    public CustomerEntity(String indentityNo, String password, String firstName, String lastName, String gender, Integer age, String phone, String address, String city, String emailAddress, List<AppointmentEntity> appointmentEntity) {
        this.indentityNo = indentityNo;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.emailAddress = emailAddress;
        this.appointmentEntity = appointmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndentityNo() {
        return indentityNo;
    }

    public void setIndentityNo(String indentityNo) {
        this.indentityNo = indentityNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<AppointmentEntity> getAppointmentEntity() {
        return appointmentEntity;
    }

    public void setAppointmentEntity(List<AppointmentEntity> appointmentEntity) {
        this.appointmentEntity = appointmentEntity;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CustomerEntity[ id=" + id + " ]";
    }
}
