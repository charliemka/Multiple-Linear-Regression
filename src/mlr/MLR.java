package mlr;

/**
 *
 * @author Carlos Varela
 */
public class MLR {

    public static void main(String[] args) {
// Dataset obtained from: https://www.randomservices.org/random/data/Galton.html 
        double[][] heightData = {
//           x1  x2   y               
            {66, 66, 66},
            {70, 63.5, 63.5},
            {70, 63.7, 62.2},
            {71, 64, 65.5},
            {68, 65, 72},
            {70.5, 64, 65},
            {69.5, 62, 69},
            {68.2, 63.5, 65.5},
            {72.5, 62, 71},
            {71, 69, 67},
            {65.5, 63, 69},
            {69, 63.5, 61},
            {71, 63, 63},
            {66.5, 63, 65},
            {66, 59, 58},
            {68.5, 65, 69.2},
            {71, 67, 69},
            {70, 60, 65},
            {69, 63.5, 70.5},
            {69, 62.5, 71},};
/*          Where x1 = height of father, x2 = height of mother and
            y = height of offspring.  
            **note** height is calculated in inches. 
*/
        DataSet data = new DataSet(heightData);
        Procedure mlr = new Procedure(data);
        mlr.sums();
        mlr.CramerDS();
        mlr.CramerB0();
        mlr.CramerB1();
        mlr.CramerB2();
        mlr.Results();
    }
}
