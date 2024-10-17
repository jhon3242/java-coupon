package coupon.config;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoutingReplicas<T> {

    private final List<T> replicas;

    private final AtomicInteger index;

    public RoutingReplicas(List<T> replicas) {
        this.replicas = replicas;
        this.index = new AtomicInteger(0);
    }

    public T get() {
        return replicas.get(index.getAndIncrement() % replicas.size());
    }
}