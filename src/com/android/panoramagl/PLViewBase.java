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

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.android.panoramagl.enumeration.PLOrientationSupported;
import com.android.panoramagl.iphone.CAEAGLLayer;
import com.android.panoramagl.iphone.NSTimer;
import com.android.panoramagl.iphone.UITouch;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.iphone.structs.CGPoint;
import com.android.panoramagl.iphone.structs.CGSize;
import com.android.panoramagl.iphone.structs.UIAcceleration;
import com.android.panoramagl.structs.PLRange;
import com.android.panoramagl.structs.PLShakeData;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.GestureDetector.OnDoubleTapListener;

public abstract class PLViewBase extends Activity implements SensorEventListener, OnDoubleTapListener
{
	protected PLRenderer renderer;
	protected PLScene scene;
	
	protected NSTimer animationTimer;
	protected float animationInterval;
	
	protected CGPoint startPoint, endPoint;
	
	protected boolean isValidForFov;
	protected float fovDistance;
	
	protected boolean isDeviceOrientationEnabled, isValidForOrientation;
	protected UIDeviceOrientation deviceOrientation;
	protected PLOrientationSupported deviceOrientationSupported;
	
	protected boolean isAccelerometerEnabled, isAccelerometerLeftRightEnabled, isAccelerometerUpDownEnabled;
	protected float accelerometerSensitivity;
	protected float accelerometerInterval;
	
	protected boolean isScrollingEnabled, isValidForScrolling, isScrolling;
	protected int minDistanceToEnableScrolling;

	protected boolean isInertiaEnabled, isValidForInertia;
	protected NSTimer inertiaTimer;
	protected float inertiaInterval;
	protected float inertiaStepValue;
	
	protected boolean isResetEnabled;
	protected boolean isShakeResetEnabled;

	protected boolean isValidForTouch;
	
	protected PLShakeData shakeData;
	
	protected PLViewEventListener delegate;
	
	protected NSTimer getAnimationTimer()
	{
		return animationTimer;
	}

	public float getAnimationInterval()
	{
		return animationInterval;
	}
	
	public PLViewEventListener getDelegate()
	{
		return delegate;
	}

	public void setDelegate(PLViewEventListener delegate)
	{
		this.delegate = delegate;
	}

	public boolean isDeviceOrientationEnabled()
	{
		return isDeviceOrientationEnabled;
	}
	
	public void setDeviceOrientationEnabled(boolean isDeviceOrientationEnabled)
	{
		this.isDeviceOrientationEnabled = isDeviceOrientationEnabled;
	}

	public UIDeviceOrientation getDeviceOrientation()
	{
		return deviceOrientation;
	}

	public PLOrientationSupported getDeviceOrientationSupported()
	{
		return deviceOrientationSupported;
	}

	public void setDeviceOrientationSupported(PLOrientationSupported deviceOrientationSupported)
	{
		this.deviceOrientationSupported = deviceOrientationSupported;
	}

	public boolean isAccelerometerEnabled()
	{
		return isAccelerometerEnabled;
	}

	public void setAccelerometerEnabled(boolean isAccelerometerEnabled)
	{
		this.isAccelerometerEnabled = isAccelerometerEnabled;
	}

	public boolean isAccelerometerLeftRightEnabled()
	{
		return isAccelerometerLeftRightEnabled;
	}

	public void setAccelerometerLeftRightEnabled(boolean isAccelerometerLeftRightEnabled)
	{
		this.isAccelerometerLeftRightEnabled = isAccelerometerLeftRightEnabled;
	}

	public boolean isAccelerometerUpDownEnabled()
	{
		return isAccelerometerUpDownEnabled;
	}

	public void setAccelerometerUpDownEnabled(boolean isAccelerometerUpDownEnabled)
	{
		this.isAccelerometerUpDownEnabled = isAccelerometerUpDownEnabled;
	}

	public float getAccelerometerSensitivity()
	{
		return accelerometerSensitivity;
	}

	public float getAccelerometerInterval()
	{
		return accelerometerInterval;
	}

	public CGPoint getStartPoint()
	{
		return startPoint;
	}

	public void setStartPoint(CGPoint startPoint)
	{
		this.startPoint = startPoint;
	}

	public CGPoint getEndPoint()
	{
		return endPoint;
	}

	public void setEndPoint(CGPoint endPoint)
	{
		this.endPoint = endPoint;
	}

	public boolean isScrollingEnabled()
	{
		return isScrollingEnabled;
	}

	public void setScrollingEnabled(boolean isScrollingEnabled)
	{
		this.isScrollingEnabled = isScrollingEnabled;
	}

	public int getMinDistanceToEnableScrolling()
	{
		return minDistanceToEnableScrolling;
	}

	public void setMinDistanceToEnableScrolling(int minDistanceToEnableScrolling)
	{
		this.minDistanceToEnableScrolling = minDistanceToEnableScrolling;
	}

	public boolean isInertiaEnabled()
	{
		return isInertiaEnabled;
	}

	public void setInertiaEnabled(boolean isInertiaEnabled)
	{
		this.isInertiaEnabled = isInertiaEnabled;
	}

	public float getInertiaInterval()
	{
		return inertiaInterval;
	}

	public void setInertiaInterval(float inertiaInterval)
	{
		this.inertiaInterval = inertiaInterval;
	}

	public boolean isResetEnabled()
	{
		return isResetEnabled;
	}

	public void setResetEnabled(boolean isResetEnabled)
	{
		this.isResetEnabled = isResetEnabled;
	}
	
	public boolean isShakeResetEnabled()
	{
		return isShakeResetEnabled;
	}

	public void setShakeResetEnabled(boolean isShakeResetEnabled)
	{
		this.isShakeResetEnabled = isShakeResetEnabled;
	}
	
	/**init methods*/
	
	protected void allocAndInitVariables() throws Exception
	{
		scene = PLScene.scene();
		renderer = PLRenderer.rendererWithView(this, scene);
	}
	
	protected void initializeValues()
	{
		animationInterval = PLConstants.kDefaultAnimationTimerInterval;
		
		isAccelerometerEnabled = false;
		isAccelerometerLeftRightEnabled = true;
		isAccelerometerUpDownEnabled = false;
		accelerometerSensitivity = PLConstants.kDefaultAccelerometerSensitivity;
		accelerometerInterval = PLConstants.kDefaultAccelerometerInterval;
		
		isDeviceOrientationEnabled = false;
		deviceOrientation = UIDeviceOrientation.UIDeviceOrientationPortrait;
		deviceOrientationSupported = PLOrientationSupported.PLOrientationSupportedAll;
		
		isScrollingEnabled = false;
		minDistanceToEnableScrolling = PLConstants.kDefaultMinDistanceToEnableScrolling;
		
		isInertiaEnabled = true;
		inertiaInterval = PLConstants.kDefaultInertiaInterval;
		
		isValidForTouch = false;
		
		isResetEnabled = isShakeResetEnabled = true;
		
		shakeData = PLShakeData.PLShakeDataMake(-1);
		
		this.reset();
	}
	
	public void reset()
	{
		this.stopAnimationInternally();
		this.stopInertia();
		isValidForFov = isValidForScrolling = isScrolling = isValidForInertia = isValidForOrientation = false;
		startPoint = CGPoint.CGPointMake(0.0f, 0.0f); endPoint = CGPoint.CGPointMake(0.0f, 0.0f);
		fovDistance = 0.0f;
		if(isDeviceOrientationEnabled)
			this.setDeviceOrientation(this.currentDeviceOrientation());
	}
	
	/**layer method*/
	
	public static Class<?> layerClass()
	{
	    return CAEAGLLayer.class;
	}
	
	/**draw methods*/

	public void drawView()
	{
		if(isScrolling && delegate != null && !delegate.onShouldScroll(this, startPoint, endPoint))
			return;
		this.drawViewInternally();
		if(isScrolling && delegate != null)
			delegate.onDidScroll(this, startPoint, endPoint);
	}
	
	public void drawViewNTimes(int times)
	{
		for(int i = 0; i < times; i++)
			this.drawView();
	}

	protected void drawViewInternally()
	{
	}

	protected void drawViewInternallyNTimes(int times)
	{
		for(int i = 0; i < times; i++)
			this.drawViewInternally();
	}
	
	/**animation methods*/
	
	public void startAnimation()
	{
		this.animationTimer = NSTimer.scheduledTimerWithTimeInterval(animationInterval, this, PLUtils.getMethod(this, "drawView"), null, true);
		if(isScrollingEnabled)
			isValidForScrolling = true;
		this.stopInertia();
	}
	
	public void stopAnimation()
	{
		this.stopAnimationInternally();
		this.stopInertia();
	}
	
	protected void stopAnimationInternally()
	{		
		if(animationTimer != null)		
			this.animationTimer.invalidate();
	    this.animationTimer = null;
		if(isScrollingEnabled)
		{
			isValidForScrolling = false;
			if(!isInertiaEnabled)
				isValidForTouch = false;
		}
		else
			isValidForTouch = false;
	}
	
	public void setAnimationTimer(NSTimer newTimer) 
	{
		if(animationTimer != null)
			animationTimer.invalidate();
		animationTimer = newTimer;
	}
	
	public void setAnimationInterval(float interval)
	{
		animationInterval = interval;
		if (animationTimer != null) 
		{
			this.stopAnimation();
			this.startAnimation();
	    }
	}
	
	/**action methods*/
	
	protected boolean calculateFov(List<UITouch> touches)
	{
		if(touches.size() >= 2)
		{
			CGPoint point0 = touches.get(0).locationInView(this);
			CGPoint point1 = touches.get(1).locationInView(this);
			
			float distance = PLMath.distanceBetweenPoints(point0, point1);
			
			if(Math.abs(distance - fovDistance) < scene.getCurrentCamera().getMinDistanceToEnableFov())
				return false;
			
			startPoint.x = 0.0f;
			startPoint.y = 0.0f;
			endPoint.x = 0.0f;
			endPoint.y = 0.0f;
			
			distance = Math.abs(fovDistance) <= distance ? distance : -distance;
			boolean isZoomIn = (distance >= 0);
			boolean isCancelable = false;
			
			if(delegate != null)
				isCancelable = delegate.onShouldRunZooming(this, distance, isZoomIn, !isZoomIn);
					
			if(!isCancelable)
			{
				fovDistance = distance;
				if(delegate != null)
					delegate.onDidRunZooming(this, fovDistance, isZoomIn, !isZoomIn);
			}
			return true;
		}
		return false;
	}
	
	protected boolean executeDefaultAction(List<UITouch> touches)
	{
		return this.executeDefaultAction(touches, true);
	}
	
	protected boolean executeDefaultAction(List<UITouch> touches, boolean isFovCalculated)
	{
		if(isValidForFov)
			this.calculateFov(touches);
		else
		{
			boolean isCancelable = false;
			if (touches.size() == 3) 
			{
				if(isResetEnabled)
				{
					if(delegate != null)
						isCancelable = delegate.onShouldReset(this);
					if(!isCancelable)
					{
						this.reset();
						this.drawViewInternally();
						if(delegate != null)
							delegate.onDidReset(this);
					}
				}
			}
			else if(touches.size() >= 2)
			{
				if(delegate != null)
					isCancelable = delegate.onShouldBeginZooming(this);
				if(!isCancelable)
				{
					isValidForFov = true;
					if(isFovCalculated)
						this.calculateFov(touches);
					if(delegate != null)
						delegate.onDidBeginZooming(this, touches.get(0).locationInView(this), touches.get(1).locationInView(this));
				}
				return false;
			}
			else
				return false;
		}
		return true;
	}
	
	/**touch methods*/
	
	protected boolean isTouchInView(List<UITouch> touches)
	{
		for(UITouch touch : touches)
			if(touch.getView() != this)
				return false;
		return true;
	}
	
	protected CGPoint getLocationOfFirstTouch(List<UITouch> touches)
	{
		UITouch touch = touches.get(0);
		return touch.locationInView(this);
	}
	
	//Equivalent - (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event 
	protected void touchesBegan(List<UITouch> touches, MotionEvent event) 
	{	
		if(!this.isTouchInView(touches))
			return;
		
		if(delegate != null && !delegate.onShouldBeginTouching(this, touches, event))
			return;
		
		if(isValidForFov)
			return;
		
		this.stopInertia();
		if(touches.get(0).getTapCount() == 2)
		{
			if(isValidForScrolling)
			{
				this.stopAnimationInternally();
				isScrolling = false;
				
				if(delegate != null)
					delegate.onDidEndScrolling(this, startPoint, endPoint);
				
				return;
			}
		}
		
		isValidForTouch = true;
		
		if(!this.executeDefaultAction(touches, false))
		{
			startPoint = this.getLocationOfFirstTouch(touches); endPoint = startPoint.clone();
			this.startAnimation();
		}
		
		if(delegate != null)
			delegate.onDidBeginTouching(this, touches, event);
	}

	//Equivalent - (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
	protected void touchesMoved(List<UITouch> touches, MotionEvent event)
	{
		if(!this.isTouchInView(touches))
			return;
		
		if(delegate != null && !delegate.onShouldTouch(this, touches, event))
			return;
		
		if(!this.executeDefaultAction(touches))
			endPoint = this.getLocationOfFirstTouch(touches);
		
		if(delegate != null)
			delegate.onDidTouch(this, touches, event);
	}
	
	//Equivalent - (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
	protected void touchesEnded(List<UITouch> touches, MotionEvent event)
	{ 	
		if(!this.isTouchInView(touches))
			return;
		
		if(delegate != null && !delegate.onShouldEndTouching(this, touches, event))
			return;
		
		if(isValidForFov)
		{
			this.stopAnimationInternally();
			isValidForFov = isValidForTouch = false;
			startPoint = CGPoint.CGPointMake(0.0f, 0.0f); endPoint = CGPoint.CGPointMake(0.0f, 0.0f);
		}
		else 
		{
			if(!this.executeDefaultAction(touches, false))
			{
				endPoint = this.getLocationOfFirstTouch(touches);
				boolean isCancelable = false;
					
				if(isScrollingEnabled && delegate != null)
					isCancelable = delegate.onShouldBeingScrolling(this, startPoint, endPoint);
					
				if(isScrollingEnabled && !isCancelable)
				{
					boolean isValidForMove = ((startPoint.x == endPoint.x && startPoint.y == endPoint.y) || PLMath.distanceBetweenPoints(startPoint, endPoint) <= minDistanceToEnableScrolling);
					if(isInertiaEnabled)
					{
						this.stopAnimationInternally();
						if(isValidForMove)
							isValidForTouch = false;
						else
						{
							isCancelable = false;
							if(delegate != null)
								isCancelable = delegate.onShouldBeginInertia(this, startPoint, endPoint);
							if(!isCancelable)
								this.startInertia();
						}
					}
					else
					{
						if(isValidForMove)
							this.stopAnimationInternally();
						else
						{
							isScrolling = true;
							if(delegate != null)
								delegate.onDidBeginScrolling(this, startPoint, endPoint);
						}
					}
				}
				else
				{
					startPoint = endPoint.clone();
					this.stopAnimationInternally();
					if(delegate != null)
						delegate.onDidEndMoving(this, startPoint, endPoint);
				}
			}
		}
		
		if(delegate != null)
			delegate.onDidEndTouching(this, touches, event);
	}
    
    /**inertia methods*/
	
	protected void startInertia()
	{
		this.stopInertia();
		float interval = inertiaInterval / PLMath.distanceBetweenPoints(startPoint, endPoint);
		if(interval < 0.01f)
		{
			inertiaStepValue = 0.01f / interval;
			interval = 0.01f;
		}
		else
			inertiaStepValue = 1.0f;
		inertiaTimer = NSTimer.scheduledTimerWithTimeInterval(interval, this, PLUtils.getMethod(this, "inertia"), null, true);
		
		if(delegate != null)
			delegate.onDidBeginInertia(this, startPoint, endPoint);
	}
	
	protected void stopInertia()
	{
		if(inertiaTimer != null)
			inertiaTimer.invalidate();
		inertiaTimer = null;
	}
	
	protected void inertia()
	{
		if(delegate != null && !delegate.onShouldRunInertia(this, startPoint, endPoint))
			return;
		
		float m = (endPoint.y - startPoint.y) / (endPoint.x - startPoint.x);
		float b = (startPoint.y * endPoint.x - endPoint.y * startPoint.x) / (endPoint.x - startPoint.x);
		float x, y, add;
		
		if(Math.abs(endPoint.x - startPoint.x) >= Math.abs(endPoint.y - startPoint.y))
		{
			add = (endPoint.x > startPoint.x ? -inertiaStepValue : inertiaStepValue);
			x = endPoint.x + add;
			if((add > 0.0f && x > startPoint.x) || (add <= 0.0f && x < startPoint.x))
			{
				this.stopInertia();
				isValidForTouch = false;
				
				if(delegate != null)
					delegate.onDidEndInertia(this, startPoint, endPoint);
				
				return;
			}
			y = m * x + b;
		}
		else
		{
			add = (endPoint.y > startPoint.y ? -inertiaStepValue : inertiaStepValue);
			y = endPoint.y + add;
			if((add > 0.0f && y > startPoint.y) || (add <= 0.0f && y < startPoint.y))
			{
				this.stopInertia();
				isValidForTouch = false;
				
				if(delegate != null)
					delegate.onDidEndInertia(this, startPoint, endPoint);
				
				return;
			}
			x = (y - b)/m;
		}
		endPoint = CGPoint.CGPointMake(x, y);
		this.drawView();
		
		if(delegate != null)
			delegate.onDidRunInertia(this, startPoint, endPoint);
	}
    
    /**accelerometer methods*/
    
	public void setAccelerometerInterval(float value)
	{
		accelerometerInterval = value;
		this.activateAccelerometer();
	}
	
	public void setAccelerometerSensitivity(float value)
	{
		accelerometerSensitivity = PLMath.valueInRange(value, PLRange.PLRangeMake(PLConstants.kAccelerometerSensitivityMinValue, PLConstants.kAccelerometerSensitivityMaxValue));
	}
	
	protected void activateAccelerometer()
	{		
		if(sensorManager != null)
			if(!sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), (int) accelerometerInterval * 1000))
				Log.e("Accelerometer", "Accelerometer not running on the device!");
	}
	
	protected void deactiveAccelerometer()
	{
		if(sensorManager != null)
			sensorManager.unregisterListener(this);
	}
	
	//Equivalent - (void)accelerometer:(UIAccelerometer *)accelerometer didAccelerate:(UIAcceleration *)acceleration
	public void accelerometer(SensorEvent event, UIAcceleration acceleration)
    {
		if(isValidForTouch || isValidForOrientation)
			return;
		
		if(this.resetWithShake(acceleration))
			return;
		
		if(isAccelerometerEnabled)
		{
			if(delegate != null && !delegate.onShouldAccelerate(this, acceleration, event))
				return;
			
			float x = 0, y = 0;
			float factor = PLConstants.kAccelerometerMultiplyFactor * accelerometerSensitivity;
			CGSize size = this.getSize();
			if(isDeviceOrientationEnabled)
			{
				switch (deviceOrientation) 
				{
					case UIDeviceOrientationUnknown:
					case UIDeviceOrientationPortrait:
					case UIDeviceOrientationPortraitUpsideDown:
						x = (isAccelerometerLeftRightEnabled ? acceleration.x : 0.0f);
						y = (isAccelerometerUpDownEnabled ? acceleration.z : 0.0f);
						startPoint = CGPoint.CGPointMake(size.width / 2.0f, size.height / 2.0f);
						break;
					case UIDeviceOrientationLandscapeLeft:
					case UIDeviceOrientationLandscapeRight:
						x = (isAccelerometerUpDownEnabled ? -acceleration.z : 0.0f);
						y = (isAccelerometerLeftRightEnabled ? -acceleration.y : 0.0f);
						startPoint = CGPoint.CGPointMake(size.height / 2.0f, size.width / 2.0f);
						break;
					default:
						startPoint = CGPoint.CGPointMake(size.width / 2.0f, size.height / 2.0f);
						break;
				}
			}
			else 
			{
				UIDeviceOrientation currentOrientation = this.currentDeviceOrientation();
				switch (currentOrientation) 
				{
					case UIDeviceOrientationUnknown:
					case UIDeviceOrientationPortrait:
					case UIDeviceOrientationPortraitUpsideDown:
						x = (isAccelerometerLeftRightEnabled ? acceleration.x : 0.0f);
						y = (isAccelerometerUpDownEnabled ? acceleration.z : 0.0f);
						startPoint = CGPoint.CGPointMake(size.width / 2.0f, size.height / 2.0f);
						if(currentOrientation == UIDeviceOrientation.UIDeviceOrientationPortraitUpsideDown)
						{
							x = -x;
							y = -y;
						}
						break;
					case UIDeviceOrientationLandscapeLeft:
					case UIDeviceOrientationLandscapeRight:
						x = (isAccelerometerLeftRightEnabled ? acceleration.y : 0.0f);
						y = (isAccelerometerUpDownEnabled ? acceleration.z : 0.0f);
						startPoint = CGPoint.CGPointMake(size.height / 2.0f, size.width / 2.0f);
						if(currentOrientation == UIDeviceOrientation.UIDeviceOrientationLandscapeRight)
						{
							x = -x;
							y = -y;
						}
						break;
					default:
						startPoint = CGPoint.CGPointMake(size.width / 2.0f, size.height / 2.0f);
						break;
				}
			}

			endPoint = CGPoint.CGPointMake(startPoint.x + (x * factor), startPoint.y + (y * factor));
			this.drawView();
			
			if(delegate != null)
				delegate.onDidAccelerate(this, acceleration, event);
		}
    }
	
	/**orientation methods*/
	
	public void setDeviceOrientation(UIDeviceOrientation orientation)
	{
		if(deviceOrientation != orientation && this.isOrientationValid(orientation))
		{
			deviceOrientation = orientation;
			this.changeOrientation(orientation);
		}
	}
	
	protected void activateOrientation()
	{
		if(sensorManager != null)
			if(!sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL))
				Log.e("Orientation", "Orientation not running on the device!");
	}
	
	protected void deactiveOrientation()
	{
		if(sensorManager != null)
			sensorManager.unregisterListener(this);
	}
	
	//Equivalent - (void)didRotate:(NSNotification *)notification
	protected void didRotate(SensorEvent event)
	{
		UIDeviceOrientation currentOrientation = this.currentDeviceOrientation();
		
		if(isDeviceOrientationEnabled && this.isOrientationValid(currentOrientation))
		{	
			if(delegate != null && !delegate.onShouldRotate(this, currentOrientation))
				return;
			
			deviceOrientation = currentOrientation;
			this.changeOrientation(deviceOrientation);
			
			if(delegate != null)
				delegate.onDidRotate(this, deviceOrientation);
		}
	}
	
	protected void changeOrientation(UIDeviceOrientation orientation)
	{
		isValidForOrientation = true;
		this.orientationChanged(orientation);
		this.drawViewInternally();
		isValidForOrientation = false;
	}
	
	protected abstract void orientationChanged(UIDeviceOrientation orientation);
	
	protected boolean isOrientationValid(UIDeviceOrientation orientation)
	{
		PLOrientationSupported value;
		switch (orientation) 
		{
			case UIDeviceOrientationUnknown:
			case UIDeviceOrientationPortrait:
				value = PLOrientationSupported.PLOrientationSupportedPortrait;
				break;
			case UIDeviceOrientationPortraitUpsideDown:
				value = PLOrientationSupported.PLOrientationSupportedPortraitUpsideDown;
				break;
			case UIDeviceOrientationLandscapeLeft:
				value = PLOrientationSupported.PLOrientationSupportedLandscapeLeft;
				break;
			case UIDeviceOrientationLandscapeRight:
				value = PLOrientationSupported.PLOrientationSupportedLandscapeRight;
				break;
			default:
				return false;
		}
		return ((deviceOrientationSupported.customOrdinal() & value.customOrdinal()) != 0);
	}
	
	public UIDeviceOrientation currentDeviceOrientation()
	{
		return currentDeviceOrientation;
	}
	
	/**shake methods*/
	
	protected boolean resetWithShake(UIAcceleration acceleration)
	{
		if(!isResetEnabled)
			return false;
		
		boolean result = false;
				
		long currentTime = System.currentTimeMillis();
		
	    if ((currentTime - shakeData.lastTime) > PLConstants.kShakeDiffTime)
	    {
	    	long diffTime = (currentTime - shakeData.lastTime);
	    	shakeData.lastTime = currentTime;
	    	
	    	shakeData.shakePosition.x = acceleration.x;
	    	shakeData.shakePosition.y = acceleration.y;
	    	shakeData.shakePosition.z = acceleration.z;
 
	    	float speed = Math.abs(shakeData.shakePosition.x + shakeData.shakePosition.y + shakeData.shakePosition.z - shakeData.shakeLastPosition.x - shakeData.shakeLastPosition.y - shakeData.shakeLastPosition.z) / diffTime * 10000;
	    	if (speed > PLConstants.kShakeThreshold)
	    	{
	    		this.reset();
	    		this.drawViewInternally();
	    		result = true;
	    	}
	    	
	    	shakeData.shakeLastPosition.x = shakeData.shakePosition.x; 
	    	shakeData.shakeLastPosition.y = shakeData.shakePosition.y; 
	    	shakeData.shakeLastPosition.z = shakeData.shakePosition.z;
	    }
	    return result;
	}
	
	/**util methods*/
	
	public Bitmap getImageWithResouce(int resID)
	{
		try
		{
			return BitmapFactory.decodeResource(this.getResources(), resID); 
		}
		catch(Exception ex)
		{
			Log.e("PLViewBase::getImage", ex.getMessage());
		}
		return null;
	}
	
	/**dealloc methods*/

	protected void finalize() throws Throwable 
	{
		this.stopAnimation();
		
		this.deactiveOrientation();
		this.deactiveAccelerometer();
		
		this.scene = null;
		this.renderer = null;
		
		this.shakeData = null;
		
		super.finalize();
	}
	
	/**android methods*/
		
	protected void onGLContextCreated(GL10 gl)
	{
	}
	
	protected SensorManager sensorManager = null;
	
	//Equivalent - (id)initWithCoder:(NSCoder*)coder
	@Override
    protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        try
        {
	        this.allocAndInitVariables();
	        this.initializeValues();
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFormat(PixelFormat.TRANSLUCENT);
	   		setContentView(this.renderer);
			sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        }
        catch(Exception ex)
        {
        	Log.e("PLViewBase::onCreate", "Error:" + ex.getMessage());
        }
    }
	
	@Override
    protected void onResume()
    {
		super.onResume();
		
		this.activateAccelerometer();
		this.activateOrientation();
    }
   
    @Override
    protected void onPause()
    {
    	this.deactiveAccelerometer();
    	this.deactiveOrientation();    	
    	this.stopAnimation();
    	
    	super.onPause();
    }
    
	public CGSize getSize()
	{
		return this.renderer.getSize();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getAction() & MotionEvent.ACTION_MASK)
		{
			case MotionEvent.ACTION_DOWN:
				this.touchesBegan(this.getTouches(event), event);
				break;
			case MotionEvent.ACTION_MOVE:
				this.touchesMoved(this.getTouches(event), event);
				break;
			case MotionEvent.ACTION_UP:
				this.touchesEnded(this.getTouches(event), event);
				break;
		}
		return false;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
    {	
    }
	
	protected List<UITouch> getTouches(MotionEvent event, int tapCount)
	{
		List<UITouch> touches = new ArrayList<UITouch>();
		for (int i = 0; i < event.getPointerCount(); i++)
			touches.add(new UITouch(this, CGPoint.CGPointMake(event.getX(i), event.getY(i)), tapCount));
		return touches;
	}
	
	protected List<UITouch> getTouches(MotionEvent event)
	{
		return this.getTouches(event, 1);
	}
	
	public boolean onDoubleTap(MotionEvent event)
	{
		this.touchesBegan(this.getTouches(event, 2), event);
        return false;
	}
	
	public boolean onDoubleTapEvent(MotionEvent event)
	{
        return false;
	}
	
	public boolean onSingleTapConfirmed(MotionEvent event)
	{
        return false;
	}
	
	protected UIDeviceOrientation currentDeviceOrientation = UIDeviceOrientation.UIDeviceOrientationPortrait;
	    
	public void onSensorChanged(SensorEvent event)
	{		
		synchronized (this)
		{			
			switch(event.sensor.getType())
			{
				case Sensor.TYPE_ACCELEROMETER:
					this.accelerometer(event, UIAcceleration.UIAccelerationMake(event.values));			
					break;
				case Sensor.TYPE_ORIENTATION:
					UIDeviceOrientation newOrientation = currentDeviceOrientation;
										
					int pitch = (int)event.values[1];
					int roll = (int)event.values[2];
						
		            if (roll <= 45 && roll >= -45)
		            {
			            if (pitch <= -45 && pitch >= -135)
			            	newOrientation = UIDeviceOrientation.UIDeviceOrientationPortrait;
			            else if (pitch >= 45 && pitch <= 135)
			            	newOrientation = UIDeviceOrientation.UIDeviceOrientationPortraitUpsideDown;
		            }
		            else if (roll > 45)
		            	newOrientation = UIDeviceOrientation.UIDeviceOrientationLandscapeRight;
		            else if (roll < -45)
		            	newOrientation = UIDeviceOrientation.UIDeviceOrientationLandscapeLeft;
		            
		            if(currentDeviceOrientation != newOrientation)
		            {
		            	currentDeviceOrientation = newOrientation;
		            	this.didRotate(event);
		            }
					break;
			}
		}
	}
}