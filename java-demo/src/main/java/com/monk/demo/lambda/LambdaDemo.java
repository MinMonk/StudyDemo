package com.monk.test;

interface Strategy {
    String approach(String msg);
}

class Soft implements Strategy {
    @Override
    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}

class Unrelated {
    static String twice(String msg) {
        return msg + " " + msg;
    }
}

public class LambdaDemo {


    Strategy strategy;
    String msg;

    LambdaDemo(String msg) {
        strategy = new Soft(); // [1]
        this.msg = msg;
    }

    void communicate() {
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                new Strategy() { // [2]
                    @Override
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                msg -> msg.substring(0, 5), // [3]
                Unrelated::twice, // [4]
                String::toUpperCase
        };
        LambdaDemo lambda = new LambdaDemo("Hello there");
        lambda.communicate();
        for (Strategy newStrategy : strategies) {
            lambda.changeStrategy(newStrategy); // [5]
            lambda.communicate(); // [6]
        }
    }
}
