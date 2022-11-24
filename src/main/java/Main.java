import org.apache.log4j.Logger;
import service.UserService;
import service.UserServiceImpl;

public class Main {
    private static final Logger log = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) {
        log.info("Entering the program");
        UserService service = new UserServiceImpl();
    }
}
