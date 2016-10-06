/*
 * This file is part of the PanoramaGL library for Android.
 *
 *  Authors: Javier Baez <javbaezga@gmail.com> and Miguel �au�ay <mg_naunay@hotmail.com>
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

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import com.android.panoramagl.iphone.EAGLContext;

public class PLCube extends PLSceneElement 
{	
	/**init methods*/
	
	public PLCube()
	{
		super();
		cubeBuffer = PLUtils.makeFloatBuffer(cube);
		textureCoordsBuffer = PLUtils.makeFloatBuffer(textureCoords);
	}
	
	public PLCube(PLTexture front, PLTexture back, PLTexture left, PLTexture right, PLTexture top, PLTexture bottom)
	{
		this();
		this.addTexture(front);
		this.addTexture(back);
		this.addTexture(left);
		this.addTexture(right);
		this.addTexture(top);
		this.addTexture(bottom);
	}
	
	public static PLCube cube()
	{
		return new PLCube();
	}
	
	public static PLCube cubeWithTextures(PLTexture front, PLTexture back, PLTexture left, PLTexture right, PLTexture top, PLTexture bottom)
	{
		return new PLCube(front, back, left, right, top, bottom);
	}
	
	/** utility methods*/
	
	@Override
	protected void evaluateIfElementIsValid()
	{
		isValid = (textures.size() >= 6);
	}
	
	/**render methods*/
	
	protected FloatBuffer cubeBuffer, textureCoordsBuffer;
	
	public final float R = 1.0f;
	
	protected float[] cube = 
	{
		// Front Face
		-R, -R,  R,
		 R, -R,  R,
		-R,  R,  R,
		 R,  R,  R,
		// Back Face
		-R, -R, -R,
		-R,  R, -R,
		 R, -R, -R,
		 R,  R, -R,
		// Right Face
		-R, -R,  R,
		-R,  R,  R,
		-R, -R, -R,
		-R,  R, -R,
		// Left Face
		 R, -R, -R,
		 R,  R, -R,
		 R, -R,  R,
		 R,  R,  R,
		// Top Face
		-R,  R,  R,
		 R,  R,  R,
		-R,  R, -R,
		 R,  R, -R,
		// Bottom Face
		-R, -R,  R,
		-R, -R, -R,
		 R, -R,  R,
		 R, -R, -R,
	};

	protected float[] textureCoords = 
	{
		// Front Face
		1.0f, 1.0f,
		0.0f, 1.0f,	
		1.0f, 0.0f,
		0.0f, 0.0f,
		// Back Face
		0.0f, 1.0f,
		0.0f, 0.0f,
		1.0f, 1.0f,
		1.0f, 0.0f,
		// Right Face
		0.0f, 1.0f,
		0.0f, 0.0f,
		1.0f, 1.0f,
		1.0f, 0.0f,
		// Left Face
		0.0f, 1.0f,
		0.0f, 0.0f,
		1.0f, 1.0f,
		1.0f, 0.0f,
		// Top Face
		1.0f, 1.0f,
		0.0f, 1.0f,
		1.0f, 0.0f,
		0.0f, 0.0f,
		// Bottom Face
		1.0f, 0.0f,
		1.0f, 1.0f,
		0.0f, 0.0f,
		0.0f, 1.0f,
	};
	
	@Override
	protected void internalRender()
	{
		GL10 gl = EAGLContext.currentGL();

		gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL10.GL_BLEND);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureCoordsBuffer);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_FRONT);
		gl.glShadeModel(GL10.GL_SMOOTH);
			
		// Front Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeFrontFaceIndex).getTextureId());
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		
		// Back Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeBackFaceIndex).getTextureId());
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
		
		// Right Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeRightFaceIndex).getTextureId());
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
		
		// Left Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeLeftFaceIndex).getTextureId());
		gl.glNormal3f(1.0f, 0.0f, 0.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
		
		// Top Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeTopFaceIndex).getTextureId());
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
		
		// Bottom Face
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(PLConstants.kCubeBottomFaceIndex).getTextureId());
		gl.glNormal3f(0.0f, -1.0f, 0.0f);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);	
		gl.glDisable(GL10.GL_CULL_FACE);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		gl.glDisable(GL10.GL_BLEND);

		/** method end*/
	}
	
	/**dealloc methods*/
	
	@Override
	protected void finalize() throws Throwable
	{
		cubeBuffer = textureCoordsBuffer = null;
		cube = textureCoords = null;
		super.finalize();
	}
}
