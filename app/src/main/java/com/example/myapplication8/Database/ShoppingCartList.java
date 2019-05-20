package com.example.myapplication8.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartList implements Serializable {
   public List<Product> listShoppingCart = new ArrayList<>();

   public ShoppingCartList(ArrayList<Product> shoppingCart){
       this.listShoppingCart = shoppingCart;

   }
   public ShoppingCartList(){

   }

        public List<Product> getListShoppingCart() {
            return listShoppingCart; //oder mit this.
        }

        public void newShoppingCartItem(Product newItem) {
            this.listShoppingCart.add(newItem);
        }

        public int size(){
            if(listShoppingCart != null){
                 return listShoppingCart.size();
            }
            else{return 0; }
        }
        public Product get(int pos) {
         return listShoppingCart.get(pos);
         }
         public void remove(int pos){
            listShoppingCart.remove(pos);
         }
         public void clearAll(){
            listShoppingCart.clear();
         }

    }

