import java.io.*;
import java.util.*;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            if (args.length == 2) {
                // Read matrices from files
                String file1 = args[0];
                String file2 = args[1];
                int[][] matrix1 = readMatrixFromFile(file1);
                int[][] matrix2 = readMatrixFromFile(file2);

                if (!canMultiplyMatrices(matrix1, matrix2)) {
                    System.out.println("Matrix multiplication requirements not satisfied.");
                    return;
                }

                int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
                writeMatrixToFile(resultMatrix, "matrix3.txt");
                System.out.println("Matrix multiplication complete. Results saved to matrix3.txt.");
            } else if (args.length == 1) {
                try {
                    int size = Integer.parseInt(args[0]);
                    int[][] matrix1 = generateRandomMatrix(size);
                    int[][] matrix2 = generateRandomMatrix(size);

                    writeMatrixToFile(matrix1, "matrix1.txt");
                    writeMatrixToFile(matrix2, "matrix2.txt");

                    int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
                    writeMatrixToFile(resultMatrix, "matrix3.txt");
                    System.out.println("Matrix multiplication complete. Results saved to matrix3.txt.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid integer input.");
                }
            } else {
                System.out.println("Enter two file names or an integer:");
                String input = scanner.nextLine();
                String[] inputs = input.split(" ");

                if (inputs.length == 2) {
                    String file1 = inputs[0];
                    String file2 = inputs[1];
                    int[][] matrix1 = readMatrixFromFile(file1);
                    int[][] matrix2 = readMatrixFromFile(file2);

                    if (!canMultiplyMatrices(matrix1, matrix2)) {
                        System.out.println("Matrix multiplication requirements not satisfied.");
                        return;
                    }

                    int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
                    writeMatrixToFile(resultMatrix, "matrix3.txt");
                    System.out.println("Matrix multiplication complete. Results saved to matrix3.txt.");
                } else if (inputs.length == 1) {
                    try {
                        int size = Integer.parseInt(inputs[0]);
                        int[][] matrix1 = generateRandomMatrix(size);
                        int[][] matrix2 = generateRandomMatrix(size);

                        writeMatrixToFile(matrix1, "matrix1.txt");
                        writeMatrixToFile(matrix2, "matrix2.txt");

                        int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);
                        writeMatrixToFile(resultMatrix, "matrix3.txt");
                        System.out.println("Matrix multiplication complete. Results saved to matrix3.txt.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer input.");
                    }
                } else {
                    System.out.println("Invalid input.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int[][] readMatrixFromFile(String fileName) throws IOException {
        List<int[]> matrixList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                int[] row = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Integer.parseInt(tokens[i]);
                }
                matrixList.add(row);
            }
        }
        return matrixList.toArray(new int[0][0]);
    }

    private static void writeMatrixToFile(int[][] matrix, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int[] row : matrix) {
                for (int value : row) {
                    writer.write(value + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int[][] generateRandomMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(10);  // Random integers between 0 and 9
            }
        }
        return matrix;
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    private static boolean canMultiplyMatrices(int[][] matrix1, int[][] matrix2) {
        return matrix1[0].length == matrix2.length;
    }
}
