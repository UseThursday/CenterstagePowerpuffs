/*
 * Copyright (c) 2015 Titan Robotics Club (http://www.titanrobotics.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package TrcFtcSamples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import TrcCommonLib.trclib.TrcRobot;
import TrcCommonLib.trclib.TrcSensor;
import TrcFtcLib.ftclib.FtcDashboard;
import TrcFtcLib.ftclib.FtcMRI2cColorSensor;
import TrcFtcLib.ftclib.FtcOpMode;

/**
 * This opmode demonstrates the use of I2C Color Sensor.
 */
@TeleOp(name="Test: I2C Color Sensor", group="TrcFtcSamples")
@Disabled
public class FtcTestI2cColorSensor extends FtcOpMode
{
    private static final int ALTERNATE_I2CADDRESS = 0x40;

    private FtcDashboard dashboard;
    private FtcMRI2cColorSensor i2cColorSensor;
    private ColorSensor colorSensor;

    //
    // Implements FtcOpMode abstract methods.
    //

    @Override
    public void robotInit()
    {
        hardwareMap.logDevices();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTextView(
            ((FtcRobotControllerActivity)hardwareMap.appContext)
                .findViewById(com.qualcomm.ftcrobotcontroller.R.id.textOpMode));

//        i2cColorSensor = new FtcMRI2cColorSensor("i2cColorSensor");
        i2cColorSensor = new FtcMRI2cColorSensor("i2cColorSensor", ALTERNATE_I2CADDRESS, false);
//        i2cColorSensor.setLEDEnabled(false);
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
    }   //robotInit

    //
    // Overrides TrcRobot.RobotMode methods.
    //

    @Override
    public void startMode(TrcRobot.RunMode prevMode, TrcRobot.RunMode nextMode)
    {
        dashboard.clearDisplay();
    }   //startMode

    @Override
    public void periodic(double elapsedTime, boolean slowPeriodicLoop)
    {
        if (slowPeriodicLoop)
        {
            final int LABEL_WIDTH = 200;
            dashboard.displayPrintf(1, LABEL_WIDTH, "FirmwareRev: ", "%x", i2cColorSensor.getFirmwareRevision());
            dashboard.displayPrintf(2, LABEL_WIDTH, "ManufacturerCode: ", "%x", i2cColorSensor.getManufacturerCode());
            dashboard.displayPrintf(3, LABEL_WIDTH, "IDCode: ", "%x", i2cColorSensor.getIdCode());
            TrcSensor.SensorData<Double> data = i2cColorSensor.getColorNumber();
            //
            // The data may not be ready yet, check it!
            //
            if (data.value != null)
            {
                dashboard.displayPrintf(4, LABEL_WIDTH, "ColorNumber: ", "%.0f", i2cColorSensor.getColorNumber().value);
                dashboard.displayPrintf(5, LABEL_WIDTH, "RedValue: ", "%.0f", i2cColorSensor.getRedValue().value);
                dashboard.displayPrintf(6, LABEL_WIDTH, "GreenValue: ", "%.0f", i2cColorSensor.getGreenValue().value);
                dashboard.displayPrintf(7, LABEL_WIDTH, "BlueValue: ", "%.0f", i2cColorSensor.getBlueValue().value);
                dashboard.displayPrintf(8, LABEL_WIDTH, "WhiteValue: ", "%.0f", i2cColorSensor.getWhiteValue().value);
            }
            dashboard.displayPrintf(9, LABEL_WIDTH, "W/R/G/B: ", "%02x%02x%02x%02x (%d/%d/%d/%d)",
                                    colorSensor.alpha(), colorSensor.red(), colorSensor.green(), colorSensor.blue(),
                                    colorSensor.alpha(), colorSensor.red(), colorSensor.green(), colorSensor.blue());
            dashboard.displayPrintf(10, LABEL_WIDTH, "Hue: ", "%08x", colorSensor.argb());
        }
    }   //periodic

}   //class FtcTestI2cColorSensor
