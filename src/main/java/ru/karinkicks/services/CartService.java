package ru.karinkicks.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karinkicks.dto.ProductDto;
import ru.karinkicks.entity.Cart;
import ru.karinkicks.entity.CartItem;
import ru.karinkicks.entity.Person;
import ru.karinkicks.entity.Product;
import ru.karinkicks.repositories.CartRepository;
import ru.karinkicks.repositories.PersonRepository;
import ru.karinkicks.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {
//
//    private CartRepository cartRepository;
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
