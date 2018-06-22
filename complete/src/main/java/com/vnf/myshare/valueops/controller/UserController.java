package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.UserRepository;
import com.vnf.myshare.valueops.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //search all user
    @GetMapping(value = "/findusers")
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //add one user
    @PostMapping(value = "/saveuser")
    public User addUser(@RequestParam("name") String name, @RequestParam("phone") String phone){
        User user = new User(name,phone);
        user.setName(name);
        user.setPhone(phone);
        return userRepository.save(user);
    }



    //update one user
    //PutMapping,至少一个PathVariable才可获取到参数
    @PutMapping(value = "/updateuser/{id}")
    //public User updateUser(@PathVariable("id") long id,@RequestParam("name") String name){
    public User updateUser(@RequestBody User myuser){
        User user = userRepository.getOne(myuser.getId());
        user.setName(myuser.getName());
        return userRepository.save(user);
    }

    //delete one user
    @DeleteMapping(value = "deleteuserbyid/{id}")
    public void deleteUserbyid(@PathVariable("id") long id) {
        userRepository.deleteById(id);
    }


    //money of user
    @GetMapping(value = "income/{id}")
    public float getIncome(@PathVariable("id") String id) {
        String sql = "select ifnull(sum(field4),0.00) as income from bo_filedsvalue4 " +
                "where field1 = '" + id + "'" +
                "and field3 = '5'";
        System.out.println("sql:"+sql);
        Float income = jdbcTemplate.queryForObject(sql,Float.class);
        return income;
    }
    @GetMapping(value = "expanse/{id}")
    public float getExpanse(@PathVariable("id") String id) {
        String sql = "select ifnull(sum(field4),0.00) as expanse from bo_filedsvalue4 " +
                "where field1 = '" + id + "'" +
                "and field3 = '6'";
        System.out.println("sql:"+sql);
        Float expanse = jdbcTemplate.queryForObject(sql,Float.class);
        return expanse;
    }

    //delete one user
    //使用DeleteMapping, 在postman中的body写参数，获取不到
    //除非使用如下，直接在url中写，就可获取到参数
    //localhost:8182/deleteuserbyid?id=5
    //若用PostMapping，则没有获取不到参数的问题
    /*@DeleteMapping(value = "deleteuserbyid")
    public void deleteUser2(@RequestParam("id") long id) {
        userRepository.deleteById(id);
    }
    //update one user
    @PostMapping(value = "/deleteuser")
    public void deleteUser(@RequestParam("id") long id){
        userRepository.deleteById(id);
    }*/

    //update one user
    /*@PostMapping(value = "/updateuser")
    public User updateUser(@RequestParam("id") long id,@RequestParam("asset") double asset){
        User user = userRepository.getOne(id);
        user.setAsset(asset);
        return userRepository.save(user);
    }*/

}
