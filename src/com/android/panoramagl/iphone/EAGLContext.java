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

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

import android.util.Log;
import android.view.SurfaceView;

import com.android.panoramagl.iphone.enumeration.EAGLRenderingAPI;

public class EAGLContext
{	
	private EGLContext context;
	private EGLConfig[] configs;
	private EGLDisplay display;
	private EGLSurface surface;
	
	public EGLConfig[] getConfigs()
	{
		return configs;
	}
	
	public EGLDisplay getDisplay()
	{
		return display;
	}
	
	public EGLContext getContext()
	{
		return context;
	}
	
	void setSurface(EGLSurface surface)
	{
		this.surface = surface;
	}
	
	public EGLSurface getSurface()
	{
		return this.surface;
	}
		
	public EAGLContext(EAGLRenderingAPI api) throws Exception
	{
		 this.api = api;
		 
		 if(api == EAGLRenderingAPI.kEAGLRenderingAPIOpenGLES1)
		 {
			 EGL10 egl = (EGL10)EGLContext.getEGL();
			 display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

			 int[] version = new int[2];
			 egl.eglInitialize(display, version);
			 
			 int[] configSpec = 
			 {
					EGL10.EGL_RED_SIZE,      5,
					EGL10.EGL_GREEN_SIZE,    6,
					EGL10.EGL_BLUE_SIZE,     5,
					EGL10.EGL_DEPTH_SIZE,   16,
					EGL10.EGL_NONE
			 };
			
			 configs = new EGLConfig[1];
			 
			 int[] num_config = new int[1];
			 egl.eglChooseConfig(display, configSpec, configs, 1, num_config);
			 EGLConfig config = configs[0];
			
			 context = egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT, null);	
		 }
		 else
			 throw new Exception("OpenGL ES 2.0 do not supported.");
	 }
	 
	 private EAGLRenderingAPI api;
	 
	 public EAGLRenderingAPI getAPI()
	 {
		 return api;
	 }
	 
	 public void destroyContext()
	 {
		 if(surface != null && context != null)
		 {
			 EGL10 egl = (EGL10)EGLContext.getEGL();
			 egl.eglDestroySurface(display, surface);
			 egl.eglDestroyContext(display, context);
			 egl.eglTerminate(display);
		 }
	     	     
	     context = null;
	     configs = null;
	     display = null;
	     surface = null;
	 }
	 
	 protected void finalize() throws Throwable
	 {
		 this.destroyContext();
		 super.finalize();
	 }
	 
	 protected static EAGLContext currentContext;
	 
	 public static GL10 currentGL()
	 {
		 return (GL10)currentContext.getContext().getGL();
	 }
	 
	 public static boolean setCurrentContext(EAGLContext context)
	 {
		 try
		 {
			EGL10 egl = (EGL10)EGLContext.getEGL();
			if(context == null)
				egl.eglMakeCurrent(currentContext == null ? egl.eglGetCurrentDisplay() : currentContext.getDisplay(), EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
			else if(currentContext != context)
			{
				egl.eglMakeCurrent(context.getDisplay(), context.getSurface(), context.getSurface(), context.getContext());
				currentContext = context;
			}
		 	return true;
		 }
		 catch(Exception ex)
		 {
			 Log.e("setCurrentContext", "Error can't setCurrentContext:" + ex.getMessage());
		 }
		 return false;
	 }
	 
	 public static EAGLContext currentContext()
	 {
		 return currentContext;
	 }
	 
	 /* Attaches an EAGLDrawable as storage for the OpenGL ES renderbuffer object bound to <target> */
	 public boolean renderbufferStorage(Integer target, SurfaceView drawable)
	 {
		 EGL10 egl = (EGL10)EGLContext.getEGL();
		 surface = egl.eglCreateWindowSurface(display, configs[0], drawable.getHolder(), null);
		 return surface != null;
	 }

	 /* Request the native window system display the OpenGL ES renderbuffer bound to <target> */
	 public boolean presentRenderbuffer(Integer target)
	 {
		 EGL10 egl = (EGL10)EGLContext.getEGL();
		 return egl.eglSwapBuffers(display, surface);
	 }
}
