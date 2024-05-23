package test2_sample;

public class CuongSecretSearch {
    double XA;
    double YA;
    double VA;
    double XB;
    double YB;
    double VB;

    public CuongSecretSearch(double XA, double YA, double VA, double XB, double YB, double VB) {
        this.XA = XA;
        this.YA = YA;
        this.VA = VA;
        this.XB = XB;
        this.YB = YB;
        this.VB = VB;
    }

    public double timeFromA(double XZ) {
        double distance = Math.sqrt((XA - XZ) * (XA - XZ) + YA * YA);
        return (double) Math.round(distance / VA * 1000000) / 1000000;
    }

    private double timeFromB(double XZ) {
        double distance = Math.sqrt((XB - XZ) * (XB - XZ) + YB * YB);
        return (double) Math.round(distance / VB * 1000000) / 1000000;
    }

    public double pointC() {
        double eps = 0.0000001;
        double minX = XA;
        double maxX = XB;
        while (maxX - minX > eps) {
            double mid = (minX + maxX) / 2;
            double tA = timeFromA(mid);
            double tB = timeFromB(mid);
            if (tA < tB) {
                minX = mid;
            } else {
                maxX = mid;
            }
        }
        return (double) Math.round(maxX * 1000000) / 1000000;
    }

    public static void main(String[] args) {
        CuongSecretSearch ss = new CuongSecretSearch(-1, 1, 1, 1, -1, 0.5);
        System.out.println(ss.timeFromA(0));
        System.out.println(ss.pointC());
    }
}
