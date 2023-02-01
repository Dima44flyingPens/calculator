package Test_calculator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Calculator {
    public static void main(String[] args) {
        //2+3
        //X+V=XV
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex=-1;



        //проверем сколько символов (+, -, /, *) использует юзер.
        for (int i = 0; i < actions.length; i++) {
            int count = exp.length() - exp.replace(actions[i], "").length();
            if(count>1){
                System.out.println("Формат математической операции не удовлетворяет заданию - \n" +
                        "два операнда и один оператор (+, -, /, *)");
                System.exit(1);
            }

        }



        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку


        String[] data = exp.split(regexActions[actionIndex]);
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
                if (result > 20){
                    System.out.println("Эта версия калькулятора может сщитать только до 20. \n"  +
                            "Ваше число оказалось больше 20, для того чтобы узнать какое число у вас получилась \n" +
                            "купите полную версию приложения по ссылке skam@ssulka.ru ");
                }
                else{//если числа были римские, возвращаем результат в римском числе
                    System.out.println(converter.intToRoman(result));}

            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        }else{
            System.out.println("Числа должны быть в одном формате");
        }


    }
}


