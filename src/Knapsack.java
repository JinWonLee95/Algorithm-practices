import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Knapsack {
    static int[][] cal;
    static int item;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("data11.txt"));
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String[] stuff;
        ArrayList<String[]> stuffs = new ArrayList<>();

        String temp;
        while ((temp = br.readLine()) != null) {
            stuff = temp.split(",");
            stuffs.add(stuff);
        }

        int[] weights = new int[stuffs.size()];
        int[] values = new int[stuffs.size()];

        for (int i = 0; i < stuffs.size(); i++) {
            weights[i] = Integer.parseInt(stuffs.get(i)[2]);
            values[i] = Integer.parseInt(stuffs.get(i)[1]);
        }

        int W;
        while (true) {
            System.out.print("배낭의 사이즈를 입력하세요(0-50): ");
            W = Integer.parseInt(sc.readLine());
            if (W >= 0 && W <= 50)
                break;
            else
                System.out.println("사이즈는 0에서 50사이만 가능합니다.");
        }

        OPT(W, weights, values, stuffs.size());
    }

    static void OPT(int W, int wt[], int val[], int n) {
        cal = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    cal[i][w] = 0;
                else if (wt[i - 1] <= w)
                    cal[i][w] = max(val[i - 1] + cal[i - 1][w - wt[i - 1]], cal[i - 1][w]);
                else
                    cal[i][w] = cal[i - 1][w];

                item = i - 1; //item 알아보기 위해
                System.out.print(cal[i][w] + "\t");
            }
            System.out.println();
        }
        System.out.println("max : " + cal[n][W]);
        System.out.println("item : " + (item - 1) + ", " + item);
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }


}