package com.mcmanus.os.api.persistence;

import com.mcmanus.os.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
