package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list*", method = RequestMethod.GET)
    public ModelAndView getProducts(){
        List<Product> productList  = productService.findAllHaveBusiness();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productList);
        modelAndView.addObject("message","Danh sach san pham");
        return modelAndView;
    }

    @GetMapping("/add-product")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("message","Add Product");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save-product")
    public ModelAndView saveProduct(@ModelAttribute Product product){

        productService.addHaveBusiness(product);


        ModelAndView modelAndView = new ModelAndView("/product/create");
        return modelAndView;
    }

    @GetMapping("/search-by-name")
    public ModelAndView searchByName(@RequestParam("name") String name){

        List<Product> productList  = productService.findAllHaveBusiness();
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productList);
        modelAndView.addObject("message","Danh sach san pham");
        return modelAndView;

    }

    @GetMapping("/edit-product/{id}/{address}")
    public ModelAndView editForm(@PathVariable("id") Long id, @PathVariable("address") String address){


        ModelAndView modelAndView = new ModelAndView("/product/edit");
        return modelAndView;
    }
}
