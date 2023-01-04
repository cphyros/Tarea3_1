
public class testHashingDoble {
      public static void main(String[] args) {
            HashingDoble table = new HashingDoble(7);

            int M1 = 1020;
            int M2 = 1050;

            movil m1 = new movil(M1, 1, 8, 500, 600);
            movil m1_1 = new movil(M1, 9, 10, 250, 300);
            movil m1_2 = new movil(M1, 15, 18, 300, 1000);
            
            movil m2 = new movil(M2, 2, 10, 100, 200);
            movil m2_1 = new movil(M2, 20, 30, 100, 400);
            movil m2_2 = new movil(M2, 32, 40, 200, 200);

            table.insert(m1);
            table.insert(m1_1);
            table.insert(m1_2);
            
            table.insert(m2);
            table.insert(m2_1);
            table.insert(m2_2);

            System.out.println("");

            table.displayTable();

            System.out.println("");

            table.Trayectoria(M2);
            
            System.out.println("");
            
            table.Distancia(M2);

            System.out.println("");
            
            System.out.println(table.PosiblementeCercanos(M1, M2, 185));
            //"185" es el distancia minima para declarar que estuvieron cercanos

      }
}
