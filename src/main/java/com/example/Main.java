package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        //    Singleton
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
        //    Adapter
        TypeC typeC = new TypeC();
        USB usbAdapter = new TypeCToUSBAdapter(typeC);
        usbAdapter.connect();
        //     Observer
        NewsAgency agency = new NewsAgency();
        User user1 = new User("Миша");
        User user2 = new User("Елена");
        agency.addObserver(user1);
        agency.addObserver(user2);
        agency.setNews("Java 21 выпущена!");

    }

//    -----------Singleton
    public static class Singleton {
        private static Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }

//    -------------Adapter

interface USB{
        void connect();
}

static class TypeC{
    void plugIn(){
        System.out.println("Type-C connected");
    }
}
static class TypeCToUSBAdapter implements USB{
        private TypeC typeC;

        public TypeCToUSBAdapter(TypeC typeC){
            this.typeC = typeC;
        }
       @Override
    public void connect(){
            typeC.plugIn();
       }

}

//    -------------Observer
interface Observer {
        void update(String message);
}
static class NewsAgency{
        private List<Observer> observers = new ArrayList<>();
        private  String news;

        public void addObserver(Observer observer){
            observers.add(observer);
        }

        public void setNews(String news){
            this.news = news;
            notifyObservers();
        }
        private void notifyObservers(){
            for(Observer observer : observers){
                observer.update(news);
            }
        }
}
static class User implements Observer{
        private String name;

        public User(String name){
            this.name = name;
        }
        @Override
    public void update(String message){
            System.out.println(name + " получил новость: " + message);
        }
}


}