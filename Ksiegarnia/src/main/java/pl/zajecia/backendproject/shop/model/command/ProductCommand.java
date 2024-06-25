package pl.zajecia.backendproject.shop.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@AllArgsConstructor
@Getter
@Setter
public class ProductCommand {
    private String name;
    private double price;
    private int quantity;

}
