package net.youssfi;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AppFunc {
    public static void main(String[] args) {
        Consumer<String> consumer=(input)-> {
            System.out.println(input);
        };
        consumer.accept("Hello");

        Supplier<String> supplier=()-> {
            return "azerty";
        };
        System.out.println(supplier.get());

        Function<Integer,Integer> function=a->a*4;

        System.out.println(function.apply(43));

    }
}
