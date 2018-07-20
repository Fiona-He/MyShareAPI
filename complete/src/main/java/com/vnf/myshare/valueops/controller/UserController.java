package com.vnf.myshare.valueops.controller;

import com.vnf.myshare.valueops.dao.UserRepository;
import com.vnf.myshare.valueops.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final List<SseEmitter> emitters = new ArrayList<>();


    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream() throws IOException {

        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }


    @RequestMapping(path = "/createuser", method = RequestMethod.POST)
    public User sendMessage(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(emitters.size());

        emitters.forEach((SseEmitter emitter) -> {
            try {
                System.out.println(emitter.toString());
                System.out.println(user.getName());
                emitter.send(user, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                emitters.remove(emitter);
                e.printStackTrace();
            }
        });
        return user;
    }
//-------------------------------------------------------------------------------------------------
    private static Map<Long,SseEmitter> sseEmitters = new Hashtable<>();

    public void addSseEmitters(Long payRecordId, SseEmitter sseEmitter) {
        sseEmitters.put(payRecordId, sseEmitter);
    }

    @RequestMapping(path = "/stream2/{payRecordId}", method = RequestMethod.GET)
    public SseEmitter stream2(@PathVariable Long payRecordId){
        final SseEmitter emitter = new SseEmitter();
        try {
            this.addSseEmitters(payRecordId,emitter);
        }catch (Exception e){
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @RequestMapping(path = "/createuser2", method = RequestMethod.POST)
    public User sendMessage2(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(emitters.size());

        Long payRecordId = user.getId();
        SseEmitter sseEmitter = sseEmitters.get(payRecordId);

        System.out.println(sseEmitter);

        try{
            if(sseEmitter!=null)
            {
                System.out.println(sseEmitter);
                sseEmitter.send(user,MediaType.APPLICATION_JSON);
            }
            else{
                System.out.println("sseEmitter is null ");
            }


        }catch (IOException e) {}

        return user;
    }

    @RequestMapping(path = "/createuser3", method = RequestMethod.POST)
    public int sendMessage3(@RequestBody User[] users) {
        int cnt=0;
        for(int i=0; i<users.length; i++) {
            Long payRecordId = users[i].getId();
            SseEmitter sseEmitter = sseEmitters.get(payRecordId);
            try{
                if(sseEmitter!=null)
                {
                    System.out.println(sseEmitter);
                    sseEmitter.send(users[i],MediaType.APPLICATION_JSON);
                    cnt++;
                }
                else{
                    System.out.println("sseEmitter is null ");
                }

            }catch (IOException e) {}
        }
        return cnt;
    }
//-------------------------------------------------------------------------------------------------



    //search one user
    @GetMapping(value = "/findusers")
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    //add one asset
    @GetMapping(value = "/finduser/{id}")
    public double findUser(@PathVariable("id") long id) {
        User user = userRepository.getOne(id);
        return user.getAsset();
    }
    //add 1 money
    @PostMapping(value = "/add1money/{id}")
    public double add1Money(@PathVariable("id") long id) {
        User user = userRepository.getOne(id);
        user.setAsset(user.getAsset()+1);
        userRepository.save(user);
        return user.getAsset();
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
        user.setAsset(myuser.getAsset());
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
