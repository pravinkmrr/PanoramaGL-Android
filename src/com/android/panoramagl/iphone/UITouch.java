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

package com.android.panoramagl.iphone;

import com.android.panoramagl.iphone.structs.CGPoint;

import android.app.Activity;

public class UITouch 
{
	protected int tapCount;
	protected Activity view;
	protected CGPoint position;
	
	/**property methods*/
	
	public int getTapCount()
	{
		return tapCount;
	}
	
	public Activity getView()
	{
		return view;
	}
	
	/**init methods*/
	
	public UITouch(Activity view, CGPoint position, int tapCount)
	{
		this.view = view;
		this.position = position;
		this.tapCount = tapCount;
	}
	
	public UITouch(Activity view, CGPoint position)
	{
		new UITouch(view, position, 1);
	}
	
	public UITouch(Activity view)
	{
		new UITouch(view, CGPoint.CGPointMake(0, 0), 1);
	}
	
	/**location methods*/
	
	public CGPoint locationInView(Activity view)
	{
		return position;
	}
}
