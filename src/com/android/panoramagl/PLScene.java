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

public class PLScene extends Object
{	
	private List<PLCamera> cameras;
	private PLCamera currentCamera;
	private Integer cameraIndex;
	
	private List<PLSceneElement> elements;
	
	/**property methods*/
	
	public List<PLCamera> getCameras()
	{
		return cameras;
	}

	public PLCamera getCurrentCamera()
	{
		return currentCamera;
	}

	public Integer getCameraIndex()
	{
		return cameraIndex;
	}

	public List<PLSceneElement> getElements()
	{
		return elements;
	}

	/**init methods*/
	
	public PLScene()
	{
		super();
		this.initializeValues();
		this.addCamera(PLCamera.camera());
	}

	public PLScene(PLCamera camera)
	{
		super();
		this.initializeValues();
		this.addCamera(camera);
	}
	
	public PLScene(PLSceneElement element)
	{
		this(element, new PLCamera());
	}
	
	public PLScene(PLSceneElement element, PLCamera camera)
	{
		super();
		this.initializeValues();
		this.addElement(element);
		this.addCamera(camera);
	}
	
	public static PLScene scene()
	{
		return new PLScene();
	}
	
	public static PLScene sceneWithCamera(PLCamera camera)
	{
		return new PLScene(camera);
	}
	
	public static PLScene sceneWithElement(PLSceneElement element)
	{
		return new PLScene(element);
	}
	
	public static PLScene sceneWithElement(PLSceneElement element, PLCamera camera)
	{
		return new PLScene(element, camera);
	}
	
	protected void initializeValues()
	{
		elements = new ArrayList<PLSceneElement>();
		cameras = new ArrayList<PLCamera>();
	}
	
	/**camera methods*/

	public void setCameraIndex(Integer index)
	{
		if(index < cameras.size())
		{
			cameraIndex = index;
			currentCamera = cameras.get(index);
		}
	}
	
	public void addCamera(PLCamera camera)
	{
		if(cameras.size() == 0)
		{
			cameraIndex = 0;
			currentCamera = camera;
		}
		cameras.add(camera);
	}

	public void removeCameraAtIndex(Integer index)
	{
		cameras.remove(index);
		if(cameras.size() == 0)
		{
			currentCamera = null;
			cameraIndex = -1;
		}
	}
	
	/**element methods*/
	
	public void addElement(PLSceneElement element)
	{
		elements.add(element);
	}

	public void removeElementAtIndex(Integer index)
	{
		elements.remove(index);
	}
	
	public void removeAllElements()
	{
		elements.clear();
	}
	
	/**dealloc methods*/

	@Override
	protected void finalize() throws Throwable
	{
		elements = null;
		cameras = null;
		currentCamera = null;
		super.finalize();
	}
}
