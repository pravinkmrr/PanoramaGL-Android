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

import javax.microedition.khronos.opengles.GL10;

import com.android.panoramagl.enumeration.PLOrientation;
import com.android.panoramagl.iphone.EAGLContext;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.structs.PLRange;
import com.android.panoramagl.structs.PLRotation;

public abstract class PLSceneElementBase extends PLObject 
{	
	protected List<PLTexture> textures;
	protected boolean isVisible, isValid;

	/**property methods*/
	
	public boolean isVisible()
	{
		return isVisible;
	}

	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}

	public boolean isValid()
	{
		return isValid;
	}

	/**init methods*/
	
	public PLSceneElementBase()
	{
		super();
	}
	
	protected void initializeValues()
	{
		super.initializeValues();
		isVisible = true;
		isValid = false;
	}
	
	/**action methods*/
	
	protected void translate()
	{
		GL10 gl = EAGLContext.currentGL();
		gl.glTranslatef(isXAxisEnabled ? position.x : 0.0f, isYAxisEnabled ? position.y : 0.0f, isZAxisEnabled ? position.z : 0.0f);
	}
	
	protected void rotate()
	{
		PLOrientation orientationValue;
		int rotationSign = 0;
		switch (orientation) 
		{
			case UIDeviceOrientationUnknown:
			case UIDeviceOrientationPortrait:
				rotationSign = 1;
			case UIDeviceOrientationPortraitUpsideDown:
				if(rotationSign == 0)
					rotationSign = -1;
				orientationValue = PLOrientation.PLOrientationPortrait;
				break;
			case UIDeviceOrientationLandscapeLeft:
				rotationSign = 1;
			case UIDeviceOrientationLandscapeRight:
				if(rotationSign == 0)
					rotationSign = -1;
				orientationValue = PLOrientation.PLOrientationLandscape;
				break;
			default:
				rotationSign = 1;
				orientationValue = PLOrientation.PLOrientationUnknown;
				break;
		}
		this.internalRotate(rotation, orientationValue, rotationSign);
	}
	
	protected void internalRotate(PLRotation rotationValue, PLOrientation orientationValue ,int rotationSign)
	{
		GL10 gl = EAGLContext.currentGL();
		if(orientationValue == PLOrientation.PLOrientationLandscape)
		{
			if(isPitchEnabled)
				gl.glRotatef(rotationSign * rotationValue.yaw * (isReverseRotation ? -1.0f : 1.0f), 1.0f, 0.0f, 0.0f);
			if(isYawEnabled)
				gl.glRotatef(rotationSign * rotationValue.pitch * (isReverseRotation ? 1.0f : -1.0f), 0.0f, 0.0f, 1.0f);
		}
		else
		{
			if(isPitchEnabled)
				gl.glRotatef(rotationSign * rotationValue.pitch * (isReverseRotation ? 1.0f : -1.0f), 1.0f, 0.0f, 0.0f);
			if(isYawEnabled)
				gl.glRotatef(rotationSign * rotationValue.yaw * (isReverseRotation ? 1.0f : -1.0f), 0.0f, 0.0f, 1.0f);
		}
		if(isRollEnabled)
			gl.glRotatef(rotationValue.roll * (isReverseRotation ? 1.0f : -1.0f), 0.0f, 1.0f, 0.0f);
	}
	
	/**render methods*/
	
	protected void beginRender()
	{
		GL10 gl = EAGLContext.currentGL();
		gl.glPushMatrix();
		
		this.rotate();
		this.translate();
	}
	
	protected void endRender()
	{
		GL10 gl = EAGLContext.currentGL();
		gl.glPopMatrix();
	}
	
	public boolean render()
	{
		if(isVisible && isValid)
		{
			this.beginRender();
			this.internalRender();
			this.endRender();
			return true;
		}
		return false;
	}
	
	protected abstract void internalRender();
	
	/**swap methods*/
	
	protected void swapPitchValues()
	{
		this.swapPitchValuesWithSign(false);
	}
	
	protected void swapPitchValuesWithSign(boolean sign)
	{
		if(sign)
		{
			pitchRange.min = -pitchRange.min;
			pitchRange.max = -pitchRange.max;
		}
		float[] swap = PLUtils.swapFloatValues(pitchRange.min, pitchRange.max);
		pitchRange.min = swap[0];
		pitchRange.max = swap[1];
	}
	
	protected void swapYawValues()
	{
		this.swapYawValuesWithSign(false);
	}
	
	protected void swapYawValuesWithSign(boolean sign)
	{
		if(sign)
		{
			yawRange.min = -yawRange.min;
			yawRange.max = -yawRange.max;
		}
		float[] swap = PLUtils.swapFloatValues(yawRange.min ,yawRange.max);
		yawRange.min = swap[0];
		yawRange.max = swap[1];
	}
	
	protected void swapPitchRangeByYawRange(boolean isSwapPitchValues ,boolean isSwapYawValues, boolean isSwapPitchSign, boolean isSwapYawSign)
	{
		if(isSwapPitchValues)
			this.swapPitchValues();
		if(isSwapYawValues)
			this.swapYawValues();
		
		if(isSwapPitchSign)
			pitchRange = PLRange.PLRangeMake(-pitchRange.min, -pitchRange.max);
		if(isSwapYawSign)
			yawRange = PLRange.PLRangeMake(-yawRange.min, -yawRange.max);
		
		PLRange swapRange = pitchRange;
		pitchRange = yawRange;
		yawRange = swapRange;
	}
	
	protected void swapPitchRangeByYawRange(int swapPitchValue, int swapYawValue)
	{
		this.swapPitchRangeByYawRange(swapPitchValue != 0, swapYawValue != 0, swapPitchValue < 0, swapYawValue < 0);
	}
	
	/**orientation methods*/
	
	public void setOrientation(UIDeviceOrientation value)
	{
		if(value != UIDeviceOrientation.UIDeviceOrientationFaceUp && value != UIDeviceOrientation.UIDeviceOrientationFaceDown)
		{
			if(orientation != value)
			{
				oldOrientation = orientation;
				orientation = value;
				this.changeOrientation(orientation, oldOrientation);
			}
		}
	}
	
	protected void changeOrientation(UIDeviceOrientation orientationValue, UIDeviceOrientation oldOrientationValue)
	{
		float pitch = rotation.pitch, yaw = rotation.yaw;
		float swap = rotation.pitch;
		switch (orientationValue) 
		{
			//The orientation of the device cannot be determined.
			case UIDeviceOrientationUnknown:
			//The device is in portrait mode, with the device held upright and the home button at the bottom. (normal)
			case UIDeviceOrientationPortrait:
				switch(oldOrientationValue)
				{
					case UIDeviceOrientationPortraitUpsideDown:
						pitch = -pitch;
						yaw = -yaw;
						this.swapPitchValuesWithSign(true);
						this.swapYawValuesWithSign(true);
						break;
					case UIDeviceOrientationLandscapeLeft:
						pitch = -yaw;
						yaw = swap;
						this.swapPitchRangeByYawRange(-1, 0);
						break;
					case UIDeviceOrientationLandscapeRight:
						pitch = yaw;	
						yaw = -swap;
						this.swapPitchRangeByYawRange(0, -1);
						break;
				}
				break;
			//The device is in portrait mode but upside down, with the device held upright and the home button at the top. (normal mirror)
			case UIDeviceOrientationPortraitUpsideDown:
				switch(oldOrientationValue)
				{
					case UIDeviceOrientationPortrait:
						pitch = -pitch;
						yaw = -yaw;
						this.swapPitchValuesWithSign(true);
						this.swapYawValuesWithSign(true);
						break;
					case UIDeviceOrientationLandscapeLeft:
						pitch = yaw;
						yaw = -swap;
						this.swapPitchRangeByYawRange(0, -1);
						break;
					case UIDeviceOrientationLandscapeRight:
						pitch = -yaw;
						yaw = swap;
						this.swapPitchRangeByYawRange(-1, 0);
						break;
				}
				break;
			//The device is in landscape mode, with the device held upright and the home button on the right side. (button right side)
			case UIDeviceOrientationLandscapeLeft:
				switch(oldOrientationValue)
				{
					case UIDeviceOrientationUnknown:
					case UIDeviceOrientationPortrait:
						pitch = yaw;
						yaw = -swap;
						this.swapPitchRangeByYawRange(0, -1);
						break;
					case UIDeviceOrientationPortraitUpsideDown:
						pitch = -yaw;
						yaw = swap;
						this.swapPitchRangeByYawRange(-1, 0);
						break;
					case UIDeviceOrientationLandscapeRight:
						pitch = -pitch;
						yaw = -yaw;
						this.swapPitchValuesWithSign(true);
						this.swapYawValuesWithSign(true);
						break;
				}
				break;
			//The device is in landscape mode, with the device held upright and the home button on the left side. (button left side)
			case UIDeviceOrientationLandscapeRight:
				switch(oldOrientationValue)
				{
					case UIDeviceOrientationUnknown:
					case UIDeviceOrientationPortrait:
						pitch = -yaw;
						yaw = swap;
						this.swapPitchRangeByYawRange(-1, 0);
						break;
					case UIDeviceOrientationPortraitUpsideDown:
						pitch = yaw;
						yaw = -swap;
						this.swapPitchRangeByYawRange(0, -1);
						break;
					case UIDeviceOrientationLandscapeLeft:
						pitch = -pitch;
						yaw = -yaw;
						this.swapPitchValuesWithSign(true);
						this.swapYawValuesWithSign(true);
						break;
				}
				break;
		}
		rotation.pitch = pitch;
		rotation.yaw = yaw;
	}
}
