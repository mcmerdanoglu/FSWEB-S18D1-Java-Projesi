package JpaHibernate.Burger.dao;

import JpaHibernate.Burger.entity.BreadType;
import JpaHibernate.Burger.entity.Burger;

import java.util.List;

public interface BurgerDao {

    List<Burger> findAll();
    Burger findById(int id);
    List<Burger> findByPrice(double price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);
    Burger save(Burger burger);
    Burger update(Burger burger);
    Burger remove(Burger burger);
}
