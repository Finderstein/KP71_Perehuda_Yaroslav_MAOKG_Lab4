package perehuda;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.util.Date;

public class Clock {
	private TransformGroup objectTransformGroup;
	private Transform3D clockTransform3D = new Transform3D();
	private TransformGroup clockTransformGroupSeconds = new TransformGroup();
	private TransformGroup clockTransformGroupMinutes = new TransformGroup();
	private TransformGroup clockTransformGroupHours = new TransformGroup();
	private float angle = 0;

	public BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();

		objectTransformGroup = new TransformGroup();
		objectTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		buildObject(); // Building our clock
		objRoot.addChild(objectTransformGroup);

		// Light
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		Color3f light1Color = new Color3f(1.0f, 1f, 1f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);

		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		objRoot.addChild(ambientLightNode);

		return objRoot;
	}

	// Building our clock
	private void buildObject() {
		double angle = 0;

		// Back of the clock(yellow)
		{
			Transform3D transform3D = new Transform3D();
			TransformGroup transformGroup = new TransformGroup();
			transform3D.rotX(Math.PI/2);
			transform3D.setTranslation(new Vector3f(0f, 0f, -0.05f));
			transformGroup.setTransform(transform3D);

			transformGroup.addChild(ClockElements.getBase());
			objectTransformGroup.addChild(transformGroup);
		}

		// Front of the clock(white)
		{
			Transform3D transform3D = new Transform3D();
			TransformGroup transformGroup = new TransformGroup();
			transform3D.rotX(Math.PI/2);
			transform3D.setTranslation(new Vector3f(0f, 0f, -0.045f));
			transformGroup.setTransform(transform3D);

			transformGroup.addChild(ClockElements.getBack());
			objectTransformGroup.addChild(transformGroup);
		}

		// Creating 12 dashes in specific "angle"
		for(int i = 0; i < 12; i++) {
			Transform3D transform3D = new Transform3D();

			transform3D.rotZ(angle);
			transform3D.setTranslation(new Vector3f((float)Math.cos(angle)*.6f, (float)Math.sin(angle)*.6f, 0));


			TransformGroup transformGroup = new TransformGroup();
			transformGroup.setTransform(transform3D);
			transformGroup.addChild(ClockElements.getDash());

			angle += Math.PI/6;
			objectTransformGroup.addChild(transformGroup);
		}

		// Creating secons, minutes and hours hands
		clockTransformGroupSeconds.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		clockTransformGroupMinutes.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		clockTransformGroupHours.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		clockTransformGroupSeconds.addChild(ClockElements.getSecondsHand());
		clockTransformGroupMinutes.addChild(ClockElements.getMinutesHand());
		clockTransformGroupHours.addChild(ClockElements.getHoursHand());

		objectTransformGroup.addChild(clockTransformGroupHours);
		objectTransformGroup.addChild(clockTransformGroupMinutes);
		objectTransformGroup.addChild(clockTransformGroupSeconds);

		// Setting hands at specific angle(show current time)
		updateClock();
	}

	// Rotate hand at specific angle
	private static void rotateHand(double angle, float handLength, TransformGroup tg) {
		angle += Math.PI/2;
		Transform3D transform = new Transform3D();

		transform.rotZ(angle);
		transform.setTranslation(new Vector3f((float)Math.cos(angle)*handLength,
				(float)Math.sin(angle)*handLength, 0));
		tg.setTransform(transform);
	}

	// Rotating clock with keys (left arrow, right arrow)
	public void rotate(boolean left) {
		clockTransform3D.rotY(angle);

		if(left) angle += 0.05;
		else angle -= 0.05;

		objectTransformGroup.setTransform(clockTransform3D);
	}

	// Get and set current time
	public void updateClock() {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());

		rotateHand(-Math.PI*2*date.getSeconds()/60.0, 0.25f, clockTransformGroupSeconds);
		rotateHand(-Math.PI*2*date.getMinutes()/60.0, 0.2f, clockTransformGroupMinutes);
		rotateHand(-Math.PI*2*date.getHours()/12.0, 0.15f, clockTransformGroupHours);
	}
}

