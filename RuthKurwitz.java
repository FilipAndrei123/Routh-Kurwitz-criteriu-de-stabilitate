
import java.util.Scanner;

import static java.lang.Math.abs;

public class RuthKurwitz {



    public static void main(String[] args) {
        int gradpol, i, j,m,n;
        double eps=0.01;
        System.out.println("Dati gradul polinomului");
        Scanner in = new Scanner(System.in);
        gradpol = in.nextInt();
        System.out.println("Dati coefientii polinomului");
        float[] v = new  float[gradpol + 1];
        for (i = 0; i <= gradpol; i++) {
            System.out.println("Coeficientul de grad " + i);
            v[i] = in.nextInt();
        }
        n=gradpol + 1;
        m=gradpol / 2 + 1;
        float[][] matriceRK = new float[n][m];
        for( i = 0; i < n; i++)
            for(j = 0; j < m; j++)
                matriceRK[i][j] = 0;

        if (gradpol % 2 == 0) {
            int ok=0;
            for (i = 1; i <= gradpol-2; i=i+2)
            {
                if(v[i]==0) {
                    if (v[i] == v[i + 2])
                        ok = 1;
                    if (v[i] != v[i + 2])
                        ok = 0;

                }

            }
            if(ok==1)
                for (i = 1; i <= gradpol; i=i+2)
                {
                    v[i]=v[i+1]*(i+1);
                }

            int gp1=gradpol;
            int gp=gradpol-1;
            for (j = 0; j < m; j++) {
                matriceRK[0][j] = v[gp1];
                gp1=gp1-2;
            }
            for (j = 0; j < m-1; j++) {
                matriceRK[1][j] = v[gp];
                gp=gp-2;
            }
            matriceRK[1][m-1] = 0;
        }

        if (gradpol % 2 == 1) {
            int ok=0;
            for (i = 0; i <= gradpol-2; i=i+2)
            {
                if(v[i]==0) {
                    if (v[i] == v[i + 2])
                        ok = 1;
                    if (v[i] != v[i + 2])
                        ok = 0;

                }

            }
            if(ok==1)
                for (i = 0; i <= gradpol; i=i+2)
                {
                    v[i]=v[i+1]*(i+1);
                }


            int gp1=gradpol;
            int gp=gradpol-1;
            for (j = 0; j < m; j++) {
                matriceRK[0][j] = v[gp1];
                gp1=gp1-2;
            }
            for (j = 0; j < m; j++) {
                matriceRK[1][j] = v[gp];
                gp=gp-2;
            }
        }


        for( i =0; i < n; i++)
        {
            float min = abs(matriceRK[i][0]);
            int ok=1;

            for (j = 1; j < m; j++){
                if (min > abs(matriceRK[i][j]))
                    if(abs(matriceRK[i][j])!=0)
                        min=abs(matriceRK[i][j]);
            }

            for (j = 0; j < m; j++)
                if(abs(matriceRK[i][j])%min!=0)
                    ok=0;


            if(ok==1)
                for (j = 0; j < m; j++)
                    matriceRK[i][j]=matriceRK[i][j]/min;

        }



        int z=m;
        for( i = 2; i < n; i++) {

            if(matriceRK[i-1][z-1]==0 || z%2==0) {
                z=z-1;
            }

            for (j = 0; j < z; j++)
                if (matriceRK[i - 1][j] != 0)
                    matriceRK[i][j] = (matriceRK[i - 1][j] * matriceRK[i - 2][j + 1] - matriceRK[i - 2][j] * matriceRK[i - 1][j + 1]) / matriceRK[i - 1][j];
                else
                if (matriceRK[i - 1][j] == 0)
                    matriceRK[i][j] =  matriceRK[i - 2][j + 1] - matriceRK[i - 2][j] * matriceRK[i - 1][j + 1]*100;
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                System.out.printf("%.2f", matriceRK[i][j]);
                System.out.print(" | ");
            }
            System.out.println();
        }
        System.out.println();

        int schimbare=0;
        for (i = 0; i < n-1; i++) {
            if(matriceRK[i][0]>=0&&matriceRK[i+1][0]<0)
                schimbare++;
            if(matriceRK[i][0]<=0&&matriceRK[i+1][0]>0)
                schimbare++;
        }
        System.out.println("Numarul de radacini in semiplanul drept sunt :"  +schimbare);
    }
}