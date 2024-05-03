package com.AbdoHalim.Ecommerce.Service;

import com.AbdoHalim.Ecommerce.Entity.*;
import com.AbdoHalim.Ecommerce.Model.*;
import com.AbdoHalim.Ecommerce.Repository.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserSeviceImp implements  UserService{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final CartRepo cartRepo;
    private final ReviewRepo reviewRepo;
    private  final AuthenticationManager authenticationManager;
    public UserSeviceImp(UserRepo userRepo, PasswordEncoder passwordEncoder,
                         CategoryRepo categoryRepo,ProductRepo productRepo,
                         CartRepo cartRepo,ReviewRepo reviewRepo,
                         AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepo=categoryRepo;
        this.productRepo=productRepo;
        this.cartRepo=cartRepo;
        this.reviewRepo=reviewRepo;
        this.authenticationManager=authenticationManager;
    }
    @Override
    public String Login(LoginModel loginModel) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "User Logged in";
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public String addNewUser(UserAddressModel userAddressModel) {
        User user=new User();
        UserModel userModel=userAddressModel.getUserModel();
        Address address=userAddressModel.getAddress();
        if(userModel.getUserName().equalsIgnoreCase("")||
                userModel.getEmail().equalsIgnoreCase("")||
                userModel.getPassword().equalsIgnoreCase("")||
                address.getCountry().equalsIgnoreCase("") ||
                address.getCity().equalsIgnoreCase("")||
                address.getStreet().equalsIgnoreCase("")){
            return "Enter valid credentials";}
        if (userRepo.findByEmail(userModel.getEmail())!=null){
            return "This Email  Already Exist";
        }
        if (userRepo.findByUserName(userModel.getUserName())!=null){
            return "This UserName Already Exist";
        }


        Cart cart=new Cart();
        user.setCart(cart);

        Address address1=new Address();
        address1.setCountry(address.getCountry());
        address1.setCity(address.getCity());
        address1.setStreet(address.getStreet());

        user.setUserName(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");
        user.setEmail(userModel.getEmail());
        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setAddress(address);
        userRepo.save(user);
        return "Success";
    }
    @Override
    public User GetCurrantUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findByUserName(authentication.getName());
    }

    @Override
    public List<Category> RetriveAllCategorys() {
        return categoryRepo.findAll();
    }



    @Override
    public String addProductToCart(Long id, int quantity) {
        Optional<Product> productOptional=productRepo.findById(id);
        if (!productOptional.isPresent()){
            return "Product Not found";
        }
        if (productOptional.get().getQuantity()< quantity){
            return "The quantity dosen't available";
        }

        Product product= productOptional.get();
        product.setQuantity(quantity);
        Cart cart= GetCurrantUser().getCart();
        List<Product> productList=cart.getProducts();
        //To work as Update and adding new product to list
        if(productList.contains(product)){
            productList.remove(product);
        }
        productList.add(product);
        cart.setProducts(productList);
        cartRepo.save(cart);
        return "Product Added To cart Successfully";
    }

    @Override
    public String deleteFormCart(long id) {
        Cart cart= GetCurrantUser().getCart();
        List<Product> productList=cart.getProducts();
        for(Product product : productList){
            if (product.getProductId()==id){
                productList.remove(product);
                cart.setProducts(productList);
                cartRepo.save(cart);
                return "Product Deleted Successfully";
            }
        }
        return "Product Not Found";
    }
    @Override
    public String addReview(long id, ReviewModel reviewModel) {
        if (reviewModel.getReviewContent().equalsIgnoreCase("")) {
            return "Enter a valid Review";
        }
        Optional<Product> product = productRepo.findById(id);

        if (!product.isPresent()) {
            return "Product Not found";
        }
       List<Review> reviews=product.get().getReviews();

        Review review = new Review();

        review.setUser(GetCurrantUser());
        review.setReviewContent(reviewModel.getReviewContent());
        review.setLocalDateTime(LocalDateTime.now());
        reviews.add(review);
        product.get().setReviews(reviews);
       productRepo.save(product.get());
        return "Review Addded Successfully";
    }
    @Override
    public String DeleteReview(long id) {
        Optional<Review> review=reviewRepo.findById(id);
        if (!review.isPresent()){
            return "review dosent exist";
        }
        if(review.get().getUser().getUserId()!= GetCurrantUser().getUserId()){
            return "This review Not Yours";
        }
        reviewRepo.deleteById(id);
        return "review Delete Successfully";
    }
    @Override
    public List<Product> viewAllBrandProductsByCategoryName(long brandid, String categoryName) {
        return productRepo.findAllByCategoryNameInTheBrand(categoryName,brandid);
    }

    @Override
    public String changeUserInfo(UserAddressModel userAddressModel) {
        User user=GetCurrantUser();
        UserModel userModel=userAddressModel.getUserModel();
        Address address=userAddressModel.getAddress();
        if(
                userModel.getUserName().equalsIgnoreCase("")||

                userModel.getPhoneNumber().equalsIgnoreCase("")||

                address.getCountry().equalsIgnoreCase("") ||
                address.getCity().equalsIgnoreCase("")||
                address.getStreet().equalsIgnoreCase("")){
            return "Enter valid credentials";}
        if (!user.getUserName().equalsIgnoreCase(userModel.getUserName())&& userRepo.findByUserName(userModel.getUserName())!=null){
            return "This UserName Already Exist";
        }
        Address address1=new Address();
        address1.setCountry(address.getCountry());
        address1.setCity(address.getCity());
        address1.setStreet(address.getStreet());
        user.setUserName(userModel.getUserName());
        user.setPhoneNumber(userModel.getPhoneNumber());
        user.setAddress(address);
        userRepo.save(user);

        return "Info Updated successfully";

    }

    @Override
    public String changePassword(ChangePasswordModel changePasswordModel) {
        User user=GetCurrantUser();
        if (!user.getPassword().equals(passwordEncoder.encode(changePasswordModel.getOldPassword()))){
            return "The Old Password dosent Match";
        } 
        user.setPassword(passwordEncoder.encode(changePasswordModel.getNewPassword()));
        userRepo.save(user);
        return "Password change Successfully";
    }


}
