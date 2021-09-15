import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    static final int CARS_COUNT = 4;
    static final CountDownLatch countDownLatchFinish = new CountDownLatch(CARS_COUNT);
    static final CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);
    static final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        final int THREADS_COUNT = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS_COUNT);
        final int randomSpeed = 20 + (int) (Math.random() * 10);
        for (int i = 0; i < THREADS_COUNT; i++) {

                try {
                    new Thread(new Car(race, randomSpeed)).start();
                    new Thread(new Car(race, randomSpeed)).start();
                    new Thread(new Car(race, randomSpeed)).start();
                    new Thread(new Car(race, randomSpeed)).start();
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
        }
    }
}


