public class HashingDoble {

      movil[] hashArray;
      int arraySize;
      int size = 0; // numero de elementos

      public HashingDoble(int numDeSlots) {
            if (esPrimo(numDeSlots)) {
                  hashArray = new movil[numDeSlots];
                  arraySize = numDeSlots;
            } else {
                  int numPrimo = getNextPrime(numDeSlots);
                  hashArray = new movil[numPrimo];
                  arraySize = numPrimo;

                  System.out.println("El tamaño dado para la Tabla Hash: " + numDeSlots + " no es un numero primo");
                  System.out.println("El tamaño de la tabla Hash fue cambiado a: " + numPrimo);
            }
      }

      public void insert(movil M) {
            int hashVal = hashFunc1(M.getId());
            int stepSize = hashFunc2(M.getId());

            while (hashArray[hashVal] != null) {
                  hashVal = hashVal + stepSize;
                  hashVal = hashVal % arraySize;
            }

            hashArray[hashVal] = M;

            System.out.println("Elemento insertado: " + M.getId());
            size++;
      }

      public int find(int M) {
            int hashVal = hashFunc1(M);
            int stepSize = hashFunc2(M);

            while (hashArray[hashVal] != null) {
                  if (hashArray[hashVal].getId() == M) {
                        return hashArray[hashVal].getId();
                  }
                  hashVal = hashVal + stepSize;
                  hashVal = hashVal % arraySize;

            }
            return hashArray[hashVal].getId();
      }

      // imprime la tabla hash
      public void displayTable() {
            System.out.println("Tabla: ");
            for (int i = 0; i < arraySize; i++) {
                  if (hashArray[i] != null) {
                        System.out.print("[" + i + "] " + hashArray[i].getId() + ", " + hashArray[i].getT1() + ", "
                                    + hashArray[i].getT2() + ", " + hashArray[i].getX() + ", " + hashArray[i].getY());
                  } else {
                        System.out.print("[" + i + "] " + "**");
                  }
                  System.out.println("");
            }
      }

      // Imprime la trayectoria del movil ingresado en tiempo O(l)
      public void Trayectoria(int M) {
            int hashVal = hashFunc1(M);
            int stepSize = hashFunc2(M);
            int plus = 0;
            System.out.println("Trayectoria del movil " + M + ": ");
            while (hashArray[hashVal] != null) {
                  if (hashArray[hashVal] != null && hashArray[hashVal].getId() == M) {
                        System.out.println(hashArray[hashVal].toString());
                  }
                  plus++;
                  stepSize = stepSize * plus;
                  hashVal = hashVal + stepSize;
                  hashVal = hashVal % arraySize;

            }
      }

      // Metodo que navega por la trayectoria del movil ingresado guardando sus
      // posiciones en tiempo O(l)
      // Termina imprimiendo la suma de todas sus posiciones
      public void Distancia(int M) {
            int hashVal = hashFunc1(M);
            int stepSize = hashFunc2(M);
            int plus = 0;
            coordenada P1 = null;
            coordenada P2 = null;
            double distancia = 0;
            System.out.println("Distancia de la trayectoria del movil " + M + ": ");
            while (hashArray[hashVal] != null) {
                  if (hashArray[hashVal].getId() == M) {
                        int x = hashArray[hashVal].getX();
                        int y = hashArray[hashVal].getY();
                        P1 = new coordenada(x, y);
                  }
                  plus++;
                  stepSize = stepSize * plus;
                  hashVal = hashVal + stepSize;
                  hashVal = hashVal % arraySize;

                  if (hashArray[hashVal] != null && hashArray[hashVal].getId() == M) {
                        int x = hashArray[hashVal].getX();
                        int y = hashArray[hashVal].getY();
                        P2 = new coordenada(x, y);
                        distancia += dist(P1, P2);

                  }

            }
            System.out.println(distancia);

      }

      // Método que permite verificar si ambos móviles posiblemente estuvieron
      // cercanos.
      public boolean PosiblementeCercanos(int m1, int m2, int delta) {
            int hashVal1 = hashFunc1(m1);
            int stepSize1 = hashFunc2(m1);

            int hashVal2 = hashFunc1(m2);
            int stepSize2 = hashFunc2(m2);

            int plus = 0;
            coordenada P1 = null;
            coordenada P2 = null;
            System.out.print("Los moviles " + m1 + " y " + m2 + ", estuvieron posiblemente cercanos? R: ");
            while (hashArray[hashVal1] != null) {
                  while (hashArray[hashVal2] != null) {
                        if (hashArray[hashVal1].getId() == m1) {
                              if (hashArray[hashVal1].getT1() == hashArray[hashVal2].getT1()
                                          || hashArray[hashVal1].getT1() == hashArray[hashVal2].getT2()
                                          || hashArray[hashVal1].getT2() == hashArray[hashVal2].getT2()
                                          || hashArray[hashVal1].getT2() == hashArray[hashVal2].getT1()) {

                                    int x1 = hashArray[hashVal1].getX();
                                    int y1 = hashArray[hashVal1].getY();
                                    P1 = new coordenada(x1, y1);
                                    int x2 = hashArray[hashVal2].getX();
                                    int y2 = hashArray[hashVal2].getY();
                                    P2 = new coordenada(x2, y2);
                              }
                        }
                        plus++;
                        stepSize1 = stepSize1 * plus;
                        hashVal1 = hashVal1 + stepSize1;
                        hashVal1 = hashVal1 % arraySize;
                        stepSize2 = stepSize2 * plus;
                        hashVal2 = hashVal2 + stepSize2;
                        hashVal2 = hashVal2 % arraySize;
                  }

                  if (dist(P1, P2) < delta) {
                        return true;
                  }
            }

            return false;
      }

      ///// METODOS PRIVADOS /////

      // Metodo privado para calcular la distancia entre dos puntos
      private double dist(coordenada P1, coordenada P2) {
            double distancia = 0;
            if (P1 != null && P2 != null) {
                  distancia = Math.sqrt((P1.getX() - P2.getX()) * (P1.getX() - P2.getX())
                              + (P1.getY() - P2.getY()) * (P1.getY() - P2.getY()));
            }

            return distancia;
      }

      // Verifica si el tamaño del array es un numero primo
      private boolean esPrimo(int num) {
            for (int i = 2; i * i <= num; i++) {
                  if (num % i == 0) {
                        return false;
                  }
            }
            return true;
      }

      // Si el tamaño de la lista no es primo lo cambia al siguiente numero primo
      // cercano
      private int getNextPrime(int minNum) {
            for (int i = minNum; true; i++) {
                  if (esPrimo(i)) {
                        return i;
                  }
            }
      }

      // retorna index mayor que cero
      private int hashFunc1(int num) {
            int hashVal = num;
            hashVal = hashVal % arraySize;

            if (hashVal < 0) {
                  hashVal += arraySize;
            }
            return hashVal; // donde se va a insertar
      }

      // retorna el tamaño del salto mayor que cero
      private int hashFunc2(int num) {
            int hashVal = num;
            hashVal = hashVal % arraySize;

            if (hashVal < 0) {
                  hashVal += arraySize;
            }

            return 3 - hashVal % 3; // Se usa un numero primo menor que el numero del tamaño del array
      }

}
