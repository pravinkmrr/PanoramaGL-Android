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

import com.android.panoramagl.iphone.structs.CGPoint;
import com.android.panoramagl.structs.PLRange;

public class PLMath extends Object 
{
	
	/**distance methods*/
	
	public static float distanceBetweenPoints(CGPoint point1, CGPoint point2)
	{
		return (float)Math.sqrt(((point2.x - point1.x) * (point2.x - point1.x)) + ((point2.y - point1.y) * (point2.y - point1.y)));	
	}
	
	/**range methods*/
	
	public static float valueInRange(float value, PLRange range)
	{
		return Math.max(range.min, Math.min(value, range.max));
	}
	
	/**normalize methods*/
	
	public static float normalizeAngle(float angle, PLRange range)
	{
		float result = angle;
	    if(range.min < 0.0f)
		{
	        while (result <= -180.0f) result += 360.0f;
	        while (result > 180.0f) result -= 360.0f;
	    } 
		else 
		{
	        while (result < 0.0f) result += 360.0f;
	        while (result >= 360.0f) result -= 360.0f;
	    }
		return PLMath.valueInRange(result, range);
	}
	
	public static float normalizeFov(float fov, PLRange range)
	{
		return PLMath.valueInRange(fov, range);
	}
	
	/**verification methods*/
	
	public static boolean isPowerOfTwo(int value)
	{
		while ((value & 1) == 0)
			value = value >> 1;
		return (value == 1);
	}
}
