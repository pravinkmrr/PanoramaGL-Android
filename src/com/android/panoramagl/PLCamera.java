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

public class PLCamera extends PLSceneElementBase
{	
	protected boolean isFovEnabled;
	protected float fov, fovFactor, fovSensitivity;
	protected PLRange fovRange;
	protected int minDistanceToEnableFov;
		
	/**property methods*/
	
	public boolean isFovEnabled()
	{
		return isFovEnabled;
	}
	
	public void setFovEnabled(boolean isFovEnabled)
	{
		this.isFovEnabled = isFovEnabled;
	}
	
	public float getFov()
	{
		return fov;
	}
		
	public float getFovSensitivity()
	{
		return fovSensitivity;
	}
	
	public PLRange getFovRange()
	{
		return fovRange.clone();
	}
	
	public float getFovFactor()
	{
		return fovFactor;
	}
	
	public int getMinDistanceToEnableFov()
	{
		return minDistanceToEnableFov;
	}
	
	/**init methods*/
	
	public PLCamera()
	{
		super();
	}
	
	public static PLCamera camera()
	{
		return new PLCamera();
	}
	
	@Override
	protected void initializeValues()
	{
		fovRange = PLRange.PLRangeMake(PLConstants.kDefaultFovMinValue, PLConstants.kDefaultFovMaxValue);
		isFovEnabled = true;
		fovSensitivity = PLConstants.kDefaultFovSensitivity;
		minDistanceToEnableFov = PLConstants.kDefaultMinDistanceToEnableFov;
		super.initializeValues();
		isValid = true;
	}
	
	@Override
	public void reset()
	{
		this.calculateFov();
		super.reset();
	}
	
	/**fov methods*/
	
	protected void calculateFov()
	{
		fov = fovRange.min <= 0.0f ? (fovRange.max >= 0.0f ? 0.0f : fovRange.max) : fovRange.min;
		this.setFov(fov);
	}
	
	public void setFovRange(PLRange value)
	{
		if(value.max >= value.min)
		{			
			this.fovRange = PLRange.PLRangeMake(value.min < PLConstants.kFovMinValue ? PLConstants.kFovMinValue : value.min, value.max > PLConstants.kFovMaxValue ? PLConstants.kFovMaxValue : value.max);
			this.calculateFov();
		}
	}
	
	public void setMinDistanceToEnableFov(int value)
	{
		if(value > 0)
			this.minDistanceToEnableFov = value;
	}
	
	public void setFovSensitivity(float value)
	{
		if(value > 0.0f)
			this.fovSensitivity = value;
	}
	
	public void setFov(float value)
	{
		if(isFovEnabled)
		{
			fov = PLMath.normalizeFov(value, fovRange);
			if(fov < 0.0f)
				fovFactor = PLConstants.kFovFactorOffsetValue + PLConstants.kFovFactorNegativeOffsetValue * (fov / PLConstants.kDefaultFovFactorMinValue);
			else if(fov >= 0.0f)
				fovFactor = PLConstants.kFovFactorOffsetValue + PLConstants.kFovFactorPositiveOffsetValue * (fov / PLConstants.kDefaultFovFactorMaxValue);
		}
	}

	public void addFovWithDistance(float distance)
	{
		this.setFov(this.getFov() + (distance / fovSensitivity));
	}

	public void addFovWithStartPoint(CGPoint startPoint, CGPoint endPoint, int sign)
	{
		this.addFovWithDistance(PLMath.distanceBetweenPoints(startPoint, endPoint) * (sign < 0 ? -1 : 1));
	}
	
	/**utility methods*/
	
	public void cloneCameraProperties(PLCamera value)
	{
		super.clonePropertiesOf(value);
		isFovEnabled = value.isFovEnabled;
		fovRange = value.fovRange.clone();
		this.setFov(value.getFov());		
	}
	
	/**render methods*/

	@Override
	protected void beginRender()
	{
		this.rotate();
		this.translate();
	}

	@Override
	protected void endRender()
	{
	}

	@Override
	protected void internalRender()
	{
	}
}


