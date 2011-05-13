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

import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.iphone.structs.CGPoint;
import com.android.panoramagl.structs.PLPosition;
import com.android.panoramagl.structs.PLRange;
import com.android.panoramagl.structs.PLRotation;

public class PLObject extends Object
{	
	protected boolean isXAxisEnabled, isYAxisEnabled, isZAxisEnabled;
	protected PLPosition position;
	protected PLRange xRange, yRange, zRange;
	
	protected boolean isPitchEnabled, isYawEnabled, isRollEnabled, isReverseRotation;
	protected PLRotation rotation;
	protected PLRange pitchRange, yawRange, rollRange;
	protected float rotateSensitivity;
	
	protected UIDeviceOrientation oldOrientation, orientation;
	
	/**property methods*/
	
	public boolean isXAxisEnabled()
	{
		return isXAxisEnabled;
	}

	public void setXAxisEnabled(boolean isXAxisEnabled)
	{
		this.isXAxisEnabled = isXAxisEnabled;
	}

	public boolean isYAxisEnabled()
	{
		return isYAxisEnabled;
	}

	public void setYAxisEnabled(boolean isYAxisEnabled)
	{
		this.isYAxisEnabled = isYAxisEnabled;
	}

	public boolean isZAxisEnabled()
	{
		return isZAxisEnabled;
	}

	public void setZAxisEnabled(boolean isZAxisEnabled)
	{
		this.isZAxisEnabled = isZAxisEnabled;
	}
	
	public PLPosition getPosition()
	{
		return position;
	}
	
	public PLRange getXRange()
	{
		return xRange;
	}

	public void setXRange(PLRange xRange)
	{
		this.xRange = xRange;
	}

	public PLRange getYRange()
	{
		return yRange;
	}

	public void setYRange(PLRange yRange)
	{
		this.yRange = yRange;
	}

	public PLRange getZRange()
	{
		return zRange;
	}

	public void setZRange(PLRange zRange)
	{
		this.zRange = zRange;
	}

	public boolean isPitchEnabled()
	{
		return isPitchEnabled;
	}

	public void setPitchEnabled(boolean isPitchEnabled)
	{
		this.isPitchEnabled = isPitchEnabled;
	}

	public boolean isYawEnabled()
	{
		return isYawEnabled;
	}

	public void setYawEnabled(boolean isYawEnabled)
	{
		this.isYawEnabled = isYawEnabled;
	}

	public boolean isRollEnabled()
	{
		return isRollEnabled;
	}

	public void setRollEnabled(boolean isRollEnabled)
	{
		this.isRollEnabled = isRollEnabled;
	}

	public boolean isReverseRotation()
	{
		return isReverseRotation;
	}

	public void setReverseRotation(boolean isReverseRotation)
	{
		this.isReverseRotation = isReverseRotation;
	}

	public PLRotation getRotation()
	{
		return rotation;
	}
	
	public PLRange getPitchRange()
	{
		return pitchRange;
	}

	public void setPitchRange(PLRange pitchRange)
	{
		this.pitchRange = pitchRange;
	}

	public PLRange getYawRange()
	{
		return yawRange;
	}

	public void setYawRange(PLRange yawRange)
	{
		this.yawRange = yawRange;
	}

	public PLRange getRollRange()
	{
		return rollRange;
	}

	public void setRollRange(PLRange rollRange)
	{
		this.rollRange = rollRange;
	}

	public float getRotateSensitivity()
	{
		return rotateSensitivity;
	}

	public void setRotateSensitivity(float rotateSensitivity)
	{
		this.rotateSensitivity = rotateSensitivity;
	}
	
	public UIDeviceOrientation getOrientation()
	{
		return orientation;
	}
	
	/**init methods*/
	
	public PLObject()
	{
		super();
		this.initializeValues();
	}
	
	protected void initializeValues()
	{
		xRange = PLRange.PLRangeMake(PLConstants.kFloatMinValue, PLConstants.kFloatMaxValue); yRange = PLRange.PLRangeMake(PLConstants.kFloatMinValue, PLConstants.kFloatMaxValue); zRange = PLRange.PLRangeMake(PLConstants.kFloatMinValue, PLConstants.kFloatMaxValue);
		pitchRange = PLRange.PLRangeMake(PLConstants.kDefaultRotateMinRange, PLConstants.kDefaultRotateMaxRange); yawRange = PLRange.PLRangeMake(PLConstants.kDefaultRotateMinRange, PLConstants.kDefaultRotateMaxRange); rollRange = PLRange.PLRangeMake(PLConstants.kDefaultRotateMinRange, PLConstants.kDefaultRotateMaxRange);
		
		isXAxisEnabled = isYAxisEnabled = isZAxisEnabled = true;
		isPitchEnabled = isYawEnabled = isRollEnabled = true;
		
		rotateSensitivity = PLConstants.kDefaultRotateSensitivity;	
		isReverseRotation = false;
		
		oldOrientation = UIDeviceOrientation.UIDeviceOrientationUnknown; orientation = UIDeviceOrientation.UIDeviceOrientationUnknown;
		
		this.reset();
	}
	
	public void reset()
	{
		position = PLPosition.PLPositionMake(0.0f, 0.0f, 0.0f);
		rotation = PLRotation.PLRotationMake(0.0f, 0.0f, 0.0f);
	}
	
	/**translate methods*/
	
	public void translateWith(float xValue , float yValue)
	{
		position.x = xValue;
		position.y = yValue;
	}
	
	public void translateWith(float xValue, float yValue, float zValue)
	{
		position = PLPosition.PLPositionMake(xValue, yValue, zValue);
	}
	
	/**rotate methods*/
	
	public void rotateWith(float pitchValue ,float yawValue)
	{
		this.setPitch(pitchValue);
		this.setYaw(yawValue);
	}
	
	public void rotateWith(float pitchValue, float yawValue, float rollValue)
	{
		this.rotation = PLRotation.PLRotationMake(pitchValue, yawValue, rollValue);
	}
	
	public void rotateWith(CGPoint startPoint ,CGPoint endPoint)
	{
		this.rotateWith(startPoint, endPoint, rotateSensitivity);
	}
	
	public void rotateWith(CGPoint startPoint, CGPoint endPoint, float sensitivity)
	{
		this.setPitch(this.getPitch() + ((endPoint.y - startPoint.y) / sensitivity));
		this.setYaw(this.getYaw() + ((startPoint.x - endPoint.x) / sensitivity));
	}
	
	/**position property methods*/

	public void setPosition(PLPosition value)
	{
		this.setX(value.x);
		this.setY(value.y);
		this.setZ(value.z);
	}
	
	float getX()
	{
		return position.x;	
	}
	
	void setX(float value)
	{
		if(isXAxisEnabled)
			position.x = PLMath.valueInRange(value, xRange); 
	}
	
	float getY()
	{
		return position.y;
	}
	
	void setY(float value)
	{
		if(isYAxisEnabled)
			position.y = PLMath.valueInRange(value, yRange);
	}
	
	float getZ()
	{
		return position.z;
	}
	
	void setZ(float value)
	{
		if(isZAxisEnabled)
			position.z = PLMath.valueInRange(value, zRange);
	}
	
	/**rotation property methods*/

	public void setRotation(PLRotation value)
	{
		this.setPitch(value.pitch);
		this.setYaw(value.yaw);
		this.setRoll(value.roll);
	}
	
	float getPitch()
	{
		return rotation.pitch;
	}
	
	void setPitch(float value)
	{
		if(isPitchEnabled)
			rotation.pitch = PLMath.normalizeAngle(value, pitchRange);
	}
	
	float getYaw()
	{
		return rotation.yaw;
	}
	
	void setYaw(float value)
	{
		if(isYawEnabled)
			rotation.yaw = PLMath.normalizeAngle(value, PLRange.PLRangeMake(-yawRange.max, -yawRange.min));
	}
	
	float getRoll()
	{
		return rotation.roll;
	}
	
	void setRoll(float value)
	{
		if(isRollEnabled)
			rotation.roll = PLMath.normalizeAngle(value, rollRange);
	}
	
	/**orientation methods*/
	
	public void setOrientation(UIDeviceOrientation value)
	{
		if(value != UIDeviceOrientation.UIDeviceOrientationFaceUp && value != UIDeviceOrientation.UIDeviceOrientationFaceDown)
		{
			oldOrientation = orientation;
			orientation = value;
		}
	}

	/**utility methods*/
	
	public void clonePropertiesOf(PLObject value)
	{
		this.setXAxisEnabled(value.isXAxisEnabled);
		this.setYAxisEnabled(value.isYAxisEnabled);
		this.setZAxisEnabled(value.isZAxisEnabled);
		
		this.setPitchEnabled(value.isPitchEnabled);
		this.setYawEnabled(value.isYawEnabled);
		this.setRollEnabled(value.isRollEnabled);
		
		this.setReverseRotation(value.isReverseRotation);
		
		this.setRotateSensitivity(value.rotateSensitivity);
		
		this.setXRange(value.xRange.clone());
		this.setYRange(value.yRange.clone());
		this.setZRange(value.zRange.clone());
		
		this.setPitchRange(value.pitchRange.clone());
		this.setYawRange(value.yawRange.clone());
		this.setRollRange(value.rollRange.clone());
		
		this.setX(value.getX());
		this.setY(value.getY());
		this.setZ(value.getZ());
		
		this.setPitch(value.getPitch());
		this.setYaw(value.getYaw());
		this.setRoll(value.getRoll());
		
		this.setOrientation(value.getOrientation());
	}
}