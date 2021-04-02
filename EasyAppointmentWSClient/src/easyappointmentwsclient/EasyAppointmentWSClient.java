package easyappointmentwsclient;
/**
 *
 * @author ivanlim
 */
public class EasyAppointmentWSClient {

    public static void main(String[] args) {
        Long newServiceProviderId = null;
        try {
            newServiceProviderId = createServiceProviderEntity("A234565","Fashion","Diana's Store","Woodlands Drive 72","Singapore","diana@easysp.com","87654321","password");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("You have been registered successfully! ID: " + newServiceProviderId + ".\n");
    }

    private static Long createServiceProviderEntity(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.lang.String arg7) {
        ejb.session.ws.ServiceProviderEntityWebService_Service service = new ejb.session.ws.ServiceProviderEntityWebService_Service();
        ejb.session.ws.ServiceProviderEntityWebService port = service.getServiceProviderEntityWebServicePort();
        return port.createServiceProviderEntity(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }
    
}
