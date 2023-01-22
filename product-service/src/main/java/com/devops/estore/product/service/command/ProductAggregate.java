package com.devops.estore.product.service.command;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

  @CommandHandler
  public ProductAggregate(CreateProductCommand command) {

  }

}
