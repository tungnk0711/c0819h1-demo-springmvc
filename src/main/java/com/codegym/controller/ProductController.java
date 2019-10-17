package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
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
        return modelAndView;
    }

    @PostMapping("save-product")
    public ModelAndView saveProduct(HttpServletRequest httpServletRequest){

        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String name = httpServletRequest.getParameter("name");
        Double price = Double.parseDouble(httpServletRequest.getParameter("price"));

        Product product = new Product(id, name, price);
        productService.addHaveBusiness(product);


        ModelAndView modelAndView = new ModelAndView("/product/create");
        return modelAndView;
    }
}
