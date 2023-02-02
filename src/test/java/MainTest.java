import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    static int st = initStatic();
    private int x = initMember("x");

    static {
        System.out.println("static block");
    }

    static int st2 = initStatic2();

    {
        System.out.println("init block");
        ++x;
    }

    MainTest() {
        System.out.println("constructor");
    }

    {
        System.out.println("init block after constructor");
    }

    static {
        System.out.println("static block after constructor");
    }

    int y = initMember("y");

    @Test
    void returnFromFinallyBlock() {
        System.out.println(returnFromFinally());
    }

    @Test
    void throwFromFinallyBlock() {
        try {
            System.out.println(throwFromFinally());
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }
    }

    @Test
    void printLambdaHashAndClass() {
        printLambdaHash();
        printLambdaHash();
        printLambdaHash();
    }

    @Test
    void printHashAndClassOfLambdaDependingOnScopeVar() {
        printLambdaHash(1);
        printLambdaHash(2);
        printLambdaHash(2);
    }

    @Test
    void printHashAndClassOfAnonymousClass() {
        printAnonymousHash();
        printAnonymousHash();
        printAnonymousHash();
    }

    @Test
    void printHashAndClassOfAnonymousDependingOnScopeVar() {
        printAnonymousHash(1);
        printAnonymousHash(2);
        printAnonymousHash(2);
    }

    @Test
    void printThisInsideLambdaAndAnonymous() {
        System.out.println("this = " + this);
        System.out.println("-- LAMBDA --");
        printThisInsideLambda();
        System.out.println("-- ANONYMOUS --");
        printThisInsideAnon();
    }

    @Test
    void printFieldsAndMethodsOfLambda() {
        System.out.println("this = " + this);
        int n = 1;
        Runnable r = () -> System.out.println("hello" + n);
        Class<? extends Runnable> type = r.getClass();
        System.out.println("class name = " + type.getName());
        for (Field field : type.getDeclaredFields()) {
            System.out.println("field = " + field);
        }
        for (Method method : type.getDeclaredMethods()) {
            System.out.println("method = " + method);
        }
    }

    @Test
    void printDetailsOfClassDefinedInsideMethod() {
        printClassInsideMethod();
        printClassInsideMethod();
        printClassInsideMethod();
    }

    static class S {
        int s = 0;
        static {
            System.out.println("static block inside static class S");
        }
        {
            System.out.println("init block inside static class S");
            ++s;
        }
        S(int s) {
            this.s = s;
            System.out.println("constructor of static class S");
        }
        void print() {
            System.out.println("hello from static class");
        }
    }

    @Test
    void printFromStaticNestedClass() {
        S s = new S(1);
        System.out.println(s);
        s.print();
    }

    @Test
    void printHashAndClassOfDoubleBraceInitializedList() {
        for (int i = 0; i < 3; ++i) {
            List<Integer> list = doubleBraces(i);
            System.out.println(list);
            System.out.println(list.getClass());
        }
    }

    enum Dir {
        up, left, right, down;
    }

    @Test
    void printHashOfEnumValues() {
        System.out.println(Dir.values());
        System.out.println(Dir.values());
        System.out.println(Dir.values());
    }

    @Test
    void printDetailsOfIdenticalLambdaAndMethodReference() {
        for (int i = 0; i < 3; ++i) {
            compareLambdaToMethodReference();
        }
    }

    String returnFromFinally() {
        try {
            return "from try";
        } finally {
            return "from finally";
        }
    }

    int throwFromFinally() {
        try {
            throw new IllegalArgumentException("from try");
        } finally {
            throw new IllegalStateException("from finally");
        }
    }

    void printLambdaHash() {
        Runnable r = () -> System.out.println("hello from lambda");
        System.out.println("lambda hash = " + r);
        System.out.println("lambda class = " + r.getClass());
    }

    void printAnonymousHash() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello from anonymous");
            }
        };
        System.out.println("anonymous hash = " + r);
        System.out.println("anonymous class = " + r.getClass());
    }

    void printAnonymousHash(int n) {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("hello from anonymous " + n);
            }
        };
        System.out.println("anonymous hash = " + r);
        System.out.println("anonymous class = " + r.getClass());
    }

    void printLambdaHash(int n) {
        Runnable r = () -> System.out.println("hello from lambda " + n);
        System.out.println("lambda hash = " + r);
        System.out.println("anonymous class = " + r.getClass());
    }

    void printThisInsideLambda() {
        Runnable r = () -> {
            System.out.println("lambda this = " + this);
            System.out.println("x = " + x);
        };
        r.run();
        System.out.println("lambda = " + r);
    }

    void printThisInsideAnon() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous this = " + this);
                System.out.println("x = " + x);
            }
        };
        r.run();
        System.out.println("anonymous = " + r);
    }

    void printClassInsideMethod() {
        class A {
            int a;
            A(int a) {
                this.a = a;
            }
        }

        A a = new A(5);
        System.out.println("class A defined inside method = " + A.class);
        System.out.println("object of class A defined inside method = " + a);
        System.out.println("a.a = " + a.a);
    }

    static int initStatic() {
        System.out.println("init static field");
        return 3;
    }

    static int initStatic2() {
        System.out.println("init static field 2");
        return 3;
    }

    int initMember(String name) {
        System.out.println("init member field " + name);
        return 2;
    }

    List<Integer> doubleBraces(int a) {
        return new ArrayList<>() {{
            add(a);
        }};
    }

    void someMethod() {}

    void compareLambdaToMethodReference() {
        Runnable l1 = () -> this.someMethod();
        Runnable l2 = () -> this.someMethod();
        Runnable mr1 = this::someMethod;
        Runnable mr2 = this::someMethod;
        System.out.println("- - LAMBDA - -");
        System.out.println("lambda 1 = " + l1);
        System.out.println("class of lambda 1 = " + l1.getClass());
        System.out.println("lambda 2 = " + l2);
        System.out.println("class of lambda 2 = " + l2.getClass());
        System.out.println("- - METHOD REFERENCE - -");
        System.out.println("method reference 1 = " + mr1);
        System.out.println("class of method reference 1 = " + mr1.getClass());
        System.out.println("method reference 2 = " + mr2);
        System.out.println("class of method reference 1 = " + mr2.getClass());
    }
}
