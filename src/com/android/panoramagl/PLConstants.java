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

public class PLConstants
{
	/** utility consts */
	public static final float kFloatMinValue = Float.MIN_VALUE;
	public static final float kFloatMaxValue = Float.MAX_VALUE;
	
	/** buffer consts */
	public static final int kUseDepthBuffer = 0;
	
	/** texture consts */
	public static final int kTextureMaxWidth  = 1024;
	public static final int kTextureMaxHeight = 1024;
	
	/** cube consts */
	public static final int kCubeFrontFaceIndex =	0;
	public static final int kCubeBackFaceIndex	=	1;
	public static final int kCubeLeftFaceIndex	=	2;
	public static final int kCubeRightFaceIndex =	3;
	public static final int kCubeTopFaceIndex =		4;
	public static final int kCubeBottomFaceIndex =	5;
	
	/** sphere consts */
	public static final int  kDefaultSphereDivs = 30;

	/** cylinder consts */
	public static final int kDefaultCylinderDivs =				60;
	public static final float  kDefaultCylinderHeight =			3.0f;
	public static final boolean kDefaultCylinderHeightCalc = 	true;

	/** rotation consts */
	public static final float kDefaultRotateSensitivity	=		30.0f;
	public static final float kDefaultAnimationTimerInterval =	1.0f/45.0f;

	public static final float kDefaultRotateMinRange = -180.0f;
	public static final float kDefaultRotateMaxRange =  180.0f;

	/** fov (field of view) consts */
	public static final float kDefaultFovSensitivity = -1.0f;

	public static final float kFovMinValue = -1.0f;
	public static final float kFovMaxValue =  1.0f;
	
	public static final float kDefaultFovMinValue =	0.0f;
	public static final float kDefaultFovMaxValue =	kFovMaxValue;

	public static final float kDefaultFovFactorMinValue = 0.8f;
	public static final float kDefaultFovFactorMaxValue = 1.20f;

	public static final float kFovFactorOffsetValue = 			1.0f;
	public static final float kFovFactorNegativeOffsetValue = 	kFovFactorOffsetValue - kDefaultFovFactorMinValue;
	public static final float kFovFactorPositiveOffsetValue = 	kDefaultFovFactorMaxValue - kFovFactorOffsetValue;
	
	public static final int kDefaultMinDistanceToEnableFov = 8;

	/** inertia consts */
	public static final int kDefaultInertiaInterval = 3;

	/** accelerometer consts */
	public static final float kDefaultAccelerometerSensitivity =	10.0f;
	public static final float kDefaultAccelerometerInterval	= 		1.0f/30.0f;
	public static final float kAccelerometerSensitivityMinValue	=	1.0f;
	public static final float kAccelerometerSensitivityMaxValue	=	10.0f;
	public static final float kAccelerometerMultiplyFactor =		5.0f;

	/** scrolling consts */
	public static final int kDefaultMinDistanceToEnableScrolling = 50;
	
	/** perspective consts */
	public static final float kPerspectiveValue =	290.0f;
	public static final float kPerspectiveZNear = 	0.01f;
	public static final float kPerspectiveZFar =	100.0f;
	
	/** shake consts */
	public static final int kShakeThreshold = 	1000;
	public static final int kShakeDiffTime =	100;
}
