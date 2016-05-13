package ch.robot.controller;

import java.io.IOException;

import ch.robot.controller.exception.RobotSamplingException;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.remote.ev3.RemoteRequestSampleProvider;
import lejos.robotics.RegulatedMotor;

public class RobotController{
	
	RegulatedMotor mRight;
	RegulatedMotor mLeft;
    RemoteRequestSampleProvider lightProvider;
    RemoteRequestEV3 remEv;
	final int MOVEMENTSPEED=500;
	final int MINIMUMBRIGHTNESS=1;

	
	public void connect(String ipAddress) throws IOException{
			 remEv = new RemoteRequestEV3(ipAddress);
		
	}
	
	public void connectPorts(){
			mRight = remEv.createRegulatedMotor("A", 'L');
			mLeft = remEv.createRegulatedMotor("D", 'L');
            lightProvider = (RemoteRequestSampleProvider) remEv.createSampleProvider("S2", "lejos.hardware.EV3ColorSensor", "ambient");
		
	}
	
	public void disconnectPorts() throws IOException {
		try{
		if(mRight != null){
			mRight.close();
		}
		if(mLeft != null){
			mLeft.close();
		}
		if(lightProvider !=null){
			lightProvider.close();
		}
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
		
	}
	
	public void turnLeft() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mLeft.stop(false);
		mLeft.forward();
		try{
		Thread.sleep(666);
		}catch(Exception ex){
			System.out.println(ex);
		}
		mLeft.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void startTurnLeft() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mLeft.stop(false);
		mLeft.forward();
		try{
		Thread.sleep(666);
		}catch(Exception ex){
			System.out.println(ex);
		}
		mLeft.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void turnRight() throws IOException{
		try{
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mRight.forward();
		try{
		Thread.sleep(666);
		}catch(Exception ex){
			System.out.println(ex);
		}
		mRight.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	
	public void startTurnRight() throws IOException{
		try{
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mRight.forward();
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void forward() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mLeft.stop(false);
		mLeft.forward();
		mRight.forward();
		try{
		Thread.sleep(666);
		}catch(Exception ex){
			System.out.println(ex);
		}
		mLeft.stop(true);
		mRight.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void startForward() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mLeft.stop(false);
		mLeft.forward();
		mRight.forward();
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void stopMovementMotors() throws IOException{
		try{
		mLeft.stop(true);
		mRight.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void backward() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mLeft.stop(false);
		mLeft.backward();
		mRight.backward();
		try{
		Thread.sleep(666);
		}catch(Exception ex){
			System.out.println(ex);
		}
		mLeft.stop(true);
		mRight.stop(true);
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
	public void startBackward() throws IOException{
		try{
		mLeft.setSpeed(MOVEMENTSPEED);
		mRight.setSpeed(MOVEMENTSPEED);
		mRight.stop(false);
		mLeft.stop(false);
		mLeft.backward();
		mRight.backward();
		}catch(Exception ex){
			throw new IOException(ex.getMessage());
		}
	}
	
    public boolean isTooDark() throws RobotSamplingException{
        try {
            float[] sample = null;
            lightProvider.fetchSample(sample, 1);;
            double darkness = sample[0];
            if(darkness < 1){
                return true;
            }
        } catch (Exception ex) {
            throw new RobotSamplingException(ex.getMessage());
        }
        return false;
    }

}
