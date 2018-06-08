package scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import engine.PrimitiveType;
import engine.SimulationObject;
import engine.SimulationScene;
import engine.Vector2;
import engine.main;

public class RotationHandler {

	public static float radius = 200;
	public static float mass;
	public static float duration = 5;
	public static float angle = 0;
	
	public static float angleSpeed;
	public static float speed;
	public static float force;
	
	public static Vector2 center;
	public static SimulationObject object;
	
	public static SimulationObject t_radius;
	
	public static Vector2 top;
	public static Vector2 left;
	public static Vector2 bottom;
	public static Vector2 right;
	
	public static Vector2 forceVector;
	public static Vector2 speedVector;
	public static Vector2 tip;
	
	public static SimulationObject t_speed;
	public static SimulationObject t_force;
	public static float speedFactor = 100f;
	public static float forceFactor = 100f;
	
	public static void setup(){
		center = new Vector2(main.WIDTH / 2, main.HEIGHT / 2);
		object = new SimulationObject(Color.BLUE, 100, 100, PrimitiveType.Oval);
		SimulationScene.activeScene.addObject(object, (int)(center.x + radius), (int)center.y);
		
		t_radius = new SimulationObject("Radius r", 15, Color.RED);
		SimulationScene.activeScene.addObject(t_radius, 100, 100);
		
		t_speed = new SimulationObject("Bahngeschwindigkeit: 1 m/s =", 15, Color.BLUE);
		SimulationScene.activeScene.addObject(t_speed, 10, main.HEIGHT - 10);
		
		t_force = new SimulationObject("Zentripetalkraft: 1N =", 15, Color.GREEN);
		SimulationScene.activeScene.addObject(t_force, 10, main.HEIGHT - 30);
		
		GUIHandler.updateOutputs();
	}
	
	public static void updateValues() {
		angleSpeed = (float) ((2 * Math.PI) / duration); 
		
		if(angle >= 360)
			angle -= 360;
		
		speed = (radius / 100f) * angleSpeed;
		
		force = (mass / 1000f) * (speed * speed) / (radius / 100f);
	}
	
	public static void updateRotation() {
		angle += Math.toDegrees(angleSpeed) / main.fixedTick;
		
		object.setLocalRotation(center, angle, radius);
		object.setScale(mass / 1000f);
	}
	
	public static void updateInputs() {
		mass = GUIHandler.mass.value;
		duration = GUIHandler.duration.value / 1000f;
		radius = GUIHandler.radius.value;
	}
	
	public static void setupHelperGUI() {
		top = new Vector2(center.x, center.y + radius);
		left = new Vector2(center.x - radius, center.y);
		bottom = new Vector2(center.x, center.y - radius);
		right = new Vector2(center.x + radius, center.y);
		
		forceVector = new Vector2(0,0);
		speedVector = new Vector2(0,0);
		tip = new Vector2(0,0);
	}
	
	public static void updateHelperGUI(){
		Graphics2D g = (Graphics2D)main.mainWindow.graphics.getGraphics();
		
		top.y = center.y + radius;
		left.x = center.x - radius;
		bottom.y = center.y - radius;
		right.x = center.x + radius;
		
		forceVector.setLocalRotation(center, angle, radius - (force * forceFactor));
		speedVector.setPosition(object.x, object.y);
		speedVector.localTranslate(speed * speedFactor, 0, angle + 90);
		
		//Factors
		if(force >= 25)
			forceFactor= 10;
		if(force < 25 && force >= 10)
			forceFactor = 25;	
		else if(force < 10 && force >= 5)
			forceFactor = 50;
		else if(force < 5)
			forceFactor = 100;
		
		if(speed >= 10)
			speedFactor = 25;	
		else if(speed < 10 && speed >= 3)
			speedFactor = 50;
		else if(force < 3)
			speedFactor = 100;
		
		//Radius
		g.setColor(Color.RED);
		g.drawLine((int)center.x, (int)center.y,(int) right.x,(int) right.y);
		t_radius.setPosition(center.x + (radius / 2) - t_radius.text.length() * 4, center.y);
		
		//Circle
		g.setColor(Color.BLACK);
		g.drawLine((int)top.x, (int)top.y, (int)top.x, (int)top.y + 25);
		g.drawLine((int)bottom.x, (int)bottom.y - 25, (int)bottom.x, (int)bottom.y);
		g.drawLine((int)left.x - 25, (int)left.y, (int)left.x, (int)left.y);
		g.drawLine((int)right.x, (int)right.y, (int)right.x + 25, (int)right.y);
		
		g.draw(new Ellipse2D.Float(center.x - radius, center.y - radius,radius + radius, radius + radius));
		
		//Force
		g.setColor(Color.GREEN);
		g.drawLine((int)object.x,(int) object.y,(int) forceVector.x,(int) forceVector.y);
		tip.setPosition(forceVector.x, forceVector.y);
		tip.localTranslate(10, -5, angle);
		g.drawLine((int)tip.x, (int)tip.y,(int) forceVector.x,(int) forceVector.y);
		tip.setPosition(forceVector.x, forceVector.y);
		tip.localTranslate(10, 5, angle);
		g.drawLine((int)tip.x, (int)tip.y,(int) forceVector.x,(int) forceVector.y);
		
		g.drawLine((int)t_force.x + t_force.text.length() / 2 * 16,(int) t_force.y - 5,(int)( t_force.x + t_force.text.length() / 2 * 16 + forceFactor),(int) t_force.y - 5);
		
		//Speed
		g.setColor(Color.BLUE);
		g.drawLine((int)object.x,(int) object.y,(int) speedVector.x,(int) speedVector.y);
		tip.setPosition(speedVector.x, speedVector.y);
		tip.localTranslate(10, -5, angle - 90);
		g.drawLine((int)tip.x, (int)tip.y,(int) speedVector.x,(int) speedVector.y);
		tip.setPosition(speedVector.x, speedVector.y);
		tip.localTranslate(10, 5, angle - 90);
		g.drawLine((int)tip.x, (int)tip.y,(int) speedVector.x,(int) speedVector.y);
		
		g.drawLine((int)t_speed.x + t_speed.text.length() / 2 * 16,(int) t_speed.y - 5,(int)( t_speed.x + t_speed.text.length() / 2 * 16 + speedFactor),(int) t_speed.y - 5);
		
	}
	
}
