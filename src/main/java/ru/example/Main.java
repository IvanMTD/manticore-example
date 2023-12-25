package ru.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String server = "http://localhost:9308";
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
                    Map<String,Object> jsonDocMap = new HashMap<>();
                    boolean addParam = false;
                    while(!addParam) {
                        scanner = new Scanner(System.in);
                        System.out.println("Введите название параметра. Пример name");
                        String paramName = scanner.nextLine();
                        scanner = new Scanner(System.in);
                        System.out.println("введите значение параметра. Согласно типу который в таблице.");
                        String paramType = scanner.nextLine();
                        scanner = new Scanner(System.in);
                        System.out.println("Вы ввели: " + paramName + " " + paramType + ". Добавляем?\n" +
                                "Варианты: Да(y)/Нет(n)/Последний(l)");
                        String answer = scanner.nextLine();
                        if(answer.equals("y")){
                            jsonDocMap.put(paramName,paramType);
                        }else if(answer.equals("n")){
                            addParam = true;
                            insertOver = true;
                        }else if(answer.equals("l")){
                            jsonDocMap.put(paramName,paramType);
                            addParam = true;
                        }else{
                            System.out.println("Не верный ответ. Ваши параметры сброшены.");
                        }
                    }
                    scanner = new Scanner(System.in);
                    System.out.println("Вот такой документ у вас получился: \n" + jsonDocMap.toString() + "\n Добавляем? \n Да(y)/Нет(n)");
                    String answer = scanner.nextLine();
                    if(answer.equals("y")){
                        controller.insertData(jsonDocMap,true);
                        scanner = new Scanner(System.in);
                        System.out.println("Будем добавлять еще?\nДа(y)/Нет(n)");
                        answer = scanner.nextLine();
                        if(answer.equals("y")){
                            System.out.println("Возвращаюсь в начало.");
                        }else if(answer.equals("n")){
                            System.out.println("Выход в предыдущее меню.");
                            insertOver = true;
                        }else{
                            System.out.println("Указано не верное значение. Параметры применены. Выход в предыдущее меню.");
                            insertOver = true;
                        }
                    }else if (answer.equals("n")){
                        System.out.println("Параметры сброшены. Выход в предыдущее меню.");
                        insertOver = true;
                    }else{
                        System.out.println("Указанно не верное значение. Ваши параметры сброшены.");
                    }
                }
            }else if(choose.equals("search")){
                boolean searchOver = false;
                while (!searchOver){
                    scanner = new Scanner(System.in);
                    System.out.println("Что ищем? Или напишите back для возврата назад");
                    String request = scanner.nextLine();
                    if(request.equals("back")){
                        searchOver = true;
                    }else {
                        controller.search(request);
                    }
                }
            }else if(choose.equals("over")){
                over = true;
            }
        }
    }
}
