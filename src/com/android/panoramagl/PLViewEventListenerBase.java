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

import java.util.List;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

import com.android.panoramagl.iphone.UITouch;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.iphone.structs.CGPoint;
import com.android.panoramagl.iphone.structs.UIAcceleration;

public class PLViewEventListenerBase implements PLViewEventListener
{
	@Override
	public void onDidAccelerate(PLViewBase plView, UIAcceleration acceleration, SensorEvent event)
	{
	}

	@Override
	public void onDidBeginInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidBeginMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{	
	}

	@Override
	public void onDidBeginScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidBeginTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
	}

	@Override
	public void onDidBeginZooming(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidEndInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidEndMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidEndScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidEndTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
	}

	@Override
	public void onDidEndZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut)
	{
	}

	@Override
	public void onDidReset(PLViewBase plView)
	{
	}

	@Override
	public void onDidRotate(PLViewBase plView, UIDeviceOrientation orientation)
	{
	}

	@Override
	public void onDidRunInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidRunMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidRunZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut)
	{
	}

	@Override
	public void onDidScroll(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
	}

	@Override
	public void onDidTouch(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
	}

	@Override
	public boolean onShouldAccelerate(PLViewBase plView, UIAcceleration acceleration, SensorEvent event)
	{
		return true;
	}

	@Override
	public boolean onShouldBeginInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldBeginMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldBeginTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
		return true;
	}

	@Override
	public boolean onShouldBeginZooming(PLViewBase plView)
	{
		return true;
	}

	@Override
	public boolean onShouldBeingScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldEndTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
		return true;
	}

	@Override
	public boolean onShouldReset(PLViewBase plView)
	{
		return true;
	}

	@Override
	public boolean onShouldRotate(PLViewBase plView, UIDeviceOrientation orientation)
	{
		return true;
	}

	@Override
	public boolean onShouldRunInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldRunMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldRunZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut)
	{
		return true;
	}

	@Override
	public boolean onShouldScroll(PLViewBase plView, CGPoint starPoint, CGPoint endPoint)
	{
		return true;
	}

	@Override
	public boolean onShouldTouch(PLViewBase plView, List<UITouch> touches, MotionEvent event)
	{
		return true;
	}
}
