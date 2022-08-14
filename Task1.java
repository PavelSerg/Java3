// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.

public class ferzi {
    protected int SIZE ;
    protected int board[][];
    protected int results_count = 0;
    private int threadsCount;
    public ferzi(){
    }
    public ferzi(int N){
        SIZE=N;
        board=new int [N][N];
        //zan=new boolean [N][N];
        results_count=0;
    }
    public boolean tryQueen(int a, int b){
        for (int i = 0; i < a; ++i)
            if (board[i][b]==1)
                return false;
        for (int i = 1; i <= a && b - i >= 0; ++i)
            if (board[a - i][b - i]==1)
                return false;
 
        for (int i = 1; i <= a && b + i < SIZE; i++)
            if (board[a - i][b + i]==1)
                return false;
 
        return true;
    }
    public void setQueen(int a) throws InterruptedException {
        for(int i = 0; i < SIZE; ++i){
            if (tryQueen(a, i)) {
                board[a][i] = 1;
                Thread thr = new MyThread(this,a+1);
                thr.start();
                thr.join();
                board[a][i] = 0;
            }
        }
        return;
    }
    public  void showBoard(){
        for (int a = 0; a < SIZE; ++a)
        {
            for (int b = 0; b < SIZE; ++b)
            {
                System.out.print((board[a][b]==1) ? "Q " : ". ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (int a = 0; a < SIZE; ++a) {
            for (int b = 0; b < SIZE; ++b) {
                if(board[a][b]==1)
                    builder.append( "Q ");
                else
                    builder.append( ". ");
            }
        }
        String completedString = builder.toString();
        return completedString;
    }
    public void run(){
        try {
            if (position == clock.SIZE) {
                ++clock.results_count;
                System.out.print("Result #" + clock.results_count + '\n');
                clock.showBoard();
                return;
            }
            for (int i = 0; i < clock.SIZE; ++i) {
                if (clock.tryQueen(position, i)) {
                    clock.board[position][i] = 1;
                    Thread thr = new MyThread(clock, position + 1);
                    thr.start();
                    thr.join();
                    clock.board[position][i] = 0;
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
