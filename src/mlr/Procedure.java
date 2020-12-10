package mlr;

import java.util.Scanner;

/**
 *
 * @author Carlos Varela
 */
public class Procedure {

    Scanner inputs = new Scanner(System.in);
    //Atributes
    double B0, B1, B2, DS;
    double sumX1 = 0;
    double sumX2 = 0;
    double sumY = 0;
    double sumX1sq = 0;
    double sumX2sq = 0;
    double sumX1Y = 0;
    double sumX2Y = 0;
    double sumX1X2 = 0;
    double Mtx[][];
    double Matrix[][];
    
    DataSet ds;

    //Constructor for dataset
    public Procedure(DataSet _ds) {
        this.ds = _ds;
    }
    //Methods
//Procedimiento previo necesario para el calculo de mlr 
    // x1 = pos 0,x2 = pos 1,y = pos 3
    public void sums() {
        double[][] data = ds.getData();
        for (int i = 0; i < data.length; i++) {
            sumX1 += data[i][0];
            sumX2 += data[i][1];
            sumY += data[i][2];
            sumX1sq += data[i][0] * data[i][0];
            sumX2sq += data[i][1] * data[i][1];
            sumX1Y += data[i][0] * data[i][2];
            sumX2Y += data[i][1] * data[i][2];
            sumX1X2 += data[i][0] * data[i][1];
        }
        double[][] Mtx = {
            {data.length, sumX1, sumX2, sumY},
            {sumX1, sumX1sq, sumX1X2, sumX1Y},
            {sumX2, sumX1X2, sumX2sq, sumX2Y}
        };
        Matrix = Mtx;
        Mtx = null;
    }
//Matriz Determinante del sistema
    public void CramerDS() {
       
        DS = (((Matrix[0][0] * Matrix[1][1] * Matrix[2][2])
                + (Matrix[0][1] * Matrix[1][2] * Matrix[0][2])
                + (Matrix[0][2] * Matrix[1][0] * Matrix[2][1]))
                - ((Matrix[2][0] * Matrix[1][1] * Matrix[0][2])
                + (Matrix[2][1] * Matrix[1][2] * Matrix[0][0])
                + (Matrix[2][2] * Matrix[1][0] * Matrix[0][1])));
    }

   //Matriz determinante de B0
    public void CramerB0() {

        B0 = (((Matrix[0][3] * Matrix[1][1] * Matrix[2][2])
                + (Matrix[0][1] * Matrix[1][2] * Matrix[2][3])
                + (Matrix[0][2] * Matrix[1][3] * Matrix[2][1])
                - ((Matrix[2][3] * Matrix[1][1] * Matrix[0][2])
                + (Matrix[2][1] * Matrix[1][2] * Matrix[0][3])
                + (Matrix[2][2] * Matrix[1][3] * Matrix[0][1]))));
        B0 = B0 / DS;
    }
    //Matriz determinante de B0
    public void CramerB1() {
        B1 = (((Matrix[0][0] * Matrix[1][3] * Matrix[2][2])
                + (Matrix[0][3] * Matrix[1][2] * Matrix[2][0])
                + (Matrix[0][2] * Matrix[1][0] * Matrix[2][3]))
                - ((Matrix[0][2] * Matrix[1][3] * Matrix[0][2])
                + (Matrix[2][3] * Matrix[1][2] * Matrix[0][0])
                + (Matrix[2][2] * Matrix[1][0] * Matrix[0][3])));
        B1 = B1 / DS;
    }
    //Matriz determinante de B0
    public void CramerB2() {

        B2 = (((Matrix[0][0] * Matrix[1][1] * Matrix[2][3])
                + (Matrix[0][1] * Matrix[1][3] * Matrix[2][0])
                + (Matrix[0][3] * Matrix[1][0] * Matrix[2][1]))
                - ((Matrix[2][0] * Matrix[1][1] * Matrix[0][3])
                + (Matrix[2][1] * Matrix[1][3] * Matrix[0][0])
                + (Matrix[2][3] * Matrix[1][0] * Matrix[0][1])));
        B2 = B2 / DS;
    }

    //Method scans for x1 & x2 then procedes to make the prediction and prints the results 
    public void Results() {
        System.out.println("Input an x1 value: ");
        double x1 = inputs.nextDouble();
        System.out.println("Input an x2 value: ");
        double x2 = inputs.nextDouble();
        double y = B0 + (B1 * x1) + (B2 * x2);
        System.out.println("\nThe prediction made with "+x1+" inches as the x1 value and "+x2+ " inches as the x2 value is: "+ y+" inches");
        System.out.println("Equation: " + "y = " + B0 + " + " + B1 + "x1 + " + B2 + "x2");
    }
}
