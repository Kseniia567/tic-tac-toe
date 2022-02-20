package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // write your code here
        System.out.println("Enter cells:");
        char[][] field = new char[3][3];
        for (char[] chars : field) {
            Arrays.fill(chars, '_');
        }
        showField(field);
        char figure = 'X';
        System.out.println("Enter the coordinates:");
        String coordinates = sc.nextLine();
        while (gameIsNotFinished(field)) {
            while (!isPossible(coordinates, field)) {
                String[] numb = coordinates.split(" ");
                if (numb[0].matches("\\d+") && numb[1].matches("\\d+")) {
                    int a = Integer.parseInt(numb[0]);
                    int b = Integer.parseInt(numb[1]);
                    if (a >= 1 && a <= 3 && b >= 1 && b <= 3) {
                        a = a - 1;
                        b = Math.abs(b - 3);
                        if (field[b][a] != '_') {
                            System.out.println("This cell is occupied! Choose another one!");
                            System.out.println("Enter the coordinates:");
                        }
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                        System.out.println("Enter the coordinates:");
                    }
                } else {
                    System.out.println("You should enter numbers!");
                    System.out.println("Enter the coordinates:");
                }
                coordinates = sc.nextLine();
            }
            String[] numb = coordinates.split(" ");
            int a = Integer.parseInt(numb[0]);
            int b = Integer.parseInt(numb[1]);
            a = a - 1;
            b = Math.abs(b - 3);
            field[b][a] = figure;
            figure = figure == 'X' ? 'O' : 'X';
            showField(field);
            if (gameIsNotFinished(field)) {
                System.out.println("Enter the coordinates:");
                coordinates = sc.nextLine();
            }
        }
        showResult(field);

    }

    public static boolean winX(char[][] arr) {
        boolean win = false;
        for (char[] chars : arr) {
            win = chars[0] == 'X' && chars[1] == 'X' && chars[2] == 'X';
            if (win) break;
        }
        for (int i = 0; i < arr[0].length; i++) {
            if (!win) {
                win = arr[0][i] == 'X' && arr[1][i] == 'X' && arr[2][i] == 'X';
                if (win) break;
            }
        }
        if (arr[0][0] == 'X' && arr[1][1] == 'X' && arr[2][2] == 'X') {
            win = true;
        } else if (arr[2][0] == 'X' && arr[1][1] == 'X' && arr[0][2] == 'X') {
            win = true;
        }
        return win;
    }

    public static boolean winO(char[][] arr) {
        boolean win = false;
        for (char[] chars : arr) {
            win = chars[0] == 'O' && chars[1] == 'O' && chars[2] == 'O';
            if (win) break;
        }
        for (int i = 0; i < arr[0].length; i++) {
            if (!win) {
                win = arr[0][i] == 'O' && arr[1][i] == 'O' && arr[2][i] == 'O';
                if (win) break;
            }
        }
        if (arr[0][0] == 'O' && arr[1][1] == 'O' && arr[2][2] == 'O') {
            win = true;
        } else if (arr[2][0] == 'O' && arr[1][1] == 'O' && arr[0][2] == 'O') {
            win = true;
        }
        return win;
    }

    public static boolean draw(char[][] arr) {
        boolean draw = false;
        if (!winX(arr) && !winO(arr) && !checkEmpty(arr)) {
            draw = true;
        }
        return draw;
    }

    public static boolean checkEmpty(char[][] arr) {
        boolean empty = false;
        for (char[] chars : arr) {
            for (char aChar : chars) {
                if (aChar == '_') {
                    empty = true;
                    break;
                }
                if (empty) {
                    break;
                }
            }
        }
        return empty;

    }

    public static boolean gameIsNotFinished(char[][] arr) {
        boolean finish = false;
        int counterX = 0;
        int counterO = 0;
        if (checkEmpty(arr)) {
            for (char[] chars : arr) {
                for (char aChar : chars) {
                    if (aChar == 'X') {
                        counterX++;
                    } else if (aChar == 'O') {
                        counterO++;
                    }
                }
            }
            finish = Math.abs(counterX - counterO) <= 1;
        }
        if (winX(arr) || winO(arr)) {
            finish = false;
        }

        return finish;
    }

    public static boolean impossible(char[][] arr) {
        boolean impossible = false;
        int counterX = 0;
        int counterO = 0;
        if (checkEmpty(arr)) {
            for (char[] chars : arr) {
                for (char aChar : chars) {
                    if (aChar == 'X') {
                        counterX++;
                    } else if (aChar == 'O') {
                        counterO++;
                    }
                }
            }
            impossible = Math.abs(counterX - counterO) > 1;
        }
        if (winX(arr) && winO(arr)) {
            impossible = true;
        }
        return impossible;
    }

    public static void showResult (char[][] field) {
        if (impossible(field)) {
            System.out.println("Impossible");
        } else if (draw(field)) {
            System.out.println("Draw");
        } else if (winX(field)) {
            System.out.println("X wins");
        } else if (winO(field)) {
            System.out.println("O wins");
        } else if (gameIsNotFinished(field)) {
            System.out.println("Game not finished");
        }
    }

    public static boolean isPossible(String str, char[][] arr) {
        String[] numb = str.split(" ");
        if (numb[0].matches("\\d+") && numb[1].matches("\\d+")) {
            int a = Integer.parseInt(numb[0]);
            int b = Integer.parseInt(numb[1]);
            if (a >= 1 && a <= 3 && b >= 1 && b <= 3) {
            a = a - 1;
            b = Math.abs(b - 3);
                return arr[b][a] == '_';
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void showField(char[][] field) {
        System.out.println("---------");
        for (char[] chars : field) {
            System.out.printf("| %c %c %c |%n", chars[0], chars[1], chars[2]);
        }
        System.out.println("---------");
    }
}
