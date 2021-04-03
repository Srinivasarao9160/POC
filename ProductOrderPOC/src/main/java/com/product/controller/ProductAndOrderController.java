package com.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.modeal.Message;
import com.product.modeal.Orders;
import com.product.modeal.Products;
import com.product.service.ProductAndOrderService;

@RestController
@RequestMapping("/rest")
public class ProductAndOrderController {

	@Autowired
	private ProductAndOrderService service;
	
	@PostMapping("/product/create")
	public Products createProduct(@RequestBody Products product) {
		return service.createProduct(product);
	}
	
	@GetMapping("/product/list")
	public Iterable<Products> getProducts(){
		return service.getProducts();
	}
	
	@PostMapping("/order/create")
	public Orders createOrder(@RequestBody Orders order) {
		return service.createOrder(order);
	}
	
	@GetMapping("/orders/list")
	public Iterable<Orders> getOrders(){
		return service.getOrders();
	}
	// using pathVariable
	@GetMapping("/productbyid/{id}")
    public Products findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}
	
	// using RequestParam
	
//	@GetMapping("/product/get")
//	public Optional<Products> getProduct(@RequestParam("id") Integer id, Model model){
//		model.addAttribute("id", id);
//		return service.getproduct(id);
//	}
	
	// Using Path variable
	@GetMapping("/orderbyid/{id}")
    public Orders findOrderById(@PathVariable int id) {
		return service.getOrderById(id);
	}
	// Using RequestParam
	
//	@GetMapping("/order/get")
//	public Optional<Orders> getOrder(@RequestParam("id") Integer id, Model model){
//		model.addAttribute("id", id);
//		return service.getOrder(id);
//	}
	
	@GetMapping("/order/place/{id}/{quantity}")
	public Message placeOrder(@PathVariable Integer id, @PathVariable Integer quantity) {

//	public Message placeOrder(@RequestParam("product_id") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			Orders order = new Orders();
			int code = service.placeOrder(id, quantity, order);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Order placed Successfully");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Oreder Not placed because you enter Invalid Orderid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
	
	@GetMapping("/order/update/{id}/{quantity}")
	public Message updateOrder(@PathVariable Integer id, @PathVariable Integer quantity) {

	//public Message updateOrder(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {
		Message msg = getMsgObj();
		try {
			int code = service.updateOrder(id, quantity);
			if(code == 0) {
				msg.setCode(0);
				msg.setMessage("Updates Succesfully");
			}
			else if(code == 1) {
				msg.setCode(1);
				msg.setMessage("Update Not possible because you enter invalid orderid");
			}
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
	
	public static Message getMsgObj() {
		return new Message();
	}
}
