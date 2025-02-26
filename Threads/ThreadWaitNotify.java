package Threads;

class SharedResource {

    public synchronized void waitForNotify() throws InterruptedException {
        System.out.println("Thread :" + Thread.currentThread().getName() + " started");
        wait();
        System.out.println("Thread :" + Thread.currentThread().getName() + " started again");
    }

    public synchronized void doNotify() {
        System.out.println("Thread :" + Thread.currentThread().getName() + " notifed");
        notify();
    }
}

public class work {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread t1 = new Thread(() -> {
            try {
                sharedResource.waitForNotify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(9000);
                sharedResource.doNotify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();

    }
}
