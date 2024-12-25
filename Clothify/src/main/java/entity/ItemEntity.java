package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemEntity {
    private Integer id;
    private String name;
    private String size;
    private Double price;
    private Integer qty;
    private Integer supplier_Id;
}
