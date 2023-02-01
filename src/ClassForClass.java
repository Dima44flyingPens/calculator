public class ClassForClass {
    public static void main(String[] args){
        Person person1 = new Person();
        person1.name = "ЖуЖ";
        person1.age = 88;
        person1.speak();
        Person person2 = new Person();
        person2.name = "Вован";
        person2.age = 2;
    }
}
class Person{
    String name;
    int age;
    void speak(){
        System.out.println("Меня зовут - "+ name +" Мане " + age + " лет");
    }
}