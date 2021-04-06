/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import java.util.Date;
import java.util.concurrent.Future;

/**
 *
 * @author zhijun
 */
public interface EmailSessionBeanLocal {

    public Future<Boolean> emailCheckoutNotificationAsync(Date date, String fromEmailAddress, String toEmailAddress) throws InterruptedException;
    
}
