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

public class CGPoint extends Struct<CGPoint>
{	
	public float x, y;
	
	/**init methods*/
	
	public CGPoint()
	{
		this(0, 0);
	}
	
	public CGPoint(float x, float y)
	{
		super();
		this.x = x;
		this.y = y;
	}
	
	public static CGPoint CGPointMake(float x, float y)
	{
		return new CGPoint(x, y);
	}
	
	public static CGPoint CGPointMake(CGPoint point)
	{
		return new CGPoint(point.x, point.y);
	}
}
