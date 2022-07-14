package com.bridgelabz.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class OrderDTO {
    private int price;
    private int quantity;
    private String address;
    private int userId;
    private int bookId;
    private boolean cancel;

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
