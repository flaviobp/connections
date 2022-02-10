package com.pingr.Connections.application;

import com.pingr.Connections.core.Account;
import com.pingr.Connections.core.AccountService;
import com.pingr.Connections.core.events.AccountCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final AccountService service;

    @Autowired
    public KafkaConsumerService(AccountService service) {
        this.service = service;
    }

    @KafkaListener(
            containerFactory = "accountCreatedEventKafkaListenerContainerFactory",
            topics = "${topics.acc_created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleAccountCreation(AccountCreatedEvent event) {
        Account account = event.extract();
        this.service.createAccount(account);
        System.out.println("created: " + account);
    }
    @KafkaListener(
            containerFactory = "accountDeletedEventKafkaListenerContainerFactory",
            topics = "${topics.acc_deleted}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleAccountDeletion(AccountCreatedEvent event) {
        Long accountId = event.getAccountId();
        this.service.deleteAccount(accountId);
        System.out.println("deleted: " + accountId);
    }

}
