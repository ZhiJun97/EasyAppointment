package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import util.enumeration.BusinessCategoryEnum;
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
    @Enumerated(EnumType.STRING)
    private BusinessCategoryEnum businessCategoryEnum;
    private String name;
    private String address;
    private String city;
    @Column(unique = true, nullable = false)
    private String emailAddress; //username
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    public ServiceProviderEntity() {}
    
    public ServiceProviderEntity(String businessRegNumber, BusinessCategoryEnum businessCategoryEnum, String name, String address, String city, String emailAddress, String password) {
        this.businessRegNumber = businessRegNumber;
        this.businessCategoryEnum = businessCategoryEnum;
        this.name = name;
        this.address = address;
        this.city = city;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public BusinessCategoryEnum getBusinessCategoryEnum() {
        return businessCategoryEnum;
    }

    public void setBusinessCategoryEnum(BusinessCategoryEnum businessCategoryEnum) {
        this.businessCategoryEnum = businessCategoryEnum;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    
    
}
