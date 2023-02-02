package Test_calculator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Calculator {
    public static void main(String[] args) {
        //2+3
        //X+V=XV


        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scn.nextLine();
        calc(input);
    }
    public static String calc(String input){
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        //Определяем арифметическое действие:
            int actionIndex=-1;



            //проверем сколько символов (+, -, /, *) использует юзер.
            for (int i = 0; i < actions.length; i++) {
                int count = input.length() - input.replace(actions[i], "").length();
                if(count>1){
                    System.out.println("Формат математической операции не удовлетворяет заданию - \n" +
                            "два операнда и один оператор (+, -, /, *)");
                    System.exit(1);
                }

            }



            for (int i = 0; i < actions.length; i++) {
                if(input.contains(actions[i])){
                    actionIndex = i;
                    break;
                }
            }
            //Если не нашли арифметического действия, завершаем программу
            if(actionIndex==-1){
                System.out.println("Некорректное выражение");

            }
            //Делим строчку по найденному арифметическому знаку
            String[] data = input.split(regexActions[actionIndex]);

            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
                int a,b;
                //Определяем, римские ли это числа
                boolean isRoman = converter.isRoman(data[0]);
                if(isRoman){
                    //если римские, то конвертируем их в арабские
                    //X+V
                    //x-10
                    //v - 5
                    a = 0;
                    b = 0;
                    try {
                        a = converter.romanToInt(data[0]);
                        b = converter.romanToInt(data[1]);
                        if (a>10 || b>10){
                            System.out.println("Один из введённых вами аргументов был больше 10, этот калькулятор работает только с числами меньшими либо равными 10");
                            System.exit(1);
                        }
                    }catch (NullPointerException e){
                        System.out.println("Формат математической операции не удовлетворяет заданию - \n" +
                                "два операнда и один оператор (+, -, /, *)");
                        System.exit(1);
                    }


                }else{
                    //если арабские, конвертируем их из строки в число
                    a = 0;
                    b = 0;
                    try {
                        a = Integer.parseInt(data[0]);
                        b = Integer.parseInt(data[1]);
                        if (a>10 || b>10){
                            System.out.println("Один из введённых вами аргументов был больше 10, этот калькулятор работает только с числами меньшими либо равными 10");
                            System.exit(1);
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Формат математической операции не удовлетворяет заданию - \n" +
                                "два операнда и один оператор (+, -, /, *)");
                        System.exit(1);
                    }

                }
                //выполняем с числами арифметическое действие
                int result = 0;

                try{
                    switch (actions[actionIndex]){
                        case "+":
                            result = a+b;
                            break;
                        case "-":
                            result = a-b;
                            break;
                        case "*":
                            result = a*b;
                            break;
                        default:
                            result = a/b;
                            break;
                    }
                }
                catch (ArithmeticException e){
                    System.out.println("На 0 делить нельзя!");
                    System.exit(1);
                }
                //15->XV
                if(isRoman){

                        System.out.println(converter.intToRoman(result));
                        input = converter.intToRoman(result);

                }
                else{
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                    input = String.valueOf(result);



                }
            }else{
                System.out.println("Числа должны быть в одном формате");
            }






        return input;

    }
}


