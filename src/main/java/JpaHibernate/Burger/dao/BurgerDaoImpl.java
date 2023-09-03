package JpaHibernate.Burger.dao;

import JpaHibernate.Burger.entity.BreadType;
import JpaHibernate.Burger.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{

    private EntityManager entityManager;

    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Bu bölümdeki sorgu diline JPQL (Java veya Jakarta Persistence Query Language) denir.
    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b ORDER BY id", Burger.class); //Burger.class burada dönüş tipi olarak verilir.
        return query.getResultList();
    }

    @Override
    public Burger findById(int id) {
        return entityManager.find(Burger.class, id);
    }

    @Override
    public List<Burger> findByPrice(double price) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price >= :price ORDER BY b.price DESC", Burger.class);
        query.setParameter("price", price);//Bu şekilde JPQLdeki price ile parametre olarak verilen pricein aynı olduğu belirtilir.
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType= :type ORDER BY name ASC", Burger.class);
        query.setParameter("type", breadType);//Burada nesnenin kendisi değil adı alınacağı zaman breadType.name() olarak yazılabilir.
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {//Bu bölümü değiştirdim ama çalışmadı!!!
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents ILIKE :ingredient", Burger.class);
        query.setParameter("ingredient", "%" + content + "%");//queryde ne yazarsak onu parametre ile eşitleriz. İsimleri aynı olmak zorunda değil!
        return query.getResultList();
    }

    @Transactional //Hata durumunda son işlemi geri alması için konulur. //CREATE-UPDATE-DELETE bölümlerine mutlaka koy.
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger update(Burger burger) {

        return entityManager.merge(burger);
    }

    @Override
    public Burger remove(Burger burger) {
        Burger burgerToDelete = findById(burger.getId());
        entityManager.remove(burger);
        return burgerToDelete;
    }
}
