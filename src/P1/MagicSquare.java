package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MagicSquare {
	public static void main(String[] args) {
		
		  System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\1.txt"));
		 System.out.print("\n");
		  System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\2.txt"));
		  System.out.print("\n");
		  System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\3.txt"));
		  System.out.print("\n");
		  System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\4.txt"));
		  System.out.print("\n");
		  System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\5.txt"));
		 System.out.print("\n"); System.out.print("\n");
		
		generateMagicSquare(11);
		System.out.print(isLegalMagicSquare(".\\src\\p1\\txt\\6.txt"));
	}

	static boolean isLegalMagicSquare(String fileName) {
		int[][] array = new int[150][150];
		int length = 0;
		int line = 0;
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				try {
					String[] split = tempString.split("\t");
					int n;
					length = split.length;
					for (n = 0; n < split.length; n++) {
						try {
							int t = Integer.parseInt(split[n]);
							array[line][n] = t;
						} catch (NumberFormatException e) {
							e.printStackTrace();
							return false;
						}
					}
					line++;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return false;
				}
				// 显示行号

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		if (length != line) {
			System.out.printf("不是矩阵");
			return false;
		}
		if (checkarray(array, length) == false) {
			System.out.printf("不是矩阵或者输入的数据不是正整数");
			return false;
		}
		int rowsum[] = new int[length];
		int clumnssum[] = new int[length];
		int diagonalsum[] = new int[2];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				rowsum[i] = rowsum[i] + array[i][j];
				clumnssum[i] = clumnssum[i] + array[j][i];

			}
			diagonalsum[0] = diagonalsum[0] + array[i][i];
			diagonalsum[1] = diagonalsum[1] + array[i][length - i - 1];
		}
		int a = 1, b = 1, c = 1;
		for (int i = 1; i < length; i++) {
			if (rowsum[i] != rowsum[0])
				a = 0;
			if (clumnssum[i] != clumnssum[0])
				b = 0;

		}
		if (diagonalsum[0] != diagonalsum[01])
			c = 0;
		if (a == 1 && b == 1 && c == 1)
			return true;
		else
			return false;
	}

	private static boolean checkarray(int[][] array, int m) {
		// TODO Auto-generated method stub
		for (int i = 0; i < m; i++)
			for (int j = 0; j < m; j++) {
				if (array[i][j] == 0) {
					return false;
				}
			}
		return true;
	}

	public static boolean generateMagicSquare(int n) {
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		try {
			for (i = 1; i <= square; i++) {
				magic[row][col] = i;
				if (i % n == 0)
					row++;
				else {
					if (row == 0)
						row = n - 1;
					else
						row--;
					if (col == (n - 1))
						col = 0;
					else
						col++;
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			return false;

		}
		try {
			FileWriter fw = new FileWriter(".\\src\\p1\\txt\\6.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			for (i = 0; i < n; i++) {

				for (j = 0; j < n; j++) {

					bw.write(magic[i][j] + "\t");// 往已有的文件上添加字符串

					System.out.print(magic[i][j] + "\t");
				}
				bw.write("\r\n");
				System.out.print("\n");
			}
			bw.close();
			fw.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return true;
	}
}