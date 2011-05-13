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

import com.android.panoramagl.PLViewBase;
import com.android.panoramagl.iphone.UITouch;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.iphone.structs.CGPoint;
import com.android.panoramagl.iphone.structs.UIAcceleration;

//Equivalent PLViewDelegate
public interface PLViewEventListener 
{
	boolean onShouldBeginTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event);
	void onDidBeginTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event);
	boolean onShouldTouch(PLViewBase plView, List<UITouch> touches, MotionEvent event);
	void onDidTouch(PLViewBase plView, List<UITouch> touches, MotionEvent event);
	boolean onShouldEndTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event);
	void onDidEndTouching(PLViewBase plView, List<UITouch> touches, MotionEvent event);

	boolean onShouldRotate(PLViewBase plView, UIDeviceOrientation orientation);
	void onDidRotate(PLViewBase plView, UIDeviceOrientation orientation);

	boolean onShouldAccelerate(PLViewBase plView, UIAcceleration acceleration, SensorEvent event);
	void onDidAccelerate(PLViewBase plView, UIAcceleration acceleration, SensorEvent event);

	boolean onShouldBeginInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidBeginInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	boolean onShouldRunInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidRunInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidEndInertia(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);

	boolean onShouldReset(PLViewBase plView);
	void onDidReset(PLViewBase plView);

	boolean onShouldBeginZooming(PLViewBase plView);
	void onDidBeginZooming(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	boolean onShouldRunZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut);
	void onDidRunZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut);
	void onDidEndZooming(PLViewBase plView, float distance, boolean isZoomIn, boolean isZoomOut);

	boolean onShouldBeginMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidBeginMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	boolean onShouldRunMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidRunMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidEndMoving(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);

	boolean onShouldBeingScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidBeginScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	boolean onShouldScroll(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidScroll(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
	void onDidEndScrolling(PLViewBase plView, CGPoint starPoint, CGPoint endPoint);
}
