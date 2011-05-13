/*
 * This file is part of the PanoramaGL library for Android.
 *
 *  Authors: Javier Baez <javbaezga@gmail.com> and Miguel „au–ay <mg_naunay@hotmail.com>
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

package com.android.panoramagl;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLUES;
import android.opengl.GLUquadric;
//import android.opengl.GLUquadric;

import com.android.panoramagl.PLSceneElement;
import com.android.panoramagl.iphone.EAGLContext;
import com.android.panoramagl.structs.PLRange;

public class PLCylinder extends PLSceneElement 
{
	private Integer divs;
	
	private boolean isHeightCalculated;
	private float height;
	
	/**property methods*/
	
	public Integer getDivs()
	{
		return divs;
	}
	
	public void setDivs(Integer divs)
	{
		this.divs = divs;
	}
	
	public boolean isHeightCalculated()
	{
		return isHeightCalculated;
	}
	
	public void setHeightCalculated(boolean isHeightCalculated)
	{
		this.isHeightCalculated = isHeightCalculated;
	}
	
	public void setHeight(float value) 
	{
		this.height = Math.abs(value);
	}
	
	public float getHeight()
	{
		return height;
	}
	
	/**init methods*/
	
	public PLCylinder()
	{
		super();
	}
	
	public PLCylinder(PLTexture texture)
	{
		super(texture);
	}
	
	public static PLCylinder cylinder()
	{
		return new PLCylinder();
	}

	public static PLCylinder cylinderWithTexture(PLTexture texture)
	{
		return new PLCylinder(texture);
	}

	@Override
	protected void initializeValues()
	{
		super.initializeValues();
		height = PLConstants.kDefaultCylinderHeight;
		divs = PLConstants.kDefaultCylinderDivs;
		isHeightCalculated = PLConstants.kDefaultCylinderHeightCalc;
		pitchRange = PLRange.PLRangeMake(0.0f, 0.0f);
		isXAxisEnabled = false;
	}
	
	/**render methods*/
	
	@Override
	protected void internalRender()
	{
		GL10 gl = EAGLContext.currentGL();
		
		gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
		gl.glTranslatef(0.0f, 0.0f, -height/2.0f);
		
		GLUquadric quadratic = GLUES.gluNewQuadric();
		GLUES.gluQuadricNormals(quadratic, GLUES.GLU_SMOOTH);
		GLUES.gluQuadricTexture(quadratic, true);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(0).getTextureId());
				
		GLUES.gluCylinder(gl, quadratic, 1.0f, 1.0f, height, divs, divs);
		
		GLUES.gluDeleteQuadric(quadratic);
	}
	
	/**texture methods*/
	
	@Override
	public void addTexture(PLTexture texture)
	{
		super.addTexture(texture);
		if(textures.size() == 1 && isHeightCalculated)
		{
			int textureWidth = texture.getWidth();
			int textureHeight = texture.getHeight();
			height = textureWidth >= textureHeight ? (float) textureWidth / textureHeight : (float) textureHeight / textureWidth;
		}
	}	
}
