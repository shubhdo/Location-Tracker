package social.com.locationtutorial;

/**
 * Created by HP-User on 2/7/2017.
 */

public class Model {
    public double mLatitude;
    public double mLongitude;
    public String DeviceId;

    public Model(double mLatitude, double mLongitude, String deviceId) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        DeviceId = deviceId;
    }

    public Model() {

    }



}
