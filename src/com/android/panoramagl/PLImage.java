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

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.android.panoramagl.iphone.structs.CGRect;
import com.android.panoramagl.iphone.structs.CGSize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class PLImage 
{	
	private Bitmap cgImage;
	private int width, height;
	
	/** init methods */
	
	public PLImage()
	{
		super();
		this.createWithSize(CGSize.CGSizeMake(0,0));
	}
	
	public PLImage(Bitmap image)
	{
		super();
		this.createWithCGImage(image);
	}
	
	public PLImage(CGSize size)
	{
		super();
		this.createWithSize(size);
	}
	
	public PLImage(int w, int h)
	{
		super();
		new PLImage(CGSize.CGSizeMake(w, h));	
	}
	
	public PLImage(String path)
	{
		super();
		this.createWithPath(path);
	}
	
	public static PLImage imageWithCGImage(Bitmap image)
	{
		return new PLImage(image);
	}
	
	public static PLImage imageWithSize(CGSize size)
	{
		return new PLImage(size);
	}	

	public static PLImage imageWithDimensions(int width ,int height)
	{
		return new PLImage(width, height);
	}
	
	public static PLImage imageWithPath(String path)
	{
		return new PLImage(path);
	}
	
	public static PLImage imageWithSizeZero()
	{
		return new PLImage();
	}
	
	public void createWithPath(String path)
	{
		cgImage = BitmapFactory.decodeFile(path);
		width = cgImage.getWidth();
		height = cgImage.getHeight();
	}
	
	public void createWithCGImage(Bitmap image)
	{
		width = image.getWidth();
		height = image.getHeight();
		cgImage = Bitmap.createBitmap(image, 0, 0, width, height);
	}
	
	public void createWithSize(CGSize size)
	{
		this.deleteImage();
		this.createWithCGImage(Bitmap.createBitmap(null, 0, 0, size.width, size.height));
	}
	
	/**property methods*/
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public CGSize getSize()
	{
		return CGSize.CGSizeMake(width, height);
	}
	
	public CGRect getRect()
	{
		return CGRect.CGRectMake(0, 0, width, height);
	}
	
	public int getCount()
	{
		return this.getWidth() * this.getHeight() * 4;
	}
	
	public Bitmap getCGImage() 
	{
		return cgImage;
	}
	
	//Only for Android
	public Bitmap getBitmap() 
	{
		return cgImage;
	}
	
	public ByteBuffer getBits() 
	{
		if(this.isValid())
			return null;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		cgImage.compress(Bitmap.CompressFormat.PNG, 100, buffer);
		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer.toByteArray());
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		return byteBuffer;
	}
	
	/**operation methods*/

	public boolean isValid()
	{
		return (this.getCGImage() != null);
	}

	public boolean equals(PLImage image)
	{	
		if (image.getCGImage() == this.getCGImage())
			return true;
		if (image.getCGImage() == null || this.getCGImage() == null || image.getHeight() != this.getHeight() || image.getWidth() != this.getWidth())
			return false;
		ByteBuffer bits = image.getBits();
		ByteBuffer _bits = this.getBits();
		for(int i = 0; i < this.getCount() ; i++)
		{
			if(bits.get() != _bits.get())
				return false;
		}
		return true;
	}
	
	public PLImage assign(PLImage image)
	{
		this.deleteImage();
		this.createWithCGImage(image.getCGImage());
		return this;
	}
	
	/**clone methods*/

	public Bitmap cloneCGImage()
	{
		return Bitmap.createBitmap(this.getCGImage(), 0, 0, this.getWidth(), this.getHeight());
	}
	
	//Only Android
	public Bitmap cloneBitmap()
	{
		return this.cloneCGImage();
	}
	
	public PLImage clone()
	{
		return PLImage.imageWithCGImage(this.getCGImage());
	}
	
	/** crop methods*/
	
	public PLImage crop(CGRect rect)
	{
		Bitmap image = Bitmap.createBitmap(this.getCGImage(), rect.x, rect.y, rect.width, rect.height);
		this.deleteImage();
		this.createWithCGImage(image);
		return this;
	}
	
	/**scale methods*/
	
	public PLImage scale(CGSize size)
	{
		if(this.getCGImage() == null)
		{
			Log.d("PLImage::scaled", "CGImage is nil");
			return this;
		}
		if ((size.width == 0 && size.height == 0) || (size.width == this.width && size.height == this.height))
			return this;
		Bitmap image = Bitmap.createScaledBitmap(this.getCGImage(), size.width, size.height, true);
	    this.deleteImage();
	    this.createWithCGImage(image);
		return this;
	}
	
	/**rotate methods*/
	
	public PLImage rotate(int angle)
	{
		if((angle % 90)!=0)
			return this;
		Matrix mat = new Matrix();
    	mat.preRotate(angle);
    	Bitmap image = Bitmap.createBitmap(this.getCGImage(), 0, 0, this.getCGImage().getWidth(), this.getCGImage().getHeight(), mat, true);
    	this.deleteImage();
    	this.createWithCGImage(image);
    	return this;
	}
	
	/**mirror methods*/
	
	public PLImage mirrorHorizontally()
	{
		return this.mirror(true, false);
	}
	
	public PLImage mirrorVertically()
	{
		return this.mirror(false,true);
	}
	
	public PLImage mirror(boolean horizontally, boolean vertically){
		Bitmap srcImage = this.getCGImage();
		//-1,1 Horizontal, 1, -1 Vertical, Both = -1,-1
		Matrix mat = new Matrix();
		mat.preScale(horizontally ? -1.0f : 1.0f, vertically ? -1.0f : 1.0f);
		Bitmap image = Bitmap.createBitmap(srcImage , 0, 0,srcImage .getWidth(),srcImage .getHeight(), mat, false);
		this.deleteImage();
		this.createWithCGImage(image);
		return this;
	}
	
	/**save methods */
	
	public boolean save(String path)
	{
		return this.save(path, 80);
	}
	
	public boolean save(String path,int quality)
	{
		if(this.isValid())
			return false;
		boolean state = true;
		quality = (quality <= 0 ? 80 : Math.min(quality, 100));
		try
		{
			FileOutputStream out = new FileOutputStream(path);
			this.getCGImage().compress(Bitmap.CompressFormat.JPEG, quality, out);
			out.flush();
			out.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			state = false;
		}
		return state;
	}
	
	/**dealloc methods*/

	@Override
	protected void finalize() throws Throwable
	{
		this.deleteImage();
		super.finalize();
	}

	protected void deleteImage()
	{
		cgImage = null;
	}
}