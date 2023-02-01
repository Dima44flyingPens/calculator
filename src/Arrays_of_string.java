public class Arrays_of_string {
    public static void main(String[] args) {
        int [] number = new int[5];
        number[0] = 10;
        String[] strings = new String[3];
        strings[0]="Привет";
        strings[1]="Пока";
        strings[2]="ДЖжжжж";
        System.out.println(strings[2]);

        for(String string:strings){
            System.out.println(string);
        }
        int [] number1 = {1,2,3};
        int sum = 0;
        for(int x:number1){
            sum = sum + x;
        }
        System.out.println();
        System.out.println(sum);
        
    }
}
