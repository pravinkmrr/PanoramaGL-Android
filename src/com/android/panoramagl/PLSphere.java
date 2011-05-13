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
import com.android.panoramagl.iphone.EAGLContext;

public class PLSphere extends PLSceneElement
{
	private Integer divs;
	
	/**property methods*/
	
	public Integer getDivs()
	{
		return this.divs;
	}
	
	public void setDivs(Integer value)
	{
		this.divs = value;
	}
	
	/**init methods*/

	public PLSphere()
	{
		super();
	}
	
	public PLSphere(PLTexture texture)
	{
		super(texture);
	}
	
	public static PLSphere sphere()
	{
		return new PLSphere();
	}

	public static PLSphere sphereWithTexture(PLTexture texture)
	{
		return new PLSphere(texture);
	}

	@Override
	protected void initializeValues()
	{
		super.initializeValues();
		divs = PLConstants.kDefaultSphereDivs;
	}

	/**render methods*/

	@Override
	protected void internalRender()
	{
		GL10 gl = EAGLContext.currentGL();

		gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
		
		GLUquadric quadratic = GLUES.gluNewQuadric();
		GLUES.gluQuadricNormals(quadratic, GLUES.GLU_SMOOTH);
		GLUES.gluQuadricTexture(quadratic, true);
			
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(0).getTextureId());
		
		GLUES.gluSphere(gl, quadratic, 1.0f, divs, divs);

		GLUES.gluDeleteQuadric(quadratic);
	}
}