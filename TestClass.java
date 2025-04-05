interface myInterface{
    public String drivingSpeed();
}

 enum Vehiclespeed implements myInterface{

    SLOW(20){public String drivingSpeed(){return "slow";}},

    MEDIUN(60){public String drivingSpeed(){return "Medium";}},

    FAST(120){public String drivingSpeed(){return "Fast";}};

    private int speed;

    public void VehicleSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
}


public class TestClass{

    public static void main(String[] args) {

        Vehiclespeed v1 = VehicleSpeed.SLOW(20);
        System.out.println(v1.drivingSpeed() + ": " + v1.getSpeed()) ;

        VehicleSpeed v2 = VehicleSpeed.FAST(120);
        System.out.println(v2.drivingSpeed() + ": " + v2.getSpeed());

    }
}