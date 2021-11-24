package com.company;

import java.text.MessageFormat;
import java.util.Scanner;
import static java.lang.System.out;
//import static java.lang.System.err;

public class Converter {
    public static final Scanner scanner = new Scanner(System.in);

    static final int MIN_ANYTHING = 1;
    static final int MAX_CURRENCY = 8;
    static final int MAX_LENGTH = 6;
    static final int MAX_TIME = 8;
    static final int MAX_SQUARE = 8;
    static final int MAX_VOLUME = 7;
    static final int MAX_SPEED = 4;
    static final int MAX_TEMPERATURE = 4;
    static final int MAX_WEIGHT = 7;

    static final String WRONG_CODE = "Вы ввели неподходящий код";

    /**
     * Method is used to get code of the dimension, that user wants to convert from
     * @param maxChoice - maximum available code of the dimension
     * @param minChoice - minimal available code of the dimension
     * @return the code of the dimension
     */
    public static int getChoice(int maxChoice, int minChoice) {
        int choice = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            out.print("Выберите величину ввода:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                out.println(WRONG_CODE);
                isIncorrect = true;
            }
            if (!isIncorrect && (choice < minChoice) || (choice > maxChoice)) {
                out.println(WRONG_CODE);
                choice = 0;
                isIncorrect = true;
            }
        } while (isIncorrect);
        return choice;
    }

    /**
     * Method is used to get code of the dimension, that user wants to convert to
     * @param maxOutput - maximum available code of the dimension
     * @param minOutput - minimal available code of the dimension
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @return the code of the dimension
     */
    public static int getOutput(int maxOutput, int minOutput, int choice) {
        int output = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            out.print("Выберите величиу вывода:");
            try {
                output = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                out.println(WRONG_CODE);
                isIncorrect = true;
            }
            if (!isIncorrect && (output < minOutput) || (output > maxOutput)) {
                out.println(WRONG_CODE);
                output = 0;
                isIncorrect = true;
            }
            if (!isIncorrect && choice == output) {
                out.println("Та же величина не может быть конвертирована");
                isIncorrect = true;
            }
        } while (isIncorrect);
        return output;
    }

    /**
     * Method is used to get the sum of the chosen dimension, that user wants to convert
     * @param inType - the name of the value of conversion
     * @return the sum that is going to be converted
     */
    public static double getInput(String inType) {
        double input = 0;
        boolean isIncorrect;
        do {
            isIncorrect = false;
            out.print("Введите число для конвертации из " + inType);
            try {
                input = Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                out.println("Вы ввели неподходящее число");
                isIncorrect = true;
            }
            if (!isIncorrect && (input < 0)) {
                out.println("Вы ввели неподходяще число");
                input = 0;
                isIncorrect = true;
            }
        } while (isIncorrect);
        return input;
    }


    /**
     * Method is used to create an interface of the conversion of Currency, after user chooses the dimension Currency
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertCurrency
     */
    public static void currency() {
        char usDollarSym = 36;
        char poundSym = 163;
        char euroSym = 8364;
        char yenSym = 165;
        char belRubSym = 8381;
        char rusRubSym = 8381;
        char zlotySym = 90;

        // Интерфейс

        out.println("Вы выбрали конвертацию валют.");
        out.println("Используйте следующие коды для ввода выбранной валюты:");
        out.println(" 1 - Американский доллар \n 2 - Евро \n 3 - Английский фунт \n 4 - Японская йена \n 5 - Белорусский рубль \n 6 - Российский рубль \n 7 - Польский злотый \n 8 - Завершить конвертацию валют \n");

        //Ввод первой валюты и проверка

        int choice = getChoice(MAX_CURRENCY, MIN_ANYTHING);
        if (choice == 8)
            return;

        String inType = "";

        switch (choice) {
            case 1 -> inType = "Американский доллар >> " + usDollarSym;
            case 2 -> inType = "Евро >> " + euroSym;
            case 3 -> inType = "Английский фунт >> " + poundSym;
            case 4 -> inType = "Японская йена >> " + yenSym;
            case 5 -> inType = "Белорусский рубль >> " + belRubSym;
            case 6 -> inType = "Российский рубль >> " + rusRubSym;
            case 7 -> inType = "Польский злотый >> " + zlotySym;
            default -> throw new IllegalStateException(WRONG_CODE);
        }

        //Ввод второй валюты и проверка
        int output = getOutput(MAX_CURRENCY, MIN_ANYTHING, choice);
        if (output == 8)
            return;

        //Ввод сумма перевода и проверка
        double input = getInput(inType);

        //Конвертация величин
        convertCurrency(choice, output, input);
    }

    /**
     * Method is used to perform and process the actual conversion of Currency
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertCurrency(int choice, int output, double input){
        char usDollarSym = 36;
        char poundSym = 163;
        char euroSym = 8364;
        char yenSym = 165;
        char belRubSym = 8381;
        char rusRubSym = 8381;
        char zlotySym = 90;

        String usDollar = "Американский доллар";
        String pound = "Английский фунт";
        String yen = "Японская йена";
        String euro = "Евро";
        String belRub = "Белорусский рубль";
        String rusRub = "Российский рубль";
        String zloty = "Польский злотый";

        double result;
        double rate;
        switch (choice) {
            //Доллар во все валюты
            case 1 -> {
                String usDollarText = "%s{0} по курсу {1} Американский доллар в %s = %s%.2f\n";
                switch (output) {
                    case 2 -> {
                        rate = 0.852988;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, euro, euroSym, result);
                    }
                    case 3 -> {
                        rate = 0.732562;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, pound, poundSym, result);
                    }
                    case 4 -> {
                        rate = 109.633;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, yen, yenSym, result);
                    }
                    case 5 -> {
                        rate = 2.50223;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, belRub, belRubSym, result);
                    }
                    case 6 -> {
                        rate = 72.8849;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, rusRub, rusRubSym, result);
                    }
                    case 7 -> {
                        rate = 3.94333;
                        result = input * rate;
                        out.printf(MessageFormat.format(usDollarText, input, rate), usDollarSym, zloty, zlotySym, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Евро во все валюты
            case 2 -> {
                String euroText = "%s{0} по курсу {1} Евро в %s = %s%.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 1.1728;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, usDollar, euroSym, result);
                    }
                    case 3 -> {
                        rate = 0.792648;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, pound, poundSym, result);
                    }
                    case 4 -> {
                        rate = 136.708;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, yen, yenSym, result);
                    }
                    case 5 -> {
                        rate = 2.9394;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, belRub, belRubSym, result);
                    }
                    case 6 -> {
                        rate = 84.875;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, rusRub, rusRubSym, result);
                    }
                    case 7 -> {
                        rate = 4.6131;
                        result = input * rate;
                        out.printf(MessageFormat.format(euroText, input, rate), euroSym, zloty, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Английский фунт во все валюты
            case 3 -> {
                String poundText = "%s{0} по курсу {1} Английский фунт в %s = %.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 1.60972;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, usDollar, result);
                    }
                    case 2 -> {
                        rate = 1.26161;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, euro, result);
                    }
                    case 4 -> {
                        rate = 172.511;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, yen, result);
                    }
                    case 5 -> {
                        rate = 3.4181;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, belRub, result);
                    }
                    case 6 -> {
                        rate = 99.0681;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, rusRub, result);
                    }
                    case 7 -> {
                        rate = 5.4007;
                        result = input * rate;
                        out.printf(MessageFormat.format(poundText, input, rate), poundSym, zloty, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Йена во все валюты
            case 4 -> {
                String yenText = "%s{0} по курсу {1} Японская йена в %s = %.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 0.00932574;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, usDollar, result);
                    }
                    case 2 -> {
                        rate = 0.00730615;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, euro, result);
                    }
                    case 3 -> {
                        rate = 0.00579135;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, pound, result);
                    }
                    case 5 -> {
                        rate = 0.022;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, belRub, result);
                    }
                    case 6 -> {
                        rate = 65.16;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, rusRub, result);
                    }
                    case 7 -> {
                        rate = 0.036;
                        result = input * rate;
                        out.printf(MessageFormat.format(yenText, input, rate), yenSym, zloty, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Бел руб во все валюты
            case 5 -> {
                String belRubText = "%s{0} по курсу {1} Белорусский рубль в %s = %.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 0.3987;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, usDollar, result);
                    }
                    case 2 -> {
                        rate = 0.342;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, euro, result);
                    }
                    case 3 -> {
                        rate = 0.29;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, pound, result);
                    }
                    case 4 -> {
                        rate = 44.74;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, yen, result);
                    }
                    case 6 -> {
                        rate = 29.025;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, rusRub, result);
                    }
                    case 7 -> {
                        rate = 1.58;
                        result = input * rate;
                        out.printf(MessageFormat.format(belRubText, input, rate), belRubSym, zloty, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            // Рос руб во все валюты
            case 6 -> {
                String rusRubText = "%s{0} по курсу {1} Российский рубль в %s = %.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 0.0137;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, usDollar, result);
                    }
                    case 2 -> {
                        rate = 0.012;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, euro, result);
                    }
                    case 3 -> {
                        rate = 0.0102;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, pound, result);
                    }
                    case 4 -> {
                        rate = 1.5347;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, yen, result);
                    }
                    case 5 -> {
                        rate = 0.034;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, belRub, result);
                    }
                    case 7 -> {
                        rate = 0.055;
                        result = input * rate;
                        out.printf(MessageFormat.format(rusRubText, input, rate), rusRubSym, zloty, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            // Злотый во все валюты
            case 7 -> {
                String zlotyText = "%s{0} по курсу {1} Польский злотый в %s = %.2f\n";
                switch (output) {
                    case 1 -> {
                        rate = 0.253;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, usDollar, result);
                    }
                    case 2 -> {
                        rate = 0.2166;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, euro, result);
                    }
                    case 3 -> {
                        rate = 0.1852;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, pound, result);
                    }
                    case 4 -> {
                        rate = 28.04;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, yen, result);
                    }
                    case 5 -> {
                        rate = 0.6329;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, belRub, result);
                    }
                    case 6 -> {
                        rate = 18.3435;
                        result = input * rate;
                        out.printf(MessageFormat.format(zlotyText, input, rate), zlotySym, rusRub, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Length, after user chooses the dimension Length
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertLength
     */
    public static void length() {
        // Интерфейс
        out.println("Вы выбрали перевод единиц длины");
        out.println("Используйте следующие коды для ввода выбранной единицы длины:");
        out.println(" 1 - Километры \n 2 - Метры \n 3 - Дециметры \n 4 - Сантиметры \n 5 - Миллиметры \n 6 - Завершить конвертацию единиц длины \n");

        //Ввод первой величины и проверка
        int choice = getChoice(MAX_LENGTH, MIN_ANYTHING);
        if (choice == 6)
            return;
        String inType;
        switch (choice) {
            case 1 -> inType = "Километры >> ";
            case 2 -> inType = "Метры >> ";
            case 3 -> inType = "Дециметры >> ";
            case 4 -> inType = "Сантиметры >> ";
            case 5 -> inType = "Миллиметры >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_LENGTH, MIN_ANYTHING, choice);
        if (output == 6)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertLength(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Length
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertLength(int choice, int output, double input){
        double result;
        double rate;

        String km = "Километр";
        String m = "Метр";
        String dm = "Дециметр";
        String cm = "Сантиметр";
        String mm = "Миллиметр";

        String km1 = "км";
        String m1 = "м";
        String dm1 = "дцм";
        String cm1 = "см";
        String mm1 = "мм";

        String text = "{0} в {1}: %f {2} = %.2f {3}\n";
        switch (choice) {
            //Километр во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, m, km1, m1), input, result);
                    }
                    case 3 -> {
                        rate = 10000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, dm, km1, dm1), input, result);
                    }
                    case 4 -> {
                        rate = 100000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, cm, km1, cm1), input, result);
                    }
                    case 5 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, mm, km1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Метр во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, km, m1, km1), input, result);
                    }
                    case 3 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, dm, m1, dm1), input, result);
                    }
                    case 4 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, cm, m1, cm1), input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, mm, m1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Дециметр во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.0001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, km, dm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, m, dm1, m1), input, result);
                    }
                    case 4 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, cm, dm1, cm1), input, result);
                    }
                    case 5 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, mm, dm1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Сантиметр во все величины
            case 4 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.00001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, km, cm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, m, cm1, m1), input, result);
                    }
                    case 3 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, dm, cm1, dm1), input, result);
                    }
                    case 5 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, mm, cm1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Миллиметр во все величины
            case 5 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.000001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, km, mm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, m, mm1, m1), input, result);
                    }
                    case 3 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, dm, mm1, dm1), input, result);
                    }
                    case 4 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, cm, mm1, cm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Time, after user chooses the dimension Time
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertTime
     */
    public static void time() {

        // Интерфейс
        out.println("Вы выбрали перевод единиц времени");
        out.println("Используйте следующие коды для ввода выбранной единицы времени:");
        out.println(" 1 - Года  \n 2 - Недели \n 3 - Дни \n 4 - Часы \n 5 - Минуты \n 6 - Секунды \n 7 - Миллисекунлы \n 8 - Заверщить конвертацию единиц времени");

        //Ввод первой величины и проверка
        int choice = getChoice(MAX_TIME, MIN_ANYTHING);
        if (choice == 8)
            return;
        String inType;
        switch (choice) {
            case 1 -> inType = "Годы >> ";
            case 2 -> inType = "Недели >> ";
            case 3 -> inType = "Дни >> ";
            case 4 -> inType = "Часы >> ";
            case 5 -> inType = "Минуты >> ";
            case 6 -> inType = "Секунды >> ";
            case 7 -> inType = "Миллисекунды >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_TIME, MIN_ANYTHING, choice);
        if (output == 8)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertTime(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Time
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertTime(int choice, int output, double input){
        double rate;
        double result;

        String year = "Года";
        String week = "Недели";
        String day = "Дни";
        String hour = "Часы";
        String min = "Минуты";
        String s = "Секунды";
        String ms = "Миллисекунды";

        String year1 = "лет";
        String week1 = "недель";
        String day1 = "дней";
        String hour1 = "часов";
        String min1 = "минут";
        String s1 = "секунд";
        String ms1 = "миллисекунд";

        String text = "{0} в {1}: %f {2} = %.2f {3}\n";
        switch (choice) {
            //Года во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 52.1428571;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, week, year1, week1), input, result);
                    }
                    case 3 -> {
                        rate = 365;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, day, year1, day1), input, result);
                    }
                    case 4 -> {
                        rate = 8760;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, hour, year1, hour1), input, result);
                    }
                    case 5 -> {
                        rate = 252600;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, min, year1, min1), input, result);
                    }
                    case 6 -> {
                        rate = 31536000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, s, year1, s1), input, result);
                    }
                    case 7 -> {
                        rate = 3.1536e10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, year, ms, year1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Недели во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.0191781;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, year, week1, year1), input, result);
                    }
                    case 3 -> {
                        rate = 0.142857;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, day, week1, day1), input, result);
                    }
                    case 4 -> {
                        rate = 168;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, hour, week1, hour1), input, result);
                    }
                    case 5 -> {
                        rate = 10080;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, min, week1, min1), input, result);
                    }
                    case 6 -> {
                        rate = 604800;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, s, week1, s1), input, result);
                    }
                    case 7 -> {
                        rate = 604800310;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, week, ms, week1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Дни во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.0027397;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, year, day1, year1), input, result);
                    }
                    case 2 -> {
                        rate = 0.142857216;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, week, day1, week1), input, result);
                    }
                    case 4 -> {
                        rate = 24;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, hour, day1, hour1), input, result);
                    }
                    case 5 -> {
                        rate = 1440;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, min, day1, min1), input, result);
                    }
                    case 6 -> {
                        rate = 86400;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, s, day1, s1), input, result);
                    }
                    case 7 -> {
                        rate = 86400044;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, day, ms, day1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Часы во все величины
            case 4 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.000114;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, year, hour1, year1), input, result);
                    }
                    case 2 -> {
                        rate = 0.005952;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, week, hour1, week1), input, result);
                    }
                    case 3 -> {
                        rate = 0.0417;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, day, hour1, day1), input, result);
                    }
                    case 5 -> {
                        rate = 60;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, min, hour1, min1), input, result);
                    }
                    case 6 -> {
                        rate = 3600;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, s, hour1, s1), input, result);
                    }
                    case 7 -> {
                        rate = 3600000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hour, ms, hour1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Минуты во все величины
            case 5 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1.902588493150695081e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, year, min1, year1), input, result);
                    }
                    case 2 -> {
                        rate = 9.920640000000052793e-5;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, week, min1, week1), input, result);
                    }
                    case 3 -> {
                        rate = 0.00069;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, day, min1, day1), input, result);
                    }
                    case 4 -> {
                        rate = 0.01667;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, hour, min1, hour1), input, result);
                    }
                    case 6 -> {
                        rate = 60;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, s, min1, s1), input, result);
                    }
                    case 7 -> {
                        rate = 60000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, min, ms, min1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Секунды во все величины
            case 6 -> {
                switch (output) {
                    case 1 -> {
                        rate = 3.17e-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, year, s1, year1), input, result);
                    }
                    case 2 -> {
                        rate = 1.65e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, week, s1, week1), input, result);
                    }
                    case 3 -> {
                        rate = 1.157e-5;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, day, s1, day1), input, result);
                    }
                    case 4 -> {
                        rate = 0.00028;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, hour, s1, hour1), input, result);
                    }
                    case 5 -> {
                        rate = 0.017;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, min, s1, min1), input, result);
                    }
                    case 7 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, s, ms, s1, ms1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Миллисекунды во все величины
            case 7 -> {
                switch (output) {
                    case 1 -> {
                        rate = 3.17098e-11;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, year, ms1, year1), input, result);
                    }
                    case 2 -> {
                        rate = 1.653e-9;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, week, ms1, week1), input, result);
                    }
                    case 3 -> {
                        rate = 1.157e-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, day, ms1, day1), input, result);
                    }
                    case 4 -> {
                        rate = 2.78e-7;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, hour, ms1, hour1), input, result);
                    }
                    case 5 -> {
                        rate = 1.67e-5;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, min, ms1, min1), input, result);
                    }
                    case 6 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ms, s, ms1, s1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Square, after user chooses the dimension Square
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertSquare
     */
    public static void square() {
        out.println("Вы выбрали перевод единиц площади");
        out.println("Используйте следующие коды для ввода выбранной единицы площади:");
        out.println(" 1 - Квадратный километры  \n 2 - Гектары \n 3 - Ары \n 4 - Квадратные метры \n 5 - Квадратные дециметры \n 6 - Квадратные сантиметры \n 7 - Квадратные миллиметры \n 8 - Завершить конвертацию единиц площади \n");

        int choice = getChoice(MAX_SQUARE, MIN_ANYTHING);
        if (choice == 8)
            return;
        String inType;
        switch (choice) {
            case 1 -> inType = "Квадратные километры >> ";
            case 2 -> inType = "Гектары >> ";
            case 3 -> inType = "Ары >> ";
            case 4 -> inType = "Квадратные метры >> ";
            case 5 -> inType = "Квадратные дециметры >> ";
            case 6 -> inType = "Квадратные сантиметры >> ";
            case 7 -> inType = "Квадратные миллиметры >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_SQUARE, MIN_ANYTHING, choice);
        if (output == 8)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertSquare(choice, output, input);
        if (isCorrect)
            out.print("");

    }

    /**
     * Method is used to perform and process the actual conversion of Square
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertSquare(int choice, int output, double input){
        double rate;
        double result;

        String km = "Квадратные километры";
        String hec = "Гектары";
        String ar = "Ары";
        String m = "Квадратные метры";
        String dm = "Квадратные дециметры";
        String cm = "Квадратные сантиметры";
        String mm = "Квадратные миллиметры";

        String km1 = "кв.км";
        String hec1 = "гектаров";
        String ar1 = "ар";
        String m1 = "кв.м";
        String dm1 = "кв.дм";
        String cm1 = "кв.см";
        String mm1 = "кв.мм";

        String text = "{0} в {1}: %f {2} = %.2f {3}\n";
        switch (choice) {
            //Квадратные километры во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, hec, km1, hec1), input, result);
                    }
                    case 3 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, ar, km1, ar1), input, result);
                    }
                    case 4 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, m, km1, m1), input, result);
                    }
                    case 5 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, dm, km1, dm1), input, result);
                    }
                    case 6 -> {
                        rate = 1E10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, cm, km1, cm1), input, result);
                    }
                    case 7 -> {
                        rate = 1E12;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, mm, km1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Гектары во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, km, hec1, km1), input, result);
                    }
                    case 3 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, ar, hec1, ar1), input, result);
                    }
                    case 4 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, m, hec1, m1), input, result);
                    }
                    case 5 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, dm, hec1, dm1), input, result);
                    }
                    case 6 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, cm, hec1, cm1), input, result);
                    }
                    case 7 -> {
                        rate = 1E10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, hec, mm, hec1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Ары во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, km, ar1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, hec, ar1, hec1), input, result);
                    }
                    case 4 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, m, ar1, m1), input, result);
                    }
                    case 5 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, dm, ar1, dm1), input, result);
                    }
                    case 6 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, cm, ar1, cm1), input, result);
                    }
                    case 7 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ar, mm, ar1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Квадратные метры во все велчины
            case 4 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, km, m1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, hec, m1, hec1), input, result);
                    }
                    case 3 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, ar, m1, ar1), input, result);
                    }
                    case 5 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, dm, m1, dm1), input, result);
                    }
                    case 6 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, cm, m1, cm1), input, result);
                    }
                    case 7 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, mm, m1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Квадратные дециметры во все велчины
            case 5 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1E-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, km, dm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, hec, dm1, hec1), input, result);
                    }
                    case 3 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, ar, dm1, ar1), input, result);
                    }
                    case 4 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, m, dm1, m1), input, result);
                    }
                    case 6 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, cm, dm1, cm1), input, result);
                    }
                    case 7 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, mm, dm1, mm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Квадратные сантиметры во все велчины
            case 6 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1E-10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, km, cm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 1E-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, hec, cm1, hec1), input, result);
                    }
                    case 3 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, ar, cm1, ar1), input, result);
                    }
                    case 4 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, m, cm1, m1), input, result);
                    }
                    case 5 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, dm, cm1, dm1), input, result);
                    }
                    case 7 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, mm, cm1, mm1), input, result);
                    }

                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Квадратные сантиметры во все велчины
            case 7 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1E-12;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, km, mm1, km1), input, result);
                    }
                    case 2 -> {
                        rate = 1E-10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, hec, mm1, hec1), input, result);
                    }
                    case 3 -> {
                        rate = 1E-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, ar, mm1, ar1), input, result);
                    }
                    case 4 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, m, mm1, m1), input, result);
                    }
                    case 5 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, dm, mm1, dm1), input, result);
                    }
                    case 6 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, cm, mm1, cm1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Volume, after user chooses the dimension Volume
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertVolume
     */
    public static void volume() {
        out.println("Вы выбрали перевод единиц объёма");
        out.println("Используйте следующие коды для ввода выбранной единицы объёма:");
        out.println(" 1 - Кубические метры  \n 2 - Кубические дециметры \n 3 - Кубические сантиметры \n 4 - Кубические миллиметры \n 5 - Литры \n 6 - Миллилитры \n 7 - Завершить конвертацию единиц объёма \n");

        int choice = getChoice(MAX_VOLUME, MIN_ANYTHING);
        if (choice == 7)
            return;

        String inType;
        switch (choice) {
            case 1 -> inType = "Кубические метры >> ";
            case 2 -> inType = "Кубические дециметры >> ";
            case 3 -> inType = "Кубические сантиметры >> ";
            case 4 -> inType = "Кубические миллиметры >> ";
            case 5 -> inType = "Литры >> ";
            case 6 -> inType = "Миллилитры >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_VOLUME, MIN_ANYTHING, choice);
        if (output == 7)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);
        //Конвертация
        boolean isCorrect = convertVolume(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Volume
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertVolume(int choice, int output, double input){
        double rate;
        double result;

        String m = "Кубические метры";
        String dm = "Кубические дециметры";
        String cm = "Кубические сантиметры";
        String mm = "Кубические миллиметры";
        String l = "Литры";
        String ml = "Миллилитры";

        String m1 = "куб.м";
        String dm1 = "куб.дм";
        String cm1 = "куб.см";
        String mm1 = "куб.мм";
        String l1 = "литров";
        String ml1 = "миллилитров";

        String text = "{0} в {1}: %f {2} = %.2f {3}\n";
        switch (choice) {
            //Кубические метры во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, dm, m1, dm1), input, result);
                    }
                    case 3 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, cm, m1, cm1), input, result);
                    }
                    case 4 -> {
                        rate = 1000000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, mm, m1, mm1), input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, l, m1, l1), input, result);
                    }
                    case 6 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, ml, m1, ml1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Кубические дециметры во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, m, dm1, m1), input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, cm, dm1, cm1), input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, mm, dm1, mm1), input, result);
                    }
                    case 5 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, l, dm1, l1), input, result);
                    }
                    case 6 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, dm, ml, dm1, ml1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Кубические сантиметры во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, m, cm1, m1), input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, dm, cm1, dm1), input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, mm, cm1, mm1), input, result);
                    }
                    case 5 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, l, cm1, l1), input, result);
                    }
                    case 6 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, cm, ml, cm1, ml1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Кубические миллиметры во все величины
            case 4 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-9;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, m, mm1, m1), input, result);
                    }
                    case 2 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, dm, mm1, dm1), input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, cm, mm1, cm1), input, result);
                    }
                    case 5 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, l, mm1, l1), input, result);
                    }
                    case 6 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mm, ml, mm1, ml1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Литры во все величины
            case 5 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, l, m, l1, m1), input, result);
                    }
                    case 2 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, l, dm, l1, dm1), input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, l, cm, l1, cm1), input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, l, mm, l1, mm1), input, result);
                    }
                    case 6 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, l, ml, l1, ml1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Миллилитры во все величины
            case 6 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ml, m, ml1, m1), input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ml, dm, ml1, dm1), input, result);
                    }
                    case 3 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ml, cm, ml1, cm1), input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ml, mm, ml1, mm1), input, result);
                    }
                    case 5 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, ml, l, ml1, l1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Speed, after user chooses the dimension Speed
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertSpeed
     */
    public static void speed() {
        out.println("Вы выбрали перевод единиц скорости");
        out.println("Используйте следующие коды для ввода выбранной единицы скорости:");
        out.println(" 1 - Метры в секунду  \n 2 - Километры в час \n 3 - Километры в секунду \n 4 - Завершить конвертацию единиц скорости \n");

        int choice = getChoice(MAX_SPEED, MIN_ANYTHING);
        if (choice == 4)
            return;
        String inType;
        switch (choice) {
            case 1 -> inType = "Метры в секунду >> ";
            case 2 -> inType = "Километры в час >> ";
            case 3 -> inType = "Километры в секунду >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_SPEED, MIN_ANYTHING, choice);
        if (output == 4)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertSpeed(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Speed
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertSpeed(int choice, int output, double input){
        double result;
        double rate;

        String m = "М/с";
        String km = "Км/ч";
        String kms = "Км/с";

        String text = "{0} в {1}: %f {0} = %.2f {1}\n";
        switch (choice) {
            //Метры в секунду во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 3.6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, km), input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, m, kms), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Километры в час во все велчины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.277778;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, m), input, result);
                    }
                    case 3 -> {
                        rate = 0.000277778;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, km, kms), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }//Километры в секунду во все велчины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kms, m), input, result);
                    }
                    case 2 -> {
                        rate = 3600;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kms, km), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Temperature, after user chooses the dimension Temperature
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertTemperature
     */
    public static void temperature() {

        out.println("Вы выбрали перевод единиц температуры");
        out.println("Используйте следующие коды для ввода выбранной единицы температуры:");
        out.println(" 1 - Градусы Цельсия  \n 2 - Градусы Фаренгейта \n 3 - Кельвины \n 4 - Завершить конвертацию единиц температуры \n");

        int choice = getChoice(MAX_TEMPERATURE, MIN_ANYTHING);
        if (choice == 4)
            return;

        String inType;
        switch (choice) {
            case 1 -> inType = "Градусы Цельсия >> ";
            case 2 -> inType = "Градусы Фаренгейта >> ";
            case 3 -> inType = "Кельвины >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_TEMPERATURE, MIN_ANYTHING, choice);
        if (output == 4)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertTemperature(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Temperature
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertTemperature(int choice, int output, double input){
        double result;
        char grad = 176;

        String c = "Градусы Цельсия";
        String f = "Градусы Фаренгейта";
        String k = "Кельвины";

        String c1 = "C";
        String f1 = "F";
        String k1 = "K";

        String text = "{0} в {1}: %f %s{2} = %.2f %s{3}\n";
        switch (choice) {
            //Градусы Цельсия во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        result = (input * 9 / 5) + 32;
                        out.printf(MessageFormat.format(text, c, f, c1, f1), input, grad, result, grad);
                    }
                    case 3 -> {
                        result = input + 273.15;
                        out.printf(MessageFormat.format(text, c, k, c1, k1), input, grad, result, grad);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Градусы Фаренгейта во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        result = (input - 32) * 5 / 9;
                        out.printf(MessageFormat.format(text, f, c, f1, c1), input, grad, result, grad);
                    }
                    case 3 -> {
                        result = (input - 32) * 5 / 9 + 273.15;
                        out.printf(MessageFormat.format(text, f, k, f1, k1), input, grad, result, grad);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Кельвины во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        result = input - 273.15;
                        out.printf(MessageFormat.format(text, k, c, k1, c1), input, grad, result, grad);
                    }
                    case 2 -> {
                        result = (input - 273.15) * 9 / 5 + 32;
                        out.printf(MessageFormat.format(text, k, f, k1, f1), input, grad, result, grad);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     * Method is used to create an interface of the conversion of Weight, after user chooses the dimension Weight
     * It calls getChoice, getInput, getOutput methods to get dimensions for conversion
     * and calls an actual conversion method called convertWeight
     */
    public static void weight() {
        out.println("Вы выбрали перевод единиц массы");
        out.println("Используйте следующие коды для ввода выбранной единицы массы:");
        out.println(" 1 - Тонны  \n 2 - Центнеры \n 3 - Килограммы \n 4 - Граммы \n 5 - Миллиграммы \n 6 - Фунты \n 7 - Завершить конвертацию единиц массы \n");

        int choice = getChoice(MAX_WEIGHT, MIN_ANYTHING);
        if (choice == 7)
            return;
        String inType;
        switch (choice) {
            case 1 -> inType = "Тонны >> ";
            case 2 -> inType = "Центнеры >> ";
            case 3 -> inType = "Килограммы >> ";
            case 4 -> inType = "Граммы >> ";
            case 5 -> inType = "Миллиграммы >> ";
            case 6 -> inType = "Фунты >> ";
            default -> {
                return;
            }
        }
        //Ввод второй величины и проверка
        int output = getOutput(MAX_WEIGHT, MIN_ANYTHING, choice);
        if (output == 7)
            return;

        //Ввод суммы перевода
        double input = getInput(inType);

        //Конвертация
        boolean isCorrect = convertWeight(choice, output, input);
        if (isCorrect)
            out.print("");
    }

    /**
     * Method is used to perform and process the actual conversion of Weight
     * It is called by Currency method and given the following parameters
     * @param choice - is the code of the dimension, that user had chosen to convert from
     * @param output - is the code of the dimension, that user had chosen to convert to
     * @param input - is the sum of conversion from choice to output
     * @return true, if no exceptions appeared
     */
    public static boolean convertWeight(int choice, int output, double input){
        double rate;
        double result;

        String t = "Тонны";
        String c = "Центнеры";
        String kg = "Килограммы";
        String g = "Граммы";
        String mg = "Миллиграммы";
        String f = "Фунты";

        String t1 = "тонн";
        String c1 = "центнеров";
        String kg1 = "кг";
        String g1 = "г";
        String mg1 = "мг";
        String f1 = "фунтов";

        String text = "{0} в {1}: %f {2} = %.2f {3}\n";
        switch (choice) {
            //Тонны во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, t, c, t1, c1), input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, t, kg, t1, kg1), input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, t, g, t1, g1), input, result);
                    }
                    case 5 -> {
                        rate = 1000000000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, t, mg, t1, mg1), input, result);
                    }
                    case 6 -> {
                        rate = 2204.62;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, t, f, t1, f1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Центнеры во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, c, t, c1, t1), input, result);
                    }
                    case 3 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, c, kg, c1, kg1), input, result);
                    }
                    case 4 -> {
                        rate = 100000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, c, g, c1, g1), input, result);
                    }
                    case 5 -> {
                        rate = 1e+8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, c, mg, c1, mg1), input, result);
                    }
                    case 6 -> {
                        rate = 220.462;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, c, f, c1, f1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Килограммы во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kg, t, kg1, t1), input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kg, c, kg1, c1), input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kg, g, kg1, g1), input, result);
                    }
                    case 5 -> {
                        rate = 1e+6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kg, mg, kg1, mg1), input, result);
                    }
                    case 6 -> {
                        rate = 2.20462;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, kg, f, kg1, f1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Граммы во все величины
            case 4 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, g, t, g1, t1), input, result);
                    }
                    case 2 -> {
                        rate = 1e-5;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, g, c, g1, c1), input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, g, kg, g1, kg1), input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, g, mg, g1, mg1), input, result);
                    }
                    case 6 -> {
                        rate = 0.00220462;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, g, f, g1, f1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Миллиграммы во все величины
            case 5 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-9;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mg, t, mg1, t1), input, result);
                    }
                    case 2 -> {
                        rate = 1e-8;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mg, c, mg1, c1), input, result);
                    }
                    case 3 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mg, kg, mg1, kg1), input, result);
                    }
                    case 4 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mg, g, mg1, g1), input, result);
                    }
                    case 6 -> {
                        rate = 2.20462e-6;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, mg, f, mg1, f1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Фунты во все величины
            case 6 -> {
                switch (output) {
                    case 1 -> {
                        rate = 0.000453592;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, f, t, f1, t1), input, result);
                    }
                    case 2 -> {
                        rate = 0.00453592;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, f, c, f1, c1), input, result);
                    }
                    case 3 -> {
                        rate = 0.453592;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, f, kg, f1, kg1), input, result);
                    }
                    case 4 -> {
                        rate = 453.592;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, f, g, f1, g1), input, result);
                    }
                    case 5 -> {
                        rate = 453592;
                        result = input * rate;
                        out.printf(MessageFormat.format(text, f, mg, f1, mg1), input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            default -> throw new IllegalStateException(WRONG_CODE);
        }
        return true;
    }


    /**
     *  Method is used to create an interface of the beginning of the program
     *  It offers to choose the dimension
     *  getDimension method will use chosen code for later work of the program
     */
    public static void printInterface(){
        out.println("\n***********************************************************************************");
        out.println("Используйте следующие коды для выбора величины для конвертации: \n 1 - Валюта \n 2 - Длина \n 3 - Время \n 4 - Площадь \n 5 - Объём \n 6 - Скорость \n 7 - Температура \n 8 - Масса \n 9 - Завершить программу \n");
    }

    /**
     * Method is used to get the code of the dimension for conversion
     * @return choice - the code of the chosen dimension
     */
    public static int getDimension(){
        boolean isIncorrect;
        int choice = 0;
        do {
            isIncorrect = false;
            out.print("Выберите величину для конвертации:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                out.println(WRONG_CODE);
                isIncorrect = true;
            }
            if (!isIncorrect && (choice < 1) || (choice > 9)) {
                out.println(WRONG_CODE);
                choice = 0;
                isIncorrect = true;
            }
        } while (isIncorrect);
        return choice;
    }

    /**
     * This is the body of the program
     * Method starts the program and greets the user
     * It calls printInterface method to print the interface of choosing a dimension of conversion
     * It gets this dimension from getDimension Method and, according to its value, calls one of the following methods:
     * Currency(), Length(), Time(), Square(), Volume(), Speed(), Temperature(), Weight()
     * Body of the method is looped, so as the conversion will continue until the users presses the code, that stops the program
     * instead of choosing another dimension to convert
     * @param args - regular parameter for main Method
     */
    public static void main(String[] args) {
        out.println("Добро пожаловать в программу конвертации величин");
        boolean isTrue = true;
        do {
            printInterface();
            int choice = getDimension();
            switch (choice) {
                case 1 -> currency();
                case 2 -> length();
                case 3 -> time();
                case 4 -> square();
                case 5 -> volume();
                case 6 -> speed();
                case 7 -> temperature();
                case 8 -> weight();
                case 9 -> isTrue = false;
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        } while (isTrue);
        scanner.close();
    }
}