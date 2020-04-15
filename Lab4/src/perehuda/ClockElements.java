package perehuda;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class ClockElements {
	public static Primitive getDash() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.08f, 0.01f, 0.01f, primflags, getBlackAppearance());
	}

	public static Primitive getBase() {
		Appearance ap = new Appearance();

		Color3f emissive = new Color3f(0f, 0f, 0f);
		Color3f ambient = new Color3f(0f, 0f, 0f);
		Color3f diffuse = new Color3f(.1f, .1f, .1f);
		Color3f specular = new Color3f(.9f, .6f, .4f);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1f));

		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

		return new Cylinder(0.8f, 0.1f, primflags, ap);
	}

	public static Primitive getBack() {
		Appearance ap = new Appearance();

		Color3f emissive = new Color3f(0f, 0f, 0f);
		Color3f ambient = new Color3f(0f, 0f, 0f);
		Color3f diffuse = new Color3f(.2f, .2f, .2f);
		Color3f specular = new Color3f(.9f, .9f, .9f);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1f));

		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

		return new Cylinder(0.75f, 0.1f, primflags, ap);
	}

	public static Primitive getSecondsHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.25f, 0.01f, 0.01f, primflags, getBlackAppearance());
	}

	public static Primitive getMinutesHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.2f, 0.02f, 0.01f, primflags, getBlackAppearance());
	}

	public static Primitive getHoursHand() {
		int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
		return new Box(0.15f, 0.03f, 0.01f, primflags, getBlackAppearance());
	}

	public static Appearance getBlackAppearance() {
		Appearance ap = new Appearance();

		Color3f emissive = new Color3f(0.05f, 0.05f, 0.05f);
		Color3f ambient = new Color3f(0.05f, 0.05f, 0.05f);
		Color3f diffuse = new Color3f(0.15f, 0.15f, .15f);
		Color3f specular = new Color3f(0, 0, 0);
		ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));

		return ap;
	}
}
