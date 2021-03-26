package ejb.session.stateless;

import entity.AppointmentEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AppointmentNotFoundException;

/**
 *
 * @author zhijun
 */
@Local(AppointmentEntitySessionBeanLocal.class)
@Remote(AppointmentEntitySessionBeanRemote.class)
@Stateless
public class AppointmentEntitySessionBean implements AppointmentEntitySessionBeanRemote, AppointmentEntitySessionBeanLocal {

    @PersistenceContext(unitName = "EasyAppointment-ejbPU")
    private EntityManager em;
    
    @Override
    public Long createAppointmentEntity(AppointmentEntity appointmentEntity) {
        em.persist(appointmentEntity);
        em.flush();

        return appointmentEntity.getAppointmentId();
        }

    @Override
    public AppointmentEntity retrieveAppointmentById(Long id) throws AppointmentNotFoundException {
        AppointmentEntity appointmentEntity = em.find(AppointmentEntity.class, id);
        if (appointmentEntity != null) {
            return appointmentEntity;
        } else {
            throw new AppointmentNotFoundException("Appointment id : " + id + " does not exist!");
            }
        }

    @Override
    public AppointmentEntity retrieveAppointmentByAppointmentNo(String appointmentNo) throws AppointmentNotFoundException {
        Query query = em.createQuery("SELECT a FROM AppointmentEntity a WHERE a.appointmentNo = :inAppointmentNo");
        query.setParameter("inAppointmentNo", appointmentNo);
        try {
            return (AppointmentEntity)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException ex) {
            throw new AppointmentNotFoundException("Appointment Number : " + appointmentNo + " does not exist!");
            }
        }

    @Override
    public void updateAppointment(AppointmentEntity appointmentEntity) {
        em.merge(appointmentEntity);
        }

    @Override
    public void deleteAppointment(Long id) {
        try {
            AppointmentEntity appointmentEntity = retrieveAppointmentById(id);
            em.remove(appointmentEntity);
        } catch (AppointmentNotFoundException ex) {
            System.out.println("Error deleting Appointment! " + ex.getMessage());
        }
    }
}
