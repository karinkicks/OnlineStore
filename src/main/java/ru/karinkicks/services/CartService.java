package ru.karinkicks.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.CartItem;
import ru.karinkicks.repositories.CartRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public Cart updateCart(Cart cart) {
        recalculateCart(cart);
        return cartRepository.save(cart);
    }

    public Cart findCartByOwnerId(Long id) {
        Cart cart = cartRepository.findById(id).orElse(new Cart(id));
        return cart;
    }

    public Cart clearCart(Long id) {
        Cart cart = findCartByOwnerId(id);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    private void recalculateCart(Cart cart) {
        cart.setPrice(new BigDecimal(0.0));
        for (CartItem cartItem : cart.getCartItems()) {
            cart.setPrice(cart.getPrice().add(cartItem.getPrice()));
        }
    }

    public Cart addCartItem(Cart cart, CartItem cartItem) {
        List<CartItem> cartItems = cart.getCartItems();
        if (CollectionUtils.isEmpty(cartItems)) cartItems = new ArrayList<>();
        cartItems =changeQuantityOfCartItems(cartItems, cartItem,1);
        //cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        return cart;
    }

    public List<CartItem> changeQuantityOfCartItems(List<CartItem> cartItems, CartItem cartItem, int i){
        for (CartItem ci: cartItems) {
            if(ci.getProduct().getId().equals(cartItem.getProduct().getId())){
                ci.setQuantity(ci.getQuantity()+i);
                ci.setPrice(ci.getPrice().add(ci.getPricePerProduct()));
                return cartItems;
            }
        }
        cartItems.add(cartItem);
        return cartItems;
    }

//    private PersonService personService;
//    private ProductService productService;
//    private PersonRepository personRepository;
//
//    public Optional<Cart> findCartById(Long id) {
//        return cartRepository.findById(id);
//    }
//
//    public Cart save(Cart cart) {
//        return cartRepository.save(cart);
//    }
//
//    public void addProductToCartById(Long cartId, Long productId) {
//        Cart cart = cartRepository.findById(cartId).orElseThrow(NoSuchElementException::new);
//        save(cart);
//        Optional<CartItem> cartItem = cart.getCartItemFromProductId(productId);
//
//        if (cartItem.isPresent()) {
//            cartItem.get().changeQuantityAndPrice();
//            cart.changePrice();
//            save(cart);
//            return;
//        }
//
//        Product product = productService.findProductById(productId).orElseThrow(NoSuchElementException::new);
//        cart.addItem(new CartItem(product));
//        save(cart);
//    }
//
//    public void deleteProductFromCartById(Long cartId, Long productId){
//        Cart cart = cartRepository.findById(cartId).orElseThrow(NoSuchElementException::new);
//        Optional<CartItem> cartItem = cartRepository.findById(cartId).get().getCartItemFromProductId(productId);
//        if(cartItem.isPresent()) {
//            cart.deleteItem(cartItem.get());
//        }
//    }
//
//    @Transactional
//    public Long getIdCartFromUsername(String username) {
//        Person person = getUser(username);
//        Cart cart = person.getCart();
//        //Long idCart = person.getCart().getId();
//        if(cart == null){
//            cart = new Cart(personService.findByUsername(username).get());
//            cartRepository.save(cart);
//            person.setCart(cart);
//
//        }
//        return person.getCart().getId();
//    }
//
//    private Person getUser(String username) {
//        return personService.findByUsername(username).orElseThrow(NoSuchElementException::new);
//    }


}
