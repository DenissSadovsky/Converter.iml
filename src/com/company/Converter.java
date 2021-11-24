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
        switch (choice) {
            //Километр во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Километр в метр: %f км = %.2f м\n", input, result);
                    }
                    case 3 -> {
                        rate = 10000;
                        result = input * rate;
                        out.printf("Километр в дециметр: %f км = %.0f дм\n", input, result);
                    }
                    case 4 -> {
                        rate = 100000;
                        result = input * rate;
                        out.printf("Километр в сантиметр: %f км = %.0f см\n", input, result);
                    }
                    case 5 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Километр в миллиметр: %f км = %.0f мм\n", input, result);
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
                        out.printf("Метр в километр: %f м = %.3f км\n", input, result);
                    }
                    case 3 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf("Метр в дециметр: %f м = %.2f дцм\n", input, result);
                    }
                    case 4 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Метр в сантиметр: %f м = %.2f см\n", input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Метр в миллиметр: %f м = %.2f мм\n", input, result);
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
                        out.printf("Дециметр в километр: %f дцм = %.4f км\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf("Дециметр в метр: %f дцм = %.2f м\n", input, result);
                    }
                    case 4 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf("Дециметр в сантиметр: %f дцм = %.2f см\n", input, result);
                    }
                    case 5 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Дециметр в миллиметр: %f дцм = %.2f мм\n", input, result);
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
                        out.printf("Сантиметр в километр: %f см = %.5f км\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Сантиметр в метр: %f см = %.2f м\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf("Сантиметр в децииметр: %f см = %.2f дцм\n", input, result);
                    }
                    case 5 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf("Сантиметр в миллиметр: %f см = %.2f мм\n", input, result);
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
                        out.printf("Миллиметр в километр: %f мм = %.6f км\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Миллиметр в метр: %f мм = %.3f м\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Миллиметр в децииметр: %f мм = %.2f дцм\n", input, result);
                    }
                    case 4 -> {
                        rate = 0.1;
                        result = input * rate;
                        out.printf("Миллиметр в сантиметр: %f мм = %.2f см\n", input, result);
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
        switch (choice) {
            //Года во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 52.1428571;
                        result = input * rate;
                        out.printf("Года в недели: %f лет = %.2f недели\n", input, result);
                    }
                    case 3 -> {
                        rate = 365;
                        result = input * rate;
                        out.printf("Года в дни: %f лет = %.2f дней\n", input, result);
                    }
                    case 4 -> {
                        rate = 8760;
                        result = input * rate;
                        out.printf("Года в часы: %f лет = %.2f часов\n", input, result);
                    }
                    case 5 -> {
                        rate = 252600;
                        result = input * rate;
                        out.printf("Года в минуты: %f лет = %.2f минут\n", input, result);
                    }
                    case 6 -> {
                        rate = 31536000;
                        result = input * rate;
                        out.printf("Года в секунды: %f лет = %.2f секунд\n", input, result);
                    }
                    case 7 -> {
                        rate = 3.1536e10;
                        result = input * rate;
                        out.printf("Года в миллисекунды: %f лет = %.2f миллисекунды\n", input, result);
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
                        out.printf("Недели в года: %f недель = %.3f лет\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.142857;
                        result = input * rate;
                        out.printf("Недели в дни: %f недель = %.2f дней\n", input, result);
                    }
                    case 4 -> {
                        rate = 168;
                        result = input * rate;
                        out.printf("Недели в часы: %f недель = %.2f часов\n", input, result);
                    }
                    case 5 -> {
                        rate = 10080;
                        result = input * rate;
                        out.printf("Недели в минуты: %f недель = %.2f минут\n", input, result);
                    }
                    case 6 -> {
                        rate = 604800;
                        result = input * rate;
                        out.printf("Недели в секунды: %f недель = %.2f секунд\n", input, result);
                    }
                    case 7 -> {
                        rate = 604800310;
                        result = input * rate;
                        out.printf("Недели в миллисекунды: %f недель = %.2f миллисекунды\n", input, result);
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
                        out.printf("Дни в года: %f дней = %.4f лет\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.142857216;
                        result = input * rate;
                        out.printf("Дни в недели: %f дней = %.2f недель\n", input, result);
                    }
                    case 4 -> {
                        rate = 24;
                        result = input * rate;
                        out.printf("Дни в часы: %f дней = %.2f часов\n", input, result);
                    }
                    case 5 -> {
                        rate = 1440;
                        result = input * rate;
                        out.printf("Дни в минуты: %f дней = %.2f минут\n", input, result);
                    }
                    case 6 -> {
                        rate = 86400;
                        result = input * rate;
                        out.printf("Дни в секунды: %f дней = %.2f секунд\n", input, result);
                    }
                    case 7 -> {
                        rate = 86400044;
                        result = input * rate;
                        out.printf("Дни в миллисекунды: %f дней = %.2f миллисекунды\n", input, result);
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
                        out.printf("Часы в года: %f часов = %.4f лет\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.005952;
                        result = input * rate;
                        out.printf("Часы в недели: %f часов = %.3f недель\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.0417;
                        result = input * rate;
                        out.printf("Часы в дни: %f часов = %.2f дней\n", input, result);
                    }
                    case 5 -> {
                        rate = 60;
                        result = input * rate;
                        out.printf("Часы в минуты: %f часов = %.2f минут\n", input, result);
                    }
                    case 6 -> {
                        rate = 3600;
                        result = input * rate;
                        out.printf("Часы в секунды: %f часов = %.2f секунд\n", input, result);
                    }
                    case 7 -> {
                        rate = 3600000;
                        result = input * rate;
                        out.printf("Часы в миллисекунды: %f часов = %.2f миллисекунды\n", input, result);
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
                        out.printf("Минуты в года: %f минут = %.7f лет\n", input, result);
                    }
                    case 2 -> {
                        rate = 9.920640000000052793e-5;
                        result = input * rate;
                        out.printf("Минуты в недели: %f минут = %.6f недель\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.00069;
                        result = input * rate;
                        out.printf("Минуты в дни: %f минут = %.4f дней\n", input, result);
                    }
                    case 4 -> {
                        rate = 0.01667;
                        result = input * rate;
                        out.printf("Минуты в часы: %f минут = %.2f часов\n", input, result);
                    }
                    case 6 -> {
                        rate = 60;
                        result = input * rate;
                        out.printf("Минуты в секунды: %f минут = %.2f секунд\n", input, result);
                    }
                    case 7 -> {
                        rate = 60000;
                        result = input * rate;
                        out.printf("Минуты в миллисекунды: %f минут = %.2f миллисекунды\n", input, result);
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
                        out.printf("Секунды в года: %f секунд = %.8f лет\n", input, result);
                    }
                    case 2 -> {
                        rate = 1.65e-6;
                        result = input * rate;
                        out.printf("Секунды в недели: %f секунд = %.6f недель\n", input, result);
                    }
                    case 3 -> {
                        rate = 1.157e-5;
                        result = input * rate;
                        out.printf("Секунды в дни: %f секунд = %.5f дней\n", input, result);
                    }
                    case 4 -> {
                        rate = 0.00028;
                        result = input * rate;
                        out.printf("Секунды в часы: %f секунд = %.4f часов\n", input, result);
                    }
                    case 5 -> {
                        rate = 0.017;
                        result = input * rate;
                        out.printf("Секунды в минуты: %f секунд = %.2f минут\n", input, result);
                    }
                    case 7 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Секунды в миллисекунды: %f секунд = %.2f миллисекунды\n", input, result);
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
                        out.printf("Миллисекунды в года: %f миллисекунд = %.11f лет\n", input, result);
                    }
                    case 2 -> {
                        rate = 1.653e-9;
                        result = input * rate;
                        out.printf("Миллисекунды в недели: %f миллисекунд = %.9f недель\n", input, result);
                    }
                    case 3 -> {
                        rate = 1.157e-8;
                        result = input * rate;
                        out.printf("Миллисекунды в дни: %f миллисекунд = %.8f дней\n", input, result);
                    }
                    case 4 -> {
                        rate = 2.78e-7;
                        result = input * rate;
                        out.printf("Миллисекунды в часы: %f миллисекунд = %.7f часов\n", input, result);
                    }
                    case 5 -> {
                        rate = 1.67e-5;
                        result = input * rate;
                        out.printf("Миллисекунды в минуты: %f миллисекунд = %.5f минут\n", input, result);
                    }
                    case 6 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Миллисекунды в секунды: %f миллисекунд = %.3f секунд\n", input, result);
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
        switch (choice) {
            //Квадратные километры во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Квадратные километры в Гектары: %f кв.км = %.2f гектаров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf("Квадратные километры в Ары: %f кв.км = %.2f ар\n", input, result);
                    }
                    case 4 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf("Квадратные километры в Квадратные метры: %f кв.км = %.0f кв.м\n", input, result);
                    }
                    case 5 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf("Квадратный километры в Квадратные дециметры: %f кв.км = %.0f кв.дм\n", input, result);
                    }
                    case 6 -> {
                        rate = 1E10;
                        result = input * rate;
                        out.printf("Квадратные километры в Квадратные сантиметры: %f кв.км = %.0f кв.см\n", input, result);
                    }
                    case 7 -> {
                        rate = 1E12;
                        result = input * rate;
                        out.printf("Квадратные километры в Квадратные миллиметры: %f кв.км = %.0f кв.мм\n", input, result);
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
                        out.printf("Гектары в квадратные километры: %f гектаров = %.2f кв.км\n", input, result);
                    }
                    case 3 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Гектары в квадратные в Ары: %f гектаров = %.2f ар\n", input, result);
                    }
                    case 4 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf("Гектары в квадратные метры: %f гектаров = %.2f кв.м\n", input, result);
                    }
                    case 5 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf("Гектары в квадратные дециметры: %f гектаров = %.0f кв.дм\n", input, result);
                    }
                    case 6 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf("Гектары в квадратные сантиметры: %f гектаров = %.0f кв.см\n", input, result);
                    }
                    case 7 -> {
                        rate = 1E10;
                        result = input * rate;
                        out.printf("Гектары в квадратные миллиметры: %f гектаров = %.0f кв.мм\n", input, result);
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
                        out.printf("Ары в квадратные километры: %f ар = %.4f кв.км\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Ары в гектары: %f ар = %.2f гектаров\n", input, result);
                    }
                    case 4 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Ары в квадратные метры: %f ар = %.2f кв.м\n", input, result);
                    }
                    case 5 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf("Ары в квадратные дециметры: %f ар = %.2f кв.дм\n", input, result);
                    }
                    case 6 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf("Ары в квадратные сантиметры: %f ар = %.0f кв.см\n", input, result);
                    }
                    case 7 -> {
                        rate = 1E8;
                        result = input * rate;
                        out.printf("Ары в квадратные миллиметры: %f ар = %.0f кв.мм\n", input, result);
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
                        out.printf("Квадратные метры в квадратные километры: %f кв.м = %.6f кв.км\n", input, result);
                    }
                    case 2 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf("Квадратные метры в гектары: %f кв.м = %.4f гектаров\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Квадратные метры в ары: %f кв.м = %.2f ар\n", input, result);
                    }
                    case 5 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf("Квадратные метры в квадратные дециметры: %f кв.м = %.2f кв.дм\n", input, result);
                    }
                    case 6 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf("Квадратные метры в квадратные сантиметры: %f кв.м = %.2f кв.см\n", input, result);
                    }
                    case 7 -> {
                        rate = 1E6;
                        result = input * rate;
                        out.printf("Квадратные метры в квадратные миллиметры: %f кв.м = %.0f кв.мм\n", input, result);
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
                        out.printf("Квадратные дециметры в квадратные километры: %f кв.дм = %.8f кв.км\n", input, result);
                    }
                    case 2 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf("Квадратные дециметры в гектары: %f кв.дм = %.6f гектаров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf("Квадратные дециметры в ары: %f кв.дм = %.4f ар\n", input, result);
                    }
                    case 4 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Квадратные дециметры в квадратные метры: %f кв.дм = %.2f кв.м\n", input, result);
                    }
                    case 6 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Квадратные дециметры в квадратные сантиметры: %f кв.дм = %.2f кв.см\n", input, result);
                    }
                    case 7 -> {
                        rate = 1E4;
                        result = input * rate;
                        out.printf("Квадратные дециметры в квадратные миллиметры: %f кв.дм = %.2f кв.мм\n", input, result);
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
                        out.printf("Квадратные сантиметры в квадратные километры: %f кв.см = %.10f кв.км\n", input, result);
                    }
                    case 2 -> {
                        rate = 1E-8;
                        result = input * rate;
                        out.printf("Квадратные сантиметры в гектары: %f кв.см = %.8f гектаров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf("Квадратные сантиметры в ары: %f кв.см = %.6f ар\n", input, result);
                    }
                    case 4 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf("Квадратные сантиметры в квадратные метры: %f кв.см = %.4f кв.м\n", input, result);
                    }
                    case 5 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Квадратные сантиметры в квадратные децииметры: %f кв.см = %.2f кв.дм\n", input, result);
                    }
                    case 7 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Квадратные сантиметры в квадратные миллиметры: %f кв.см = %.2f кв.мм\n", input, result);
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
                        out.printf("Квадратные миллиметры в квадратные километры: %f кв.мм = %.12f кв.км\n", input, result);
                    }
                    case 2 -> {
                        rate = 1E-10;
                        result = input * rate;
                        out.printf("Квадратные миллиметры в гектары: %f кв.мм = %.10f гектаров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1E-8;
                        result = input * rate;
                        out.printf("Квадратные миллиметры в ары: %f кв.мм = %.8f ар\n", input, result);
                    }
                    case 4 -> {
                        rate = 1E-6;
                        result = input * rate;
                        out.printf("Квадратные миллиметры в квадратные метры: %f кв.мм = %.6f кв.м\n", input, result);
                    }
                    case 5 -> {
                        rate = 1E-4;
                        result = input * rate;
                        out.printf("Квадратные миллиметры в квадратные децииметры: %f кв.мм = %.4f кв.дм\n", input, result);
                    }
                    case 6 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Квадратные миллииметры в квадратные сантииметры: %f кв.мм = %.2f кв.см\n", input, result);
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
        switch (choice) {
            //Кубические метры во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Кубические метры в Кубические дециметры: %f куб.м = %.2f куб.дм\n", input, result);
                    }
                    case 3 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Кубические метры в Кубические сантиметры: %f куб.м = %.0f куб.см\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000000000;
                        result = input * rate;
                        out.printf("Кубические метры в Кубические миллиметры: %f куб.м = %.0f куб.мм\n", input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Кубические метры в Литры: %f куб.м = %.2f литров\n", input, result);
                    }
                    case 6 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Кубические метры в Миллилитры: %f куб.м = %.0f миллилитров\n", input, result);
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
                        out.printf("Кубические дециметры в Кубические метры: %f куб.дм = %.3f куб.м\n", input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Кубические дециметры в Кубические сантиметры: %f куб.дм = %.2f куб.см\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Кубические дециметры в Кубические миллиметры: %f куб.дм = %.0f куб.мм\n", input, result);
                    }
                    case 5 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf("Кубические дециметры в Литры: %f куб.дм = %.2f литров\n", input, result);
                    }
                    case 6 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Кубические дециметры в Миллилитры: %f куб.дм = %.2f миллилитров\n", input, result);
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
                        out.printf("Кубические сантиметры в Кубические метры: %f куб.см = %.6f куб.м\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Кубические сантиметры в Кубические дециметры: %f куб.см = %.3f куб.дм\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Кубические сантиметры в Кубические миллиметры: %f куб.см = %.2f куб.мм\n", input, result);
                    }
                    case 5 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Кубические сантиметры в Литры: %f куб.см = %.3f литров\n", input, result);
                    }
                    case 6 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf("Кубические сантиметры в Миллилитры: %f куб.см = %.2f миллилитров\n", input, result);
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
                        out.printf("Кубические миллиметры в Кубические метры: %f куб.мм = %.9f куб.м\n", input, result);
                    }
                    case 2 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf("Кубические миллиметры в Кубические дециметры: %f куб.мм = %.6f куб.дм\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Кубические миллиметры в Кубические сантиметры: %f куб.мм = %.3f куб.см\n", input, result);
                    }
                    case 5 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf("Кубические миллиметры в Литры: %f куб.мм = %.6f литров\n", input, result);
                    }
                    case 6 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Кубические миллиметры в Миллилитры: %f куб.мм = %.3f миллилитров\n", input, result);
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
                        out.printf("Литры в Кубические метры: %f литров = %.3f куб.м\n", input, result);
                    }
                    case 2 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf("Литры в Кубические дециметры: %f литров = %.2f куб.дм\n", input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Литры в Кубические сантиметры: %f литров = %.2f куб.см\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Литры в Кубические миллиметры: %f литров = %.0f куб.мм\n", input, result);
                    }
                    case 6 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Литры в Миллилитры: %f литров = %.2f миллилитров\n", input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Литры во все величины
            case 6 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf("Миллилитры в Кубические метры: %f миллилитров = %.6f куб.м\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Миллилитры в Кубические дециметры: %f миллилитров = %.3f куб.дм\n", input, result);
                    }
                    case 3 -> {
                        rate = 1;
                        result = input * rate;
                        out.printf("Миллилитры в Кубические сантиметры: %f миллилитров = %.2f куб.см\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Миллилитры в Кубические миллиметры: %f миллилитров = %.2f куб.мм\n", input, result);
                    }
                    case 5 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Миллилитры в Литры: %f миллилитров = %.3f литров\n", input, result);
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
        switch (choice) {
            //Метры в секунду во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 3.6;
                        result = input * rate;
                        out.printf("М/с в км/ч: %f м/с = %.2f км/ч\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("М/с в км/с: %f м/с = %.3f км/с\n", input, result);
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
                        out.printf("Км/ч в м/с: %f км/ч = %.2f м/с\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.000277778;
                        result = input * rate;
                        out.printf("км/ч в км/с: %f км/ч = %.4f км/с\n", input, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }//Километры в секунду во все велчины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Км/с в м/с: %f км/с = %.2f м/с\n", input, result);
                    }
                    case 2 -> {
                        rate = 3600;
                        result = input * rate;
                        out.printf("км/с в км/ч: %f км/с = %.2f км/ч\n", input, result);
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
        switch (choice) {
            //Градусы Цельсия во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        result = (input * 9 / 5) + 32;
                        out.printf("Градусы Цельсия в Градусы Фаренгейта: %f %sC = %.2f %sF\n", input, grad, result, grad);
                    }
                    case 3 -> {
                        result = input + 273.15;
                        out.printf("Градусы Цельсия в Кельвины: %f %sC = %.2f K\n", input, grad, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Градусы Фаренгейта во все величины
            case 2 -> {
                switch (output) {
                    case 1 -> {
                        result = (input - 32) * 5 / 9;
                        out.printf("Градусы Фаренгейта в Градусы Цельсия: %f %sF = %.2f %sC\n", input, grad, result, grad);
                    }
                    case 3 -> {
                        result = (input - 32) * 5 / 9 + 273.15;
                        out.printf("Градусы Фаренгейта в Кельвины: %f %sF = %.2f K\n", input, grad, result);
                    }
                    default -> throw new IllegalStateException(WRONG_CODE);
                }
            }
            //Кельвины во все величины
            case 3 -> {
                switch (output) {
                    case 1 -> {
                        result = input - 273.15;
                        out.printf("Кельвины в Градусы Цельсия: %f K = %.2f %sC\n", input, result, grad);
                    }
                    case 2 -> {
                        result = (input - 273.15) * 9 / 5 + 32;
                        out.printf("Кельвины в Градусы Фаренгейта: %f K= %.2f %sF\n", input, result, grad);
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
        switch (choice) {
            //Тонны во все величины
            case 1 -> {
                switch (output) {
                    case 2 -> {
                        rate = 10;
                        result = input * rate;
                        out.printf("Тонны в Центнеры: %f тонн = %.2f центнеров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Тонны в Килограммы: %f тонн = %.2f кг\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000000;
                        result = input * rate;
                        out.printf("Тонны в Граммы: %f тонн = %.0f г\n", input, result);
                    }
                    case 5 -> {
                        rate = 1000000000;
                        result = input * rate;
                        out.printf("Тонны в Миллиграммы: %f тонн = %.0f мг\n", input, result);
                    }
                    case 6 -> {
                        rate = 2204.62;
                        result = input * rate;
                        out.printf("Тонны в Фунты: %f тонн = %.2f фунтов\n", input, result);
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
                        out.printf("Центнеры в Тонны: %f центнеров = %.2f тонн\n", input, result);
                    }
                    case 3 -> {
                        rate = 100;
                        result = input * rate;
                        out.printf("Центнеры в Килограммы: %f центнеров = %.2f кг\n", input, result);
                    }
                    case 4 -> {
                        rate = 100000;
                        result = input * rate;
                        out.printf("Центнеры в Граммы: %f центнеров = %.0f г\n", input, result);
                    }
                    case 5 -> {
                        rate = 1e+8;
                        result = input * rate;
                        out.printf("Центнеры в Миллиграммы: %f центнеров = %.0f мг\n", input, result);
                    }
                    case 6 -> {
                        rate = 220.462;
                        result = input * rate;
                        out.printf("Центнеры в Фунты: %f центнеров = %.2f фунтов\n", input, result);
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
                        out.printf("Килограммы в Тонны: %f кг = %.3f тонн\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.01;
                        result = input * rate;
                        out.printf("Килограммы в Центнеры: %f кг = %.2f центнеров\n", input, result);
                    }
                    case 4 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Килограммы в Граммы: %f кг = %.2f г\n", input, result);
                    }
                    case 5 -> {
                        rate = 1e+6;
                        result = input * rate;
                        out.printf("Килограммы в Миллиграммы: %f кг = %.0f мг\n", input, result);
                    }
                    case 6 -> {
                        rate = 2.20462;
                        result = input * rate;
                        out.printf("Килограммы в Фунты: %f кг = %.2f фунтов\n", input, result);
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
                        out.printf("Граммы в Тонны: %f г = %.6f тонн\n", input, result);
                    }
                    case 2 -> {
                        rate = 1e-5;
                        result = input * rate;
                        out.printf("Граммы в Центнеры: %f г = %.5f центнеров\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Граммы в Килограммы: %f г = %.3f кг\n", input, result);
                    }
                    case 5 -> {
                        rate = 1000;
                        result = input * rate;
                        out.printf("Граммы в Миллиграммы: %f г = %.2f мг\n", input, result);
                    }
                    case 6 -> {
                        rate = 0.00220462;
                        result = input * rate;
                        out.printf("Граммы в Фунты: %f г = %.3f фунтов\n", input, result);
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
                        out.printf("Миллиграммы в Тонны: %f мг = %.9f тонн\n", input, result);
                    }
                    case 2 -> {
                        rate = 1e-8;
                        result = input * rate;
                        out.printf("Миллиграммы в Центнеры: %f мг = %.8f центнеров\n", input, result);
                    }
                    case 3 -> {
                        rate = 1e-6;
                        result = input * rate;
                        out.printf("Миллиграммы в Килограммы: %f мг = %.6f кг\n", input, result);
                    }
                    case 4 -> {
                        rate = 0.001;
                        result = input * rate;
                        out.printf("Миллиграммы в Граммы: %f мг = %.3f г\n", input, result);
                    }
                    case 6 -> {
                        rate = 2.20462e-6;
                        result = input * rate;
                        out.printf("Миллиграммы в Фунты: %f мг = %.6f фунтов\n", input, result);
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
                        out.printf("Фунты в Тонны: %f фунтов = %.4f тонн\n", input, result);
                    }
                    case 2 -> {
                        rate = 0.00453592;
                        result = input * rate;
                        out.printf("Фунты в Центнеры: %f фунтов = %.3f центнеров\n", input, result);
                    }
                    case 3 -> {
                        rate = 0.453592;
                        result = input * rate;
                        out.printf("Фунты в Килограммы: %f фунтов = %.2f кг\n", input, result);
                    }
                    case 4 -> {
                        rate = 453.592;
                        result = input * rate;
                        out.printf("Фунты в Граммы: %f фунтов = %.2f г\n", input, result);
                    }
                    case 5 -> {
                        rate = 453592;
                        result = input * rate;
                        out.printf("Фунты в Миллиграммы: %f фунтов = %.2f мг\n", input, result);
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