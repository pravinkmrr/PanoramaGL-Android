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

public class PLVertex extends Struct<PLVertex>
{
	public float x, y, z;
	
	/**init methods*/
	
	public PLVertex()
	{
		this(0, 0, 0);
	}
	
	public PLVertex(float x, float y, float z)
	{
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static PLVertex PLVertexMake(float x, float y, float z)
	{
		return new PLVertex(x, y, z);
	}
	
	public static PLVertex PLVertexMake(PLVertex vertex)
	{
		return new PLVertex(vertex.x, vertex.y, vertex.z);
	}
}
