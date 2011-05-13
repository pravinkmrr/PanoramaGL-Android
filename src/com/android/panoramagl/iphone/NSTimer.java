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

import java.lang.reflect.Method;
import java.util.Date;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public class NSTimer extends Handler
{
	protected boolean running;
	
	protected int interval;
	protected Object target;
	protected Method method;
	protected Object[] userInfo;
	protected boolean repeats;
	
	protected static final int NEXT = 0;

	public NSTimer(Date date, float interval, Object target, Method method, Object[] userInfo, boolean repeats)
	{
		this.running = true;
		this.interval = (int)(interval * 1000);
		this.target = target;
		this.method = method;
		this.userInfo = userInfo;
		this.repeats = repeats;
		
		sendMessageDelayed(obtainMessage(NEXT), this.interval);
	}
	
	public static NSTimer scheduledTimerWithTimeInterval(float interval, Object target, Method method, Object[] userInfo, boolean repeats)
	{
		return new NSTimer(new Date(SystemClock.uptimeMillis()), interval, target, method, userInfo, repeats);
	}
	
	@Override
	public void handleMessage(Message message)
	{
		if (running && message.what == NEXT && method != null)
		{
			try
			{		
				method.invoke(target, userInfo);
			} 
			catch(Exception e)
			{
				return;
			}			
			if(repeats)
				sendMessageDelayed(obtainMessage(NEXT), interval);
		}
	}
	
	public void invalidate()
	{
		running = false;
	}
	
	public boolean isValid()
	{
		return running;
	}
	
	protected void finalize() throws Throwable
	{
		running = false;
		target = null;
		method = null;
		userInfo = null;
		super.finalize();
	}
}
