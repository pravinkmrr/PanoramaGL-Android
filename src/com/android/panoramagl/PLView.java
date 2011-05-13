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

import com.android.panoramagl.enumeration.PLViewType;
import com.android.panoramagl.iphone.UITouch;
import com.android.panoramagl.iphone.enumeration.UIDeviceOrientation;

public class PLView extends PLViewBase
{
	private PLSceneElement sceneElement;
	private List<PLTexture> textures;
	private PLViewType type;
	
	/**property methods*/
	
	public PLViewType getType()
	{
		return type;
	}
	
	/**init methods*/

	@Override
	protected void initializeValues()
	{
		super.initializeValues();
		textures = new ArrayList<PLTexture>();
		type = PLViewType.PLViewTypeUnknown;
	}
	
	@Override
	public void reset()
	{
		super.reset();
		if(scene != null && scene.getCurrentCamera() != null)
			scene.getCurrentCamera().reset();
	}
	
	/**property methods*/

	public void setType(PLViewType value) throws Exception
	{
		type = value;
		sceneElement = null;
		
		switch (value)
		{
			case PLViewTypeCylindrical:
				sceneElement = PLCylinder.cylinder();
				break;
			case PLViewTypeSpherical:
				sceneElement = PLSphere.sphere();
				break;
			case PLViewTypeCubeFaces:
				sceneElement = PLCube.cube();
				break;
			case PLViewTypeUnknown:
				sceneElement = null;
				break;
			default:
				throw new Exception("Invalid panorama type. Type unknown");
		}
		
		if(sceneElement != null)
		{
			for(PLTexture texture : textures)
				sceneElement.addTexture(texture);
			scene.removeAllElements();
			scene.addElement(sceneElement);
		}
	}
	
	public PLCamera getCamera()
	{
		if(scene != null)
			return scene.getCurrentCamera();
		return null;
	}
	
	/**draw methods*/
	
	@Override
	protected void drawViewInternally()
	{
		super.drawViewInternally();
		if(!isValidForFov && !isValidForOrientation)
			scene.getCurrentCamera().rotateWith(startPoint, endPoint, scene.getCurrentCamera().getRotateSensitivity());
		renderer.render();
	}
	
	/**fov methods*/
	
	@Override
	protected boolean calculateFov(List<UITouch> touches)
	{
		if(super.calculateFov(touches))
		{
			scene.getCurrentCamera().addFovWithDistance(fovDistance);
			return true;
		}
		return false;
	}

	/**texture methods*/

	public void addTexture(PLTexture texture)
	{
		if(texture != null)
		{
			textures.add(texture);
			if(sceneElement != null)
				sceneElement.addTexture(texture);
		}
	}

	public void addTextureAndRelease(PLTexture texture)
	{
		if(texture != null)
		{
			textures.add(texture);
			if(sceneElement != null)
				sceneElement.addTextureAndRelease(texture);
		}
	}
					
	public void removeTexture(PLTexture texture)
	{
		if(texture != null)
		{
			textures.remove(texture);
			if(sceneElement != null)
				sceneElement.removeTexture(texture);
		}
	}
					
	public void removeTextureAtIndex(Integer index)
	{
		textures.remove(index);
		if(sceneElement != null)
			sceneElement.removeTextureAtIndex(index);
	}
					
	public void removeAllTextures()
	{
		textures.clear();
		if(sceneElement != null)
			sceneElement.removeAllTextures();
	}
	
	/**orientation methods*/
	
	@Override
	protected void orientationChanged(UIDeviceOrientation orientation) 
	{
		if(scene != null && scene.getCurrentCamera() != null)
			scene.getCurrentCamera().setOrientation(orientation);
	}

	/**dealloc methods*/

	protected void finalize() throws Throwable 
	{    
		textures = null;
		sceneElement = null;
		super.finalize();
	}
}
