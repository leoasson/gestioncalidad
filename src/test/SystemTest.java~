package tests;

import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

import main.BridgeCraneTestDrive;

import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.launcher.ApplicationLauncher;

import static org.assertj.swing.finder.WindowFinder.findFrame;


public class SystemTest extends AssertJSwingTestngTestCase{
	
	public SystemTest() {
		super();
	}
	
	
	protected void onSetUp() 
	{
	}
	
	@Before
	public void BeforeTest(){
		setUpRobot();
		application(BridgeCraneTestDrive.class).start();
	}
	
	@Test
	public void setUpBridgeCrane()
	{
		/*
		 * Se mueve la grua hasta una posición donde hay una caja, se la toma, se la mueve hasta la posición
		 * inicial de la grúa y se la suelta en ese punto.
		 */
		FrameFixture controllerWindow = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {

			@Override
			protected boolean isMatching(JFrame component) 
			{
				return "Control".equals(component.getTitle());
			}
		}).using(robot());
		
		JTextComponentFixture enterBPM = controllerWindow.textBox();
		enterBPM.setText("1234"); // Seteamos contraseña 1234
		
		
		JButtonFixture buttonleft = controllerWindow.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				return "<<".equals(component.getText());
			}
		});
		buttonleft.target().doClick(); 
		sleep();
		buttonleft.target().doClick(); 
		sleep();
		buttonleft.target().doClick(); 
		sleep();
		
		JButtonFixture buttonup = controllerWindow.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				return "^".equals(component.getText());
			}
		});
		buttonup.target().doClick(); 
		sleep();
		buttonup.target().doClick(); 
		sleep();
		buttonup.target().doClick(); 
		sleep();

		JButtonFixture buttonset = controllerWindow.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				return "Set".equals(component.getText());
			}
		});
		buttonset.target().doClick(); 
		sleep();
		buttonset.target().doClick(); 
		sleep();
		
		JButtonFixture buttonright = controllerWindow.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				return ">>".equals(component.getText());
			}
		});
		buttonright.target().doClick();
		sleep();
		buttonright.target().doClick();
		sleep();
		buttonright.target().doClick(); 
		sleep();
		
		JButtonFixture buttondown = controllerWindow.button(new GenericTypeMatcher<JButton>(JButton.class) {

			@Override
			protected boolean isMatching(JButton component) {
				return "v".equals(component.getText());
			}
		});
		buttondown.target().doClick();
		sleep();
		buttondown.target().doClick(); 
		sleep();
		buttondown.target().doClick(); 
		sleep();
		
		buttonset.target().doClick(); 
		sleep();
		buttonset.target().doClick(); 
		sleep();
		
		FrameFixture bridgeWindow = findFrame(new GenericTypeMatcher<JFrame>(JFrame.class) {

			@Override
			protected boolean isMatching(JFrame component) {
				return "Deposito".equals(component.getTitle());
			}
		}).using(robot());
		
		/*
		 * Se crea la ventana y se busca un panel de color rojo (un panel es rojo
		 * cuando la grua está por encima de una caja).
		 */
		JPanelFixture panel = bridgeWindow.panel(new GenericTypeMatcher<JPanel>(JPanel.class) {

			@Override
			protected boolean isMatching(JPanel component) {
				return Color.RED.toString().equals(component.getBackground().toString());
			}
		});
		
		System.out.println(panel.toString());
		/*
		 * Si se encontró un panel rojo, significa que la caja fue movida correctamente.
		 */
		assert(panel != null);
		robot().cleanUp();
		
	}
	
	public void sleep()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

