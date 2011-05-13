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

package com.android.panoramagl.iphone.structs;

public class UIAcceleration extends Struct<UIAcceleration> 
{
	public float x, y, z;
	
	/**init methods*/
	
	public UIAcceleration()
	{
		this(0, 0, 0);
	}
	
	public UIAcceleration(float x, float y, float z)
	{
		super();
		this.x = -x;
		this.y = -y;
		this.z = -z;
	}
	
	public UIAcceleration(float[] values)
	{
		this(values[0], values[1], values[2]);
	}
	
	public static UIAcceleration UIAccelerationMake(float x, float y, float z)
	{
		return new UIAcceleration(x, y, z);
	}
	
	public static UIAcceleration UIAccelerationMake(float[] values)
	{
		return new UIAcceleration(values);
	}
	
	public static UIAcceleration UIAccelerationMake(UIAcceleration acceleration)
	{
		return new UIAcceleration(acceleration.x, acceleration.y, acceleration.z);
	}
}
