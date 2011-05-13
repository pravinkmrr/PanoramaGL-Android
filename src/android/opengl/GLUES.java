/*
 * This file is part of the PanoramaGL library for Android.
 *
 *  Authors: Javier Baez <javbaezga@gmail.com>
 *
 *  $Id$
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; version 3 of
 * the License
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package android.opengl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.microedition.khronos.opengles.GL10;

public class GLUES extends GLUconstants
{
	/** load library method */
	
	static
	{
	    System.loadLibrary("glues");
	}
	
	/** quadric methods */
		
	public static GLUquadric gluNewQuadric()
	{
		return new GLUquadric(GLU_SMOOTH, GL10.GL_FALSE, GLU_OUTSIDE, GLU_FILL, null);
	}
	
	public static void gluDeleteQuadric(GLUquadric state)
	{
		state = null;
	}
	
	protected static void gluQuadricError(GLUquadric qobj, int which)
	{
		if(qobj.errorCallback != null)
		{
			try 
			{
				qobj.errorCallback.invoke(qobj, which);
			} 
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			} 
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void gluQuadricCallback(GLUquadric qobj, int which, Method fn)
	{
	   switch (which)
	   {
	      case GLU_ERROR:
	    	  qobj.errorCallback = fn;
	    	  break;
	      default:
	    	  gluQuadricError(qobj, GLU_INVALID_ENUM);
	    	  return;
	   }
	}
	
	public static void gluQuadricNormals(GLUquadric qobj, int normals)
	{
		switch (normals)
		   {
		      case GLU_SMOOTH:
		      case GLU_FLAT:
		      case GLU_NONE:
		           break;
		      default:
		           gluQuadricError(qobj, GLU_INVALID_ENUM);
		           return;
		   }
		   qobj.normals = normals;
	}
	
	public static void gluQuadricTexture(GLUquadric qobj, boolean textureCoords)
	{
		qobj.textureCoords = (textureCoords ? GL10.GL_TRUE : GL10.GL_FALSE);
	}
	
	public static void gluQuadricTexture(GLUquadric qobj, int textureCoords)
	{
		qobj.textureCoords = textureCoords;
	}
	
	public static void gluQuadricOrientation(GLUquadric qobj, int orientation)
	{
	   switch(orientation)
	   {
	      case GLU_OUTSIDE:
	      case GLU_INSIDE:
	           break;
	      default:
	           gluQuadricError(qobj, GLU_INVALID_ENUM);
	           return;
	   }
	   qobj.orientation = orientation;
	}
	
	public static void gluQuadricDrawStyle(GLUquadric qobj, int drawStyle)
	{
	   switch(drawStyle)
	   {
	      case GLU_POINT:
	      case GLU_LINE:
	      case GLU_FILL:
	      case GLU_SILHOUETTE:
	           break;
	      default:
	           gluQuadricError(qobj, GLU_INVALID_ENUM);
	           return;
	   }
	   qobj.drawStyle = drawStyle;
	}
	
	public static void gluCylinder(GL10 gl, GLUquadric qobj, float baseRadius, float topRadius, float height, int slices, int stacks)
	{
		gluCylinderAndroid(gl, qobj, baseRadius, topRadius, height, slices, stacks, qobj.normals, qobj.textureCoords, qobj.orientation, qobj.drawStyle, qobj.errorCallback != null);
	}
	
	public static void gluDisk(GL10 gl, GLUquadric qobj, float innerRadius, float outerRadius, int slices, int loops)
	{
		gluDiskAndroid(gl, qobj, innerRadius, outerRadius, slices, loops, qobj.normals, qobj.textureCoords, qobj.orientation, qobj.drawStyle, qobj.errorCallback != null);
	}
	
	public static void gluPartialDisk(GL10 gl, GLUquadric qobj, float innerRadius, float outerRadius, int slices, int loops, float startAngle, float sweepAngle)
	{
		gluPartialDiskAndroid(gl, qobj, innerRadius, outerRadius, slices, loops, startAngle, sweepAngle, qobj.normals, qobj.textureCoords, qobj.orientation, qobj.drawStyle, qobj.errorCallback != null);
	}
	
	public static void gluSphere(GL10 gl, GLUquadric qobj, float radius, int slices, int stacks)
	{
		gluSphereAndroid(gl, qobj, radius, slices, stacks, qobj.normals, qobj.textureCoords, qobj.orientation, qobj.drawStyle, qobj.errorCallback != null);
	}
	
	private static native void gluCylinderAndroid(GL10 gl, GLUquadric qobj, float baseRadius, float topRadius, float height, int slices, int stacks, int qnormals, int qtextureCoords, int qorientation, int qdrawStyle, boolean qhasCallback);
	
	private static native void gluDiskAndroid(GL10 gl, GLUquadric qobj, float innerRadius, float outerRadius, int slices, int loops, int qnormals, int qtextureCoords, int qorientation, int qdrawStyle, boolean qhasCallback);
	
	private static native void gluPartialDiskAndroid(GL10 gl, GLUquadric qobj, float innerRadius, float outerRadius, int slices, int loops, float startAngle, float sweepAngle, int qnormals, int qtextureCoords, int qorientation, int qdrawStyle, boolean qhasCallback);
	
	private static native void gluSphereAndroid(GL10 gl, GLUquadric qobj, float radius, int slices, int stacks, int qnormals, int qtextureCoords, int qorientation, int qdrawStyle, boolean qhasCallback);

	/** project methods */
	
	public static native void gluPerspective(GL10 gl, float fovy, float aspect, float zNear, float zFar);
}
