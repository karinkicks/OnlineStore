package ru.karinkicks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.karinkicks.entity.Product;
import ru.karinkicks.servlet.PersonDao;
import ru.karinkicks.servlet.PersonToProductDao;
import ru.karinkicks.servlet.ProductDao;
import ru.karinkicks.servlet.ProductRepositoryService;

@Controller
public class ProductRepositoryController {

    private final ProductRepositoryService productRepositoryService;
    private final ProductDao productDao;

    @Autowired
    public ProductRepositoryController(ProductRepositoryService productRepositoryService,
                                       ProductDao productDao){
        this.productRepositoryService=productRepositoryService;
        this.productDao=productDao;
    }

    @GetMapping( "/product_repository")
    public String getForm(Model uiModel){
        uiModel.addAttribute("product", new Product());
        return "index";
    }

    @PostMapping("/product_repository/form")
    public String create(Product product) {
        productDao.saveOrUpdate(product);
        //System.out.println(productRepositoryService.getProducts().size());
        return "redirect:product_repository_show";
    }

    @GetMapping( "/product_repository_show")
    public String showProductRepository(Model uiModel){
        uiModel.addAttribute("products", productDao.findAll());
        return "product_repo";
    }

}
