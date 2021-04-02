package ejb.session.stateless;

import entity.AppointmentEntity;
import util.exception.AppointmentNotFoundException;


/**
 *
 * @author zhijun
 */
public interface AppointmentEntitySessionBeanLocal {

    public Long createAppointmentEntity(AppointmentEntity appointmentEntity);

    public AppointmentEntity retrieveAppointmentById(Long id) throws AppointmentNotFoundException;

    public AppointmentEntity retrieveAppointmentByAppointmentNo(String appointmentNo) throws AppointmentNotFoundException;

    public void updateAppointment(AppointmentEntity appointmentEntity);

    public void deleteAppointment(Long id);
    
}
