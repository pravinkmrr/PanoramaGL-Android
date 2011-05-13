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

package com.android.panoramagl.structs;

import com.android.panoramagl.iphone.structs.Struct;

public class PLRotation extends Struct<PLRotation>
{
	public float pitch, yaw, roll;
	
	/**init methods*/

	public PLRotation()
	{
		this(0, 0, 0);
	}
	
	public PLRotation(float pitch, float yaw, float roll)
	{
		super();
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
	public static PLRotation PLRotationMake(float pitch, float yaw, float roll)
	{
		return new PLRotation(pitch, yaw, roll);
	}
	
	public static PLRotation PLRotationMake(PLRotation rotation)
	{
		return new PLRotation(rotation.pitch, rotation.yaw, rotation.roll);
	}
}
