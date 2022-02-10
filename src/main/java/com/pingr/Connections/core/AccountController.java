package com.pingr.Connections.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping(path ={"/{id}/friends"})
    public ResponseEntity listAll(@PathVariable Long id, @RequestParam("count") Optional<Boolean> count){
        ResponseEntity resp;
        Set<Account> friends = service.listAllFriends(id);
        if(count.isPresent()){
            List<Long> listId = friends.stream()
                    .map(Account::getId)
                    .collect(Collectors.toList());
            resp = ResponseEntity.ok().body(friends.size());
        }else{
            List<Long> listId = friends.stream()
                    .map(Account::getId)
                    .collect(Collectors.toList());
            resp = ResponseEntity.ok().body(listId);
        }

        return resp;
    }
}
