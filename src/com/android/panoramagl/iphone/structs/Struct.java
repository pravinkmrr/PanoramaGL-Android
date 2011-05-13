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

package com.android.panoramagl.iphone.structs;

import java.lang.reflect.Field;

@SuppressWarnings("unchecked")
public abstract class Struct<T extends Struct>
{	
	/**clone methods*/
	
	public T clone()
	{
		Object clone = null;
		try
		{
			clone = this.getClass().newInstance();
		} 
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} 
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		for (Class obj = this.getClass(); !obj.equals(Object.class); obj = obj.getSuperclass())
		{
			for(Field field : obj.getDeclaredFields())
			{
				field.setAccessible(true);
				try
				{
					field.set(clone, field.get(this));
				}
				catch (IllegalArgumentException e)
				{
				}
				catch (IllegalAccessException e)
				{
				}
			}
		}
		return (T)clone;
	}
}
