// Шахматную доску размером NxN обойти конём так, чтобы фигура в каждой клетке была строго один раз.


import java.util.Arrays;
 
class Main
{
    // Шахматная доска `N × N`
    public static final int N = 5;
 
    // Ниже массивы детализируют все восемь возможных движений коня.
    // Не меняйте последовательность следующих массивов
    public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2, 2 };
    public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };
 
    // Проверяем, являются ли `(x, y)` действительными координатами шахматной доски.
    // Обратите внимание, что конь не может выйти за пределы доски
    private static boolean isValid(int x, int y)
    {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }
 
        return true;
    }
 
    private static void print(int[][] visited)
    {
        for (var r: visited) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println();
    }
 
    // Рекурсивная функция для выполнения обхода рыцаря с Backtracking
    public static void knightTour(int[][] visited, int x, int y, int pos)
    {
        // отметить текущий квадрат как посещенный
        visited[x][y] = pos;
 
        // если все квадраты посещены, выводим решение
        if (pos >= N*N)
        {
            print(visited);
            // откат перед возвратом
            visited[x][y] = 0;
            return;
        }
 
        // проверка всех восьми возможных движений коня
        // и повторяться для каждого допустимого движения
        for (int k = 0; k < 8; k++)
        {
            // получаем новую позицию коня из текущей
            // позиция на шахматной доске
            int newX = x + row[k];
            int newY = y + col[k];
 
            // если новая позиция действительна и еще не посещена
            if (isValid(newX, newY) && visited[newX][newY] == 0) {
                knightTour(visited, newX, newY, pos + 1);
            }
        }
 
        // вернуться из текущего квадрата и удалить его из текущего пути
        visited[x][y] = 0;
    }
 
    public static void main(String[] args)
    {
        // `visited[][]` служит двум целям:
        // 1. Он отслеживает поля, задействованные в обходе коня.
        // 2. Хранит порядок посещения квадратов.
        int[][] visited = new int[N][N];
        int pos = 1;
 
        // начинаем обход конем с углового квадрата `(0, 0)`
        knightTour(visited, 0, 0, pos);
    }
}







