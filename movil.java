public class movil {

      private int id;
      private coordenada XY;
      private intervalo T;
      public movil() {

      }

      public movil(int id, int t1, int t2, int x, int y) {
            this.id = id;
            XY = new coordenada(x, y);
            T = new intervalo(t1, t2);
      }

      public int getId() {
            return id;
      }

      public int getT1() {
            return T.getT1();
      }

      public int getT2() {
            return T.getT2();
      }

      public int getX() {
            return XY.getX();
      }

      public int getY() {
            return XY.getY();
      }

      public String toString() {
            return "[" + getT1() + "," + getT2() + "]" + "," + "(" + getX() + "," + getY() + ")";
      }
}
