///*
// * Copyright (c) 2015 Titan Robotics Club (http://www.titanrobotics.com)
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
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import TrcCommonLib.trclib.TrcEvent;
//import TrcCommonLib.trclib.TrcRobot;
//import TrcCommonLib.trclib.TrcStateMachine;
//import TrcFtcLib.ftclib.FtcOpMode;
//
///**
// * This opmode demonstrates PID line following using a state machine and without using a Robot Command object.
// */
//@Autonomous(name="Auto: K9Bot PID Line Following", group="TrcFtcSamples")
//@Disabled
//public class FtcAutoK9PidLineFollow extends FtcOpMode
//{
//    //
//    // State machine states.
//    //
//    private enum State
//    {
//        FIND_LINE,
//        TURN_TO_LINE,
//        FOLLOW_LINE,
//        DONE
//    }
//
//    private K9Robot robot;
//    //
//    // Event and state machine.
//    //
//    private TrcEvent event;
//    private TrcStateMachine<State> sm;
//
//    //
//    // Implements FtcOpMode abstract methods.
//    //
//
//    @Override
//    public void robotInit()
//    {
//        robot = new K9Robot();
//        //
//        // State machine.
//        //
//        event = new TrcEvent("driveEvent");
//        sm = new TrcStateMachine<>("lineFollow");
//    }   //robotInit
//
//    //
//    // Overrides TrcRobot.RobotMode methods.
//    //
//
//    @Override
//    public void startMode(TrcRobot.RunMode prevMode, TrcRobot.RunMode nextMode)
//    {
//        robot.startMode();
//        //
//        // Start state machine at state FIND_LINE.
//        //
//        sm.start(State.FIND_LINE);
//    }   //startMode
//
//    @Override
//    public void stopMode(TrcRobot.RunMode prevMode, TrcRobot.RunMode nextMode)
//    {
//        robot.stopMode();
//    }   //stopMode
//
//    @Override
//    public void periodic(double elapsedTime, boolean slowPeriodicLoop)
//    {
//        State state = sm.checkReadyAndGetState();
//
//        if (state == null)
//        {
//            robot.dashboard.displayPrintf(1, "State: disabled or waiting...");
//        }
//        else
//        {
//            robot.dashboard.displayPrintf(1, "State: %s", state);
//
//            switch (state)
//            {
//                case FIND_LINE:
//                    //
//                    // Go forward slowly for 3 ft to find the line.
//                    // If line is detected, PID drive will be interrupted.
//                    //
//                    robot.setColorTriggerEnabled(true);
//                    // Drive slowly, limit to half power.
//                    robot.pidDrive.getYPidCtrl().setOutputLimit(0.5);
//                    robot.pidDrive.setRelativeYTarget(36.0, event);
//                    sm.waitForSingleEvent(event, State.TURN_TO_LINE);
//                    break;
//
//                case TURN_TO_LINE:
//                    //
//                    // We have past the line slightly, so turn left or right 90 degree
//                    // slowly to find the edge of the line. If the line is detected,
//                    // PID turn will be interrupted.
//                    //
//                    robot.pidDrive.getTurnPidCtrl().setOutputLimit(0.5);
//                    robot.pidDrive.setRelativeTurnTarget(90.0, event);
//                    sm.waitForSingleEvent(event, State.FOLLOW_LINE);
//                    break;
//
//                case FOLLOW_LINE:
//                    //
//                    // Slowly follow the line for 5 ft.
//                    //
//                    robot.setColorTriggerEnabled(false);
//                    robot.colorPidLineFollow.getYPidCtrl().setOutputLimit(0.3);
//                    robot.colorPidLineFollow.getTurnPidCtrl().setOutputLimit(0.3);
//                    //
//                    // Follow right edge if red alliance.
//                    // Follow left edge if blue alliance.
//                    //
//                    robot.colorPidLineFollow.setSensorTarget(
//                            0.0, 60.0, K9Robot.LIGHT_THRESHOLD, event);
//                    sm.waitForSingleEvent(event, State.DONE);
//                    break;
//
//                case DONE:
//                default:
//                    //
//                    // We are done, restore everything and stop!
//                    //
//                    robot.pidDrive.getYPidCtrl().setOutputLimit(1.0);
//                    robot.pidDrive.getTurnPidCtrl().setOutputLimit(1.0);
//                    robot.colorPidLineFollow.getYPidCtrl().setOutputLimit(1.0);
//                    robot.colorPidLineFollow.getTurnPidCtrl().setOutputLimit(1.0);
//                    sm.stop();
//                    break;
//            }
//        }
//    }   //periodic
//
//}   //class FtcAutoK9PidLineFollow
