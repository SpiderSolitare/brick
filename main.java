package main;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Main {

	private static EV3TouchSensor t = new EV3TouchSensor(SensorPort.S1);
	public static long milkTime = 1500;
	public static long muesliTime = 5000;
	public static boolean isRunning;
	public static Thread k;
	public static Thread kk;

	public static UnregulatedMotor m1 = new UnregulatedMotor(MotorPort.B);
	public static UnregulatedMotor m2 = new UnregulatedMotor(MotorPort.A);
	public static UnregulatedMotor m3 = new UnregulatedMotor(MotorPort.C);

	public static SampleProvider sp = t.getTouchMode();
	public static float[] sample = new float[sp.sampleSize()];

	public static void main(String[] args) throws InterruptedException {

		System.out.println("duden");
		// message
		// Brick brick = BrickFinder.getDefault();

		kk = new Thread(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				System.out.print("started 2");
				// TODO Auto-generated method stub
				while (k.isAlive()) {
					while(isRunning) {
					Delay.msDelay(100);
					sp.fetchSample(sample, 0);
					if (sample[0] == 0) {
						k.stop();
						isRunning = false;
						m1.setPower(100);
						m1.forward();
						try {
							Thread.sleep(milkTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						m1.stop();
					}
					}
				}
			}
		});

		k = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.print("started 2");
				// TODO Auto-generated method stub
				while (true) {
					sp.fetchSample(sample, 0);
					if (sample[0] == 1) {
						isRunning = true;
						// vür vortnight

						System.out.println("started");
						m1.setPower(100);
						m1.backward();
						try {
							Thread.sleep(milkTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						m1.stop();

						try {
							Thread.sleep(8000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						m1.setPower(100);
						m1.forward();
						try {
							Thread.sleep(milkTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						m1.stop();

						// vor da müsli

						m2.setPower(75);
						m3.setPower(100);
						m2.backward();
						m3.forward();
						try {
							Thread.sleep(muesliTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						m2.stop();
						m3.stop();

						// Delay Time
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isRunning = false;
					}
				}
			}
		});

		kk.start();
		k.start();

//		while (true) {
//
//			
//			if(sample[0] == 1 && isRunning == true) {
//				System.out.println("Programm interruoted");
//				m1.setPower(100);
//				m1.forward();
//				Thread.sleep(milkTime);
//				m1.stop();
//			}
//			
//			if (sample[0] == 1) {
//				System.out.println("started...");
//				long time = System.currentTimeMillis();
//				while (sample[0] == 1) {
//					m.setPower(100);
//					m.backward();
//					Thread.sleep(100);
//					m.stop();
//					sp.fetchSample(sample, 0);
//				}
//				System.out.println(System.currentTimeMillis()-time);
//			}
//
//		}

	}

}
