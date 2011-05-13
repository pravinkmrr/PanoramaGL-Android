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

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

public class PLUtils extends Object 
{
	/**swap methods*/
	
	public static float[] swapFloatValues(float firstValue, float secondValue)
	{
		float swapValue = firstValue;
		firstValue = secondValue;
		secondValue = swapValue;
		return new float[]{firstValue, secondValue};
	}
	
	public static int[] swapIntValues(int firstValue,int secondValue)
	{
		firstValue = firstValue ^ secondValue;
		secondValue = secondValue ^ firstValue;
		firstValue = firstValue ^ secondValue;
		return new int[]{firstValue, secondValue};
	}
	
	/**android methods*/
	
	//Only for Android
	public static IntBuffer makeFloatBuffer(int[] array)
	{
		final int integerSize = Integer.SIZE / 8;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * integerSize);
		byteBuffer.order(ByteOrder.nativeOrder());
		IntBuffer intBuffer = byteBuffer.asIntBuffer();
		intBuffer.put(array);
		intBuffer.position(0);
		return intBuffer;
	}
	
	public static FloatBuffer makeFloatBuffer(int length)
	{
		final int floatSize = Float.SIZE / 8;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(length * floatSize);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.position(0);
		return floatBuffer;
	}
	
	public static FloatBuffer makeFloatBuffer(float[] array)
	{
		final int floatSize = Float.SIZE / 8;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * floatSize);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(array);
		floatBuffer.position(0);
		return floatBuffer;
	}
	
	public static FloatBuffer makeFloatBuffer(float[][] array2d, int rows, int cols)
	{
		float[] result = new float[rows * cols];
		 //m[3+4*2] = -1.0f;
		    //m[2][3] = -1.0f;
		int k = 0;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				result[k++] = array2d[i][j];
		/*int i = 0;
		for (float[] array : array2d)
		{
            for (float value : array)
            {
            	result[i++] = value;
            }
		}*/
		return makeFloatBuffer(result);
	}
	
	protected static HashMap<String, Method> methods = new HashMap<String, Method>();
	
	@SuppressWarnings("unchecked")
	public static Method getMethod(Object target, String name, Class... parameterTypes)
	{
		String key = target.getClass().getSimpleName() + ":" + name + ":" + (parameterTypes == null ? 0 : parameterTypes.length); 
		Method method = methods.get(key);
		if(method == null)
		{
			for (Class obj = target.getClass(); !obj.equals(Object.class); obj = obj.getSuperclass())
			{
				try
				{
					method = obj.getDeclaredMethod(name, parameterTypes);
					method.setAccessible(true);
					methods.put(key, method);
					break;
				} 
				catch (SecurityException e)
				{
					e.printStackTrace();
				} 
				catch (NoSuchMethodException e)
				{
					e.printStackTrace();
				}
			}
		}
		return method;
	}
}
