///*
// * Copyright (c) 2016 Titan Robotics Club (http://www.titanrobotics.com)
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package TrcFtcSamples;
//
//import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
//import TrcCommonLib.trclib.TrcRobot;
//import TrcCommonLib.trclib.TrcSensor;
//import TrcFtcLib.ftclib.FtcDashboard;
//import TrcFtcLib.ftclib.FtcMRI2cRangeSensor;
//import TrcFtcLib.ftclib.FtcOpMode;
//
///**
// * This opmode demonstrates the use of Modern Robotics Range Sensor.
// */
//@TeleOp(name="Test: MR Range Sensor", group="TrcFtcSamples")
//@Disabled
//public class FtcTestMRRangeSensor extends FtcOpMode
//{
//    private FtcDashboard dashboard;
//    private FtcMRI2cRangeSensor i2cRangeSensor;
//    private ModernRoboticsI2cRangeSensor rangeSensor;
//
//    //
//    // Implements FtcOpMode abstract methods.
//    //
//
//    @Override
//    public void robotInit()
//    {
//        hardwareMap.logDevices();
//        dashboard = FtcDashboard.getInstance();
//        dashboard.setTextView(
//            ((FtcRobotControllerActivity)hardwareMap.appContext)
//                .findViewById(com.qualcomm.ftcrobotcontroller.R.id.textOpMode));
//
//        i2cRangeSensor = new FtcMRI2cRangeSensor("i2cRangeSensor");
//        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "rangeSensor");
//    }   //robotInit
//
//    //
//    // Overrides TrcRobot.RobotMode methods.
//    //
//
//    @Override
//    public void startMode(TrcRobot.RunMode prevMode, TrcRobot.RunMode nextMode)
//    {
//        dashboard.clearDisplay();
//    }   //startMode
//
//    @Override
//    public void periodic(double elapsedTime, boolean slowPeriodicLoop)
//    {
//        if (slowPeriodicLoop)
//        {
//            final int LABEL_WIDTH = 200;
//            dashboard.displayPrintf(1, LABEL_WIDTH, "Range: ", "%.3f in", rangeSensor.getDistance(DistanceUnit.INCH));
//            dashboard.displayPrintf(2, LABEL_WIDTH, "FirmwareRev: ", "%x", i2cRangeSensor.getFirmwareRevision());
//            dashboard.displayPrintf(3, LABEL_WIDTH, "ManufacturerCode: ", "%x", i2cRangeSensor.getManufacturerCode());
//            dashboard.displayPrintf(4, LABEL_WIDTH, "IDCode: ", "%x", i2cRangeSensor.getIdCode());
//            TrcSensor.SensorData<Double> ultrasonicDistance = i2cRangeSensor.getUltrasonicDistance();
//            TrcSensor.SensorData<Double> opticalDistance = i2cRangeSensor.getOpticalDistance();
//            //
//            // The data may not be ready yet, check it!
//            //
//            if (ultrasonicDistance.value != null)
//            {
//                dashboard.displayPrintf(5, LABEL_WIDTH, "Ultrasonic distance: ", "%.0f", ultrasonicDistance.value);
//            }
//
//            if (opticalDistance.value != null)
//            {
//                dashboard.displayPrintf(6, LABEL_WIDTH, "Optical distance: ", "%.0f", opticalDistance.value);
//            }
//        }
//    }   //periodic
//
//}   //class FtcTestMRRangeSensor
