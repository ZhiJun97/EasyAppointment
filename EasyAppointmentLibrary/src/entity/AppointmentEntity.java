package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AppointmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String appointmentNo;
    @Temporal(TemporalType.DATE)
    private Date appointmentDate;
    @Temporal(TemporalType.TIME)
    private Date appointmentTime;
    @ManyToOne
    private CustomerEntity customerEntity;
    @ManyToOne
    private ServiceProviderEntity serviceProviderEntity;

    public AppointmentEntity() {
    }

    public AppointmentEntity(String appointmentNo, Date appointmentDate, Date appointmentTime) {
        this();
        this.appointmentNo = appointmentNo;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentEntity(String appointmentNo, Date appointmentDate, Date appointmentTime, CustomerEntity customerEntity, ServiceProviderEntity serviceProviderEntity) {
        this();
        this.appointmentNo = appointmentNo;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.customerEntity = customerEntity;
        this.serviceProviderEntity = serviceProviderEntity;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public ServiceProviderEntity getServiceProviderEntity() {
        return serviceProviderEntity;
    }

    public void setServiceProviderEntity(ServiceProviderEntity serviceProviderEntity) {
        this.serviceProviderEntity = serviceProviderEntity;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentId != null ? appointmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the appointmentId fields are not set
        if (!(object instanceof AppointmentEntity)) {
            return false;
        }
        AppointmentEntity other = (AppointmentEntity) object;
        if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AppointmentEntity[ id=" + appointmentId + " ]";
    }
    
}
