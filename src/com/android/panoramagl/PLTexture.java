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

import java.io.File;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLES10;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

import com.android.panoramagl.iphone.EAGLContext;
import com.android.panoramagl.iphone.structs.CGSize;

public class PLTexture extends Object
{	
	private int[] textureId = new int[1];
	private int width, height;
	private boolean isValid;
	
	/**property methods*/
		
	public int getTextureId()
	{
		return textureId[0];
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public boolean isValid()
	{
		return isValid;
	}
	
	/**init methods*/
	
	public PLTexture(Bitmap image) throws Throwable
	{
		super();
		this.loadTextureWithImage(image);
	}
	
	public PLTexture(String path) throws Throwable
	{
		super();
		this.loadTextureWithPath(path);
	}
	
	public PLTexture(Bitmap image, int rotateAngle) throws Throwable
	{
		super();
		this.loadTextureWithImage(image, rotateAngle);
	}
	
	public PLTexture(String path, int rotateAngle) throws Throwable
	{
		super();
		this.loadTextureWithPath(path, rotateAngle);
	}
	
	public static PLTexture textureWithImage(Bitmap image) throws Throwable
	{
		return new PLTexture(image);
	}
	
	public static PLTexture textureWithPath(String path) throws Throwable
	{
		return new PLTexture(path);
	}
	
	public static  PLTexture textureWithImage(Bitmap image, int rotateAngle) throws Throwable
	{
		return new PLTexture(image, rotateAngle);
	}
	
	public static  PLTexture textureWithPath(String path, int rotateAngle) throws Throwable
	{
		return new PLTexture(path, rotateAngle);
	}
	
	/**load methods*/
	
	protected boolean loadTextureWithObject(Object object, int angle) throws Throwable
	{
		this.deleteTexture();
		
		PLImage plImage = object.getClass() == String.class ? PLImage.imageWithPath((String)object) : PLImage.imageWithCGImage((Bitmap)object);
		
		width = plImage.getWidth();
		height = plImage.getHeight();
		
		if(width > PLConstants.kTextureMaxWidth || height > PLConstants.kTextureMaxHeight)
			throw new Exception(String.format("Invalid texture size. Texture max size is %d x %d", PLConstants.kTextureMaxWidth, PLConstants.kTextureMaxHeight));
		
		boolean isResizableImage = false;
		if(!PLMath.isPowerOfTwo(width))
		{
			isResizableImage = true;
			width = PLConstants.kTextureMaxWidth / 2;
		}
		if(!PLMath.isPowerOfTwo(height))
		{
			isResizableImage = true;
			height = PLConstants.kTextureMaxHeight / 2;
		}
		
		if(isResizableImage)
			plImage.scale(CGSize.CGSizeMake(width, height));
		
		if(angle != 0)
			plImage.rotate(angle);
		
		Bitmap bitmap = plImage.getBitmap();
		
		GL10 gl = EAGLContext.currentGL();
		
		gl.glGenTextures(1, textureId, 0);
		gl.glBindTexture(GLES10.GL_TEXTURE_2D, textureId[0]);
				
		gl.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MIN_FILTER, GLES10.GL_LINEAR); //GL10.GL_NEAREST
		gl.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MAG_FILTER, GLES10.GL_LINEAR);
		gl.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_S, GLES10.GL_CLAMP_TO_EDGE); //GL10.GL_REPEAT
		gl.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_T, GLES10.GL_CLAMP_TO_EDGE);
		
		gl.glTexEnvf(GLES10.GL_TEXTURE_ENV, GLES10.GL_TEXTURE_ENV_MODE, GLES10.GL_MODULATE);
		
		GLUtils.texImage2D(GLES10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		bitmap.recycle();
		bitmap = null;

		int errGL = gl.glGetError();
		
		plImage = null;
		
		if(errGL != GLES10.GL_NO_ERROR)
		{
			Log.e("loadTexture", String.format("glGetError = (%d) %s ...", errGL, GLU.gluErrorString(errGL)));
			isValid = false;
		}
		
		isValid = true;
		return isValid;
	}
	
	public boolean loadTextureWithPath(String path) throws Throwable
	{
		return this.loadTextureWithPath(path ,0);
	}
	
	public boolean loadTextureWithPath(String path ,int angle) throws Throwable
	{
		if(!new File(path).exists())
			throw new Exception(String.format("File not exists. File %@ not exists", path));
		
		return this.loadTextureWithObject(path, angle);
	}
	
	public boolean loadTextureWithImage(Bitmap image) throws Throwable
	{
		return this.loadTextureWithImage(image, 0);
	}
	
	public boolean loadTextureWithImage(Bitmap image,int angle) throws Throwable
	{
		return this.loadTextureWithObject(image, angle);
	}
	
	/**dealloc methods*/

	protected void deleteTexture()
	{
		if(textureId != null && textureId[0] != 0)
		{
			GL10 gl = EAGLContext.currentGL();
			gl.glDeleteTextures(1, textureId, 0);
			textureId[0] = 0;
		}
	}

	@Override
	protected void finalize() throws Throwable
	{
		this.deleteTexture();
		textureId = null;
		super.finalize();
	}
}


