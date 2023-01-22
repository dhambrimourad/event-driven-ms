package com.devops.estore.product.service.rest;

import com.devops.estore.product.service.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final Environment env;
  private final CommandGateway commandGateway;

  @Autowired
  public ProductController(Environment env, CommandGateway gateway) {
    this.env = env;
    this.commandGateway = gateway;
  }

  @PostMapping
  public String createProduct(@RequestBody CreateProductRestModel model) {
    CreateProductCommand command = CreateProductCommand.builder()
        .price(model.getPrice())
        .quantity(model.getQuantity())
        .title(model.getTitle())
        .productId(UUID.randomUUID().toString())
        .build();

    String result;
    try {
      result = commandGateway.sendAndWait(command);
    } catch (Exception ex) {
      result = ex.getLocalizedMessage();
    }

    return result;
  }

  @GetMapping
  public String getProduct() {
    return "HTTP GET Handled " + env.getProperty("local.server.port");
  }

  @PutMapping
  public String updateProduct() {
    return "HTTP PUT Handled";
  }

  @DeleteMapping
  public String deleteProduct() {
    return "HTTP DELETE Handled";
  }

}
