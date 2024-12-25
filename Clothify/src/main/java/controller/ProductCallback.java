package controller;

import dto.CartTM;

public interface ProductCallback {
    void onProductAction(CartTM cartTM);  // Example method to pass data
}
