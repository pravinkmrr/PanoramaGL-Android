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

import java.lang.reflect.Method;

public class GLUquadric
{
	  	public int       normals;
	  	public int   	 textureCoords;
	  	public int       orientation;
	  	public int       drawStyle;
	  	public Method	 errorCallback;
	  	
	  	/** init methods */
	  	
	  	public GLUquadric()
	  	{
	  		this(0, 0, 0, 0, null);
	  	}
	  	
	  	public GLUquadric(int normals, int textureCoords, int orientation, int drawStyle)
	  	{
	  		this(normals, textureCoords, orientation, drawStyle, null);
	  	}
	  	
	  	public GLUquadric(int normals, int textureCoords, int orientation, int drawStyle, Method errorCallback)
	  	{
	  		super();
	  		this.normals = normals;
	  		this.textureCoords = textureCoords;
	  		this.orientation = orientation;
	  		this.drawStyle = drawStyle;
	  		this.errorCallback = errorCallback;
	  	}
	  	
	  	/** equals methods */
	  	
	  	public boolean equals(GLUquadric value)
	  	{
	  		return !(normals != value.normals || textureCoords != value.textureCoords || orientation != value.orientation || drawStyle != value.drawStyle);
	  	}
	  	
	  	@Override 
	  	public boolean equals(Object value)
	  	{
	  		return this.equals((GLUquadric)value);
	  	}
	  	
	  	/** dealloc methods */
	  	
	  	@Override
	  	protected void finalize() throws Throwable
	  	{
	  		this.errorCallback = null;
	  		super.finalize();
	  	}
}
