package ejb.session.stateless;

import java.util.Date;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import util.email.EmailManager;



@Stateless
@Local(EmailSessionBeanLocal.class)
@Remote(EmailSessionBeanRemote.class)
public class EmailSessionBean implements EmailSessionBeanLocal, EmailSessionBeanRemote 
{
    private final String GMAIL_USERNAME = "zhijun83@gmail.com";
    private final String GMAIL_PASSWORD = "char10lene";
  
      
    @Asynchronous
    @Override
    public Future<Boolean> emailCheckoutNotificationAsync(Date date, String fromEmailAddress, String toEmailAddress) throws InterruptedException
    {        
        EmailManager emailManager = new EmailManager(GMAIL_USERNAME, GMAIL_PASSWORD);
        Boolean result = emailManager.emailCheckoutNotification(date, fromEmailAddress, toEmailAddress);
        
        return new AsyncResult<>(result);
    }
}

