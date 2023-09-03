package JpaHibernate.Burger.controller;

import JpaHibernate.Burger.dao.BurgerDao;
import JpaHibernate.Burger.dao.BurgerDaoImpl;
import JpaHibernate.Burger.entity.BreadType;
import JpaHibernate.Burger.entity.Burger;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
@Validated
public class BurgerController {

    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping("/")
    public Burger addBurger(@Validated @RequestBody Burger burger){ //@Validated Burger objesi için yazılan özellikleri control ederek valide eder.
        return burgerDao.save(burger);
    }

    @GetMapping("/")
    public List<Burger> getAllBurgers(){
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurgerById(@Positive @PathVariable int id){
        return burgerDao.findById(id);
    }

    @GetMapping("/getBurgersByPrice/{price}")
    public List<Burger> getBurgersByPrice(@PathVariable double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/getBurgersByBreadType/{breadType}")
    public List<Burger> getBurgersByBreadType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/getBurgersByContent/{content}") // Bu bölümü değiştirdim ama çalışmadı!!!
    public List<Burger> getBurgersByContent(@PathVariable String content){
        List<Burger> burgers= burgerDao.findByContent(content);
        return burgers;
    }

    @Transactional
    @PutMapping("/")//idsiz endpointte değil JSON içinde id verilerek Burgerin içinde id gönderiliyor.
    public Burger updateBurger(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable int id){
        return burgerDao.remove(burgerDao.findById(id));
    }

}
