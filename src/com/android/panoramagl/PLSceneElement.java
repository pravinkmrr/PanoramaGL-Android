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

public abstract class PLSceneElement extends PLSceneElementBase 
{	
	protected List<PLTexture> textures;
	
	/**init methods*/
	
	public PLSceneElement()
	{
		super();
	}
	
	public PLSceneElement(PLTexture texture)
	{
		super();
		this.addTexture(texture);
	}
	
	protected void initializeValues()
	{
		super.initializeValues();
		textures = new ArrayList<PLTexture>();
	}
	
	/**texture methods*/

	public  List<PLTexture> getTextures()
	{
		return textures;
	}
	
	public void addTexture(PLTexture texture)
	{
		if(texture != null && texture.isValid())
		{
			textures.add(texture);
			this.evaluateIfElementIsValid();
		}
	}
	
	public void addTextureAndRelease(PLTexture texture)
	{
		this.addTexture(texture);
		if(texture != null)
			texture = null;
	}
	
	public void removeTexture(PLTexture texture)
	{
		if(texture != null && texture.isValid())
		{
			textures.remove(texture);
			this.evaluateIfElementIsValid();
		}
	}
	
	public void removeTextureAtIndex(int index)
	{
		textures.remove(index);
		this.evaluateIfElementIsValid();
	}
	
	public void removeAllTextures()
	{
		textures.removeAll(textures);
		this.evaluateIfElementIsValid();
	}
	
	/**utility methods*/
	
	protected void evaluateIfElementIsValid()
	{
		isValid = !textures.isEmpty();
	}
	
	/**dealloc methods*/

	@Override
	protected void finalize() throws Throwable
	{
		if(textures != null)
		{
			textures.clear();
			textures = null;
		}
		super.finalize();
	}
}
