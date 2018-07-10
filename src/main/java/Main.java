import com.michal.googleMapsViewer.dao.UsersRepositoryDao;
import com.michal.googleMapsViewer.dao.UsersRepositoryDaoBean;

public class Main {
    public static void main(String[] args) {
        UsersRepositoryDao usersRepositoryDao = new UsersRepositoryDaoBean();

        System.out.println(usersRepositoryDao.getUsersList());
    }
}
