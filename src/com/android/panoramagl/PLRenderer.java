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

import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES11Ext;
import android.opengl.GLUES;
import android.util.Log;

import com.android.panoramagl.iphone.EAGLContext;
import com.android.panoramagl.iphone.enumeration.EAGLRenderingAPI;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;
import com.android.panoramagl.iphone.structs.CGSize;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PLRenderer extends SurfaceView implements SurfaceHolder.Callback
{
	protected EAGLContext context;
	
	protected IntBuffer backingWidth, backingHeight;

	protected IntBuffer viewRenderbuffer = IntBuffer.allocate(1), viewFramebuffer = IntBuffer.allocate(1), depthRenderbuffer = IntBuffer.allocate(1);
	protected boolean isUsedDepthBuffer;

	protected PLViewBase view;

	protected PLScene scene;
	
	protected UIDeviceOrientation currentOrientation;

	protected float aspect;
	
	/**property methods*/

	public IntBuffer getBackingWidth()
	{
		return backingWidth;
	}

	public IntBuffer getBackingHeight()
	{
		return backingHeight;
	}
	
	public boolean isUsedDepthBuffer()
	{
		return isUsedDepthBuffer;
	}

	public void setUsedDepthBuffer(boolean isUsedDepthBuffer)
	{
		this.isUsedDepthBuffer = isUsedDepthBuffer;
	}
	
	public PLViewBase getView()
	{
		return view;
	}

	public void setView(PLViewBase view)
	{
		this.view = view;
	}

	public PLScene getScene()
	{
		return scene;
	}

	public void setScene(PLScene scene)
	{
		this.scene = scene;
	}
	
	public UIDeviceOrientation getCurrentOrientation()
	{
		return currentOrientation;
	}
	
	/**init methods*/

	public PLRenderer(PLViewBase aView, PLScene aScene) throws Exception
	{
		super(aView);
		
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
	
		this.setView(aView);
		this.setScene(aScene);
	}

	public static PLRenderer rendererWithView(PLViewBase view, PLScene scene) throws Exception
	{
		return new PLRenderer(view, scene);
	}

	protected void initializeValues()
	{
		currentOrientation = UIDeviceOrientation.UIDeviceOrientationUnknown;
		isUsedDepthBuffer = PLConstants.kUseDepthBuffer != 0;
		
		this.destroyFramebuffer();
		this.createFramebuffer();
		aspect = (float)size.width/(float)size.height;
	}

	/**buffer methods*/

	protected boolean createFramebuffer()
	{		
		/*GLES11Ext.glGenFramebuffersOES(1, viewFramebuffer);
		GLES11Ext.glGenRenderbuffersOES(1, viewRenderbuffer);
	    
		GLES11Ext.glBindFramebufferOES(GLES11Ext.GL_FRAMEBUFFER_OES, viewFramebuffer.get(0));
		GLES11Ext.glBindRenderbufferOES(GLES11Ext.GL_RENDERBUFFER_OES, viewRenderbuffer.get(0));
		context.renderbufferStorage(GLES11Ext.GL_RENDERBUFFER_OES, this);
	    //[context renderbufferStorage:GL_RENDERBUFFER_OES fromDrawable:(CAEAGLLayer*)view.layer];
		GLES11Ext.glFramebufferRenderbufferOES(GLES11Ext.GL_FRAMEBUFFER_OES, GLES11Ext.GL_COLOR_ATTACHMENT0_OES, GLES11Ext.GL_RENDERBUFFER_OES, viewRenderbuffer.get(0));
	    
		GLES11Ext.glGetRenderbufferParameterivOES(GLES11Ext.GL_RENDERBUFFER_OES, GLES11Ext.GL_RENDERBUFFER_WIDTH_OES, backingWidth);
		GLES11Ext.glGetRenderbufferParameterivOES(GLES11Ext.GL_RENDERBUFFER_OES, GLES11Ext.GL_RENDERBUFFER_HEIGHT_OES, backingHeight);
	    
	    if (isUsedDepthBuffer) 
		{
	    	GLES11Ext.glGenRenderbuffersOES(1, depthRenderbuffer);
	    	GLES11Ext.glBindRenderbufferOES(GLES11Ext.GL_RENDERBUFFER_OES, depthRenderbuffer.get(0));
	    	GLES11Ext.glRenderbufferStorageOES(GLES11Ext.GL_RENDERBUFFER_OES, GLES11Ext.GL_DEPTH_COMPONENT16_OES, backingWidth.get(0), backingHeight.get(0));
	    	GLES11Ext.glFramebufferRenderbufferOES(GLES11Ext.GL_FRAMEBUFFER_OES, GLES11Ext.GL_DEPTH_ATTACHMENT_OES, GLES11Ext.GL_RENDERBUFFER_OES, depthRenderbuffer.get(0));
	    }
	    
	    if(GLES11Ext.glCheckFramebufferStatusOES(GLES11Ext.GL_FRAMEBUFFER_OES) != GLES11Ext.GL_FRAMEBUFFER_COMPLETE_OES) 
		{
	    	Log.e("createFramebuffer", String.format("failed to make complete framebuffer object %x", GLES11Ext.glCheckFramebufferStatusOES(GLES11Ext.GL_FRAMEBUFFER_OES)));
	        return false;
	    }*/
	    return true;
	}

	protected void destroyFramebuffer()
	{
		/*GLES11Ext.glDeleteFramebuffersOES(1, viewFramebuffer);
	    viewFramebuffer = IntBuffer.allocate(1);
	    GLES11Ext.glDeleteRenderbuffersOES(1, viewRenderbuffer);
	    viewRenderbuffer = IntBuffer.allocate(1);
	    if(depthRenderbuffer != null && depthRenderbuffer.get(0) != 0) 
		{
	    	GLES11Ext.glDeleteRenderbuffersOES(1, depthRenderbuffer);
	        depthRenderbuffer = IntBuffer.allocate(1);
	    }*/
	}

	/**render methods*/

	public void render()
	{
		this.renderWithDeviceOrientation(view.getDeviceOrientation());
	}

	public void renderNTimes(Integer times)
	{
		for(int i = 0; i < times; i++)
			this.render();
	}
	
	public void renderWithDeviceOrientation(UIDeviceOrientation deviceOrientation)
	{		
		UIDeviceOrientation orientation = this.view.isDeviceOrientationEnabled() ? deviceOrientation : this.view.currentDeviceOrientation();
		float tempAspect = (float)size.width/(float)size.height;
		
		if(currentOrientation != orientation || tempAspect != aspect)
		{
			this.destroyFramebuffer();
			this.createFramebuffer();
			aspect = tempAspect;
		}
		
		EAGLContext.setCurrentContext(context);
		//glBindFramebufferOES(GL_FRAMEBUFFER_OES, viewFramebuffer);
		
		GL10 gl = EAGLContext.currentGL();
		
		gl.glViewport(0, 0, size.width, size.height);
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		PLCamera camera = scene.getCurrentCamera();
		float zoomFactor = camera.isFovEnabled() ? camera.getFovFactor() : 1.0f ;
		GLUES.gluPerspective(gl, PLConstants.kPerspectiveValue * zoomFactor, aspect, PLConstants.kPerspectiveZNear, PLConstants.kPerspectiveZFar);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClearDepthf(1.0f);
		
		gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
		
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		
		float portraitAngle = 90.0f;
		float landscapeAngle = 0.0f;
		
		switch (deviceOrientation) 
		{
			//The device is in portrait mode but upside down, with the device held upright and the home button at the top. (normal mirror)
			case UIDeviceOrientationPortraitUpsideDown:
				portraitAngle = -portraitAngle;
				gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
				break;
			//The device is in landscape mode, with the device held upright and the home button on the right side. (button right side)
			case UIDeviceOrientationLandscapeLeft:
				landscapeAngle = -90.0f;
				break;
			//The device is in landscape mode, with the device held upright and the home button on the left side. (button left side)
			case UIDeviceOrientationLandscapeRight:
				landscapeAngle = 90.0f;
				break;
		}
		
		gl.glRotatef(portraitAngle, 1.0f, 0.0f, 0.0f);
		if(landscapeAngle != 0.0f)
			gl.glRotatef(-landscapeAngle, 0.0f, 1.0f, 0.0f);
		
		if(camera != null)
			camera.render();
		
		for(PLSceneElement element : scene.getElements())
			element.render();
		
		if(currentOrientation != deviceOrientation)
			currentOrientation = deviceOrientation;
		
		gl.glFlush();
		
		//glBindRenderbufferOES(GL_RENDERBUFFER_OES, viewRenderbuffer);
	    context.presentRenderbuffer(GLES11Ext.GL_RENDERBUFFER_OES);
	}

	public void renderNTimesWithDeviceOrientation(UIDeviceOrientation deviceOrientation, Integer times)
	{
		for(int i = 0; i < times; i++)
			this.renderWithDeviceOrientation(deviceOrientation);
	}

	/**dealloc methods*/

	protected void finalize() throws Throwable
	{
	    if (EAGLContext.currentContext() == context)
	    	EAGLContext.setCurrentContext(null);
		this.destroyFramebuffer();
		viewFramebuffer = viewRenderbuffer = depthRenderbuffer = null;
	    context = null;
		super.finalize();
	}
	
	/**android methods*/
	
	protected CGSize size = CGSize.CGSizeMake(0, 0);
	
	public CGSize getSize()
	{
		return this.size;
	}
	
	public void surfaceCreated(SurfaceHolder holder)
	{
		try
		{			
			context = new EAGLContext(EAGLRenderingAPI.kEAGLRenderingAPIOpenGLES1);
			context.renderbufferStorage(GLES11Ext.GL_RENDERBUFFER_OES, this);
			
			if (context == null || !EAGLContext.setCurrentContext(context)) 
				throw new Exception("PLRenderer without EAGLContext.");
			
			this.initializeValues();

			view.onGLContextCreated((GL10)context.getContext().getGL());
		}
		catch(Exception ex)
		{
			Log.e("PLRender::onAttachedToWindow", ex.getMessage());
		}
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
	{
		if(size.width == 0 && scene.getCurrentCamera().getFovSensitivity() == PLConstants.kDefaultFovSensitivity)
			scene.getCurrentCamera().setFovSensitivity(((float)width/(float)height >= 1.0f ? width : height) * 10.0f);
		
		size = CGSize.CGSizeMake(width, height);
		view.drawViewInternallyNTimes(2);
	}

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		if(context != null)
		{
			context.destroyContext();
			context = null;
		}
	}
}
