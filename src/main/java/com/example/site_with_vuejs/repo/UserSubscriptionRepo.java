package com.example.site_with_vuejs.repo;

import com.example.site_with_vuejs.domain.User;
import com.example.site_with_vuejs.domain.UserSubscription;
import com.example.site_with_vuejs.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepo extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);

    List<UserSubscription> findByChannel(User channel);

    UserSubscription findByChannelAndSubscriber(User chanel, User subscriber);
}
