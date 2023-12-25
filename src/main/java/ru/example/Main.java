package ru.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String server = "http://45.95.234.119:9308";
        ManticoreController controller = new ManticoreController(new SearchCore(server));
        boolean over = false;
        while (!over){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Действия которые вы можете выполнить: \n" +
                    "1. create - Задать таблицу \n" +
                    "2. insert - Добавить запись в таблицу \n" +
                    "3. search - Искать записи в таблице по словам или предложениям \n" +
                    "4. over   - завершить и выйти \n" +
                    "Напишите что будем делать?");
            String choose = scanner.nextLine();

            if(choose.equals("create")){
                boolean createOver = false;
                while (!createOver){
                    scanner = new Scanner(System.in);
                    System.out.println("Ведите название таблицы. Пример: table_name");
                    String tableName = scanner.nextLine();
                    scanner = new Scanner(System.in);
                    System.out.println("Ведите параметры таблицы. Пример: (param1 type, param2 type, ... )");
                    String tableParam = scanner.nextLine();
                    boolean answerOver = false;
                    while (!answerOver){
                        scanner = new Scanner(System.in);
                        System.out.println("Вот что у вас получилось: " + tableName + " " + tableParam + " добавляем? Да(y)/Нет(n)");
                        String answer = scanner.nextLine();
                        if(answer.equals("y")){
                            controller.createTable(tableName,tableParam,true);
                            answerOver = true;
                            createOver = true;
                        }else if (answer.equals("n")){
                            answerOver = true;
                        }else{
                            System.out.println("Не верный ввод. Укажите y ели да и n если нет");
                        }
                    }

                }
            }else if(choose.equals("insert")){
                boolean insertOver = false;
                while (!insertOver){

                }
            }else if(choose.equals("search")){

            }else if(choose.equals("over")){
                over = true;
            }
        }
    }
}
