package com.android.panoramagl.hellopanorama;

import javax.microedition.khronos.opengles.GL10;

import com.android.panoramagl.PLTexture;
import com.android.panoramagl.PLView;
import com.android.panoramagl.enumeration.PLViewType;
import com.android.panoramagl.structs.PLRange;
import android.util.Log;

public class HelloPanorama extends PLView
{
	@Override
	protected void onGLContextCreated(GL10 gl)
	{
        super.onGLContextCreated(gl);
        
        try
        {
        	//If you want to use setDeviceOrientationEnabled(true), activity orientation only must be portrait. Eg. android:screenOrientation="portrait"
	    	this.setDeviceOrientationEnabled(false);
	    	
	    	//You can use accelerometer
	    	this.setAccelerometerEnabled(false);
	    	this.setAccelerometerLeftRightEnabled(true);
	    	this.setAccelerometerUpDownEnabled(false);
	    	
	    	//Scrolling and Inertia
	    	this.setScrollingEnabled(true);
	    	this.setInertiaEnabled(true);
	    	
	    	//setFovRange determines Zoom range. Range values from -1.0f to 1.0f
	    	this.getCamera().setFovRange(PLRange.PLRangeMake(0.0f, 1.0f));
	    	
	    	//Example with Sphere type (you need one image)
	    	this.setType(PLViewType.PLViewTypeSpherical);
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.pano)));
	    	
	    	/*
	    	//Example with Cube Faces type (you need an image for each cube face)
	    	this.setType(PLViewType.PLViewTypeCubeFaces);
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.front)));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.back)));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.left)));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.right)));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.top)));
	    	this.addTextureAndRelease(PLTexture.textureWithImage(this.getImageWithResouce(R.drawable.bottom)));
	    	*/
        }
        catch(Throwable ex)
        {
        	Log.e("HelloPanorama::onGLContextCreated", "Error:" + ex.getMessage());
        }
	}
}
