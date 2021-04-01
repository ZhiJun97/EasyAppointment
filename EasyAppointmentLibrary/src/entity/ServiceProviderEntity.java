package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import util.enumeration.StatusEnum;

/**
 *
 * @author ivanlim
 */
@Entity
public class ServiceProviderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueIdNumber; //UIN
    @Column(unique = true, nullable = false)
    private String businessRegNumber;
    private String businessCategory;
    private String name;
    private String address;
    private String city;
    private String phone;
    @Column(unique = true, nullable = false)
    private String email; //username
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;
    @OneToMany(mappedBy = "serviceProviderEntity")
    private List<AppointmentEntity> appointmentEntity;

    public ServiceProviderEntity() {
        this.appointmentEntity = new ArrayList<AppointmentEntity>();
    }
    
    public ServiceProviderEntity(String businessRegNumber, String businessCategory, String name, String address, String city, String email, String phone, String password) {
        this.businessRegNumber = businessRegNumber;
        this.businessCategory = businessCategory;
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public ServiceProviderEntity(String businessRegNumber, String businessCategory, String name, String address, String city, String emailAddress, String password, StatusEnum statusEnum, List<AppointmentEntity> appointmentEntity) {
        this.businessRegNumber = businessRegNumber;
        this.businessCategory = businessCategory;
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = emailAddress;
        this.password = password;
        this.statusEnum = statusEnum;
        this.appointmentEntity = appointmentEntity;
    }

    public Long getServiceProviderId() {
        return uniqueIdNumber;
    }

    public void setServiceProviderId(Long uniqueIdNumber) {
        this.uniqueIdNumber = uniqueIdNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniqueIdNumber != null ? uniqueIdNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the serviceProviderId fields are not set
        if (!(object instanceof ServiceProviderEntity)) {
            return false;
        }
        ServiceProviderEntity other = (ServiceProviderEntity) object;
        if ((this.uniqueIdNumber == null && other.uniqueIdNumber != null) || (this.uniqueIdNumber != null && !this.uniqueIdNumber.equals(other.uniqueIdNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ServiceProviderEntity[ id=" + uniqueIdNumber + " ]";
    }

    public Long getUniqueIdNumber() {
        return uniqueIdNumber;
    }

    public void setUniqueIdNumber(Long uniqueIdNumber) {
        this.uniqueIdNumber = uniqueIdNumber;
    }

    public String getBusinessRegNumber() {
        return businessRegNumber;
    }

    public void setBusinessRegNumber(String businessRegNumber) {
        this.businessRegNumber = businessRegNumber;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
    
        public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AppointmentEntity> getAppointmentEntity() {
        return appointmentEntity;
    }

    public void setAppointmentEntity(List<AppointmentEntity> appointmentEntity) {
        this.appointmentEntity = appointmentEntity;
    }
}
