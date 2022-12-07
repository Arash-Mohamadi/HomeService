import util.EntityManagerFactoryProvider;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
    }
}
