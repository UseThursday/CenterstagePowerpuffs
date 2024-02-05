/*
 * Copyright (c) 2016 Titan Robotics Club (http://www.titanrobotics.com)
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

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import TrcCommonLib.trclib.TrcRobot;
import TrcCommonLib.trclib.TrcSensor;
import TrcFtcLib.ftclib.FtcDashboard;
import TrcFtcLib.ftclib.FtcI2cAdaFruitColorSensor;
import TrcFtcLib.ftclib.FtcOpMode;

/**
 * This opmode demonstrates the use of the AdaFruit Color Sensor.
 */
@TeleOp(name="Test: AdaFruit Color Sensor", group="TrcFtcSamples")
@Disabled
public class FtcTestAdaFruitColorSensor extends FtcOpMode
{
    private FtcDashboard dashboard;
    private FtcI2cAdaFruitColorSensor sensor;

    //
    // Implements FtcOpMode abstract method.
    //

    @Override
    public void robotInit()
    {
        hardwareMap.logDevices();
        dashboard = FtcDashboard.getInstance();
        dashboard.setTextView(
            ((FtcRobotControllerActivity)hardwareMap.appContext)
                .findViewById(com.qualcomm.ftcrobotcontroller.R.id.textOpMode));
        sensor = new FtcI2cAdaFruitColorSensor("adaFruitColorSensor");
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
            final int LABEL_WIDTH = 100;
            TrcSensor.SensorData<Integer> data;

            dashboard.displayPrintf(1, LABEL_WIDTH, "ID: ", "%d", sensor.getID());
            dashboard.displayPrintf(2, LABEL_WIDTH, "Status: ", "%02x", sensor.getStatus());

            //
            // The data may not be ready yet, check it!
            //

            data = sensor.getClearValue();
            if (data.value != null)
            {
                dashboard.displayPrintf(3, LABEL_WIDTH, "Clear: ", "%d", data.value);
            }

            data = sensor.getRedValue();
            if (data.value != null)
            {
                dashboard.displayPrintf(4, LABEL_WIDTH, "Red: ", "%d", data.value);
            }

            data = sensor.getGreenValue();
            if (data.value != null)
            {
                dashboard.displayPrintf(5, LABEL_WIDTH, "Green: ", "%d", data.value);
            }

            data = sensor.getBlueValue();
            if (data.value != null)
            {
                dashboard.displayPrintf(6, LABEL_WIDTH, "Blue: ", "%d", data.value);
            }
        }
    }   //periodic

}   //class FtcTestAdaFruitColorSensor
