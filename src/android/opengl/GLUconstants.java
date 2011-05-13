/*
 * This file is part of the PanoramaGL library for Android.
 *
 *  Authors: Javier Baez <javbaezga@gmail.com>
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

package android.opengl;

public class GLUconstants
{	
	/** Quad Constants */
	
	/* ErrorCode */
	public static final int GLU_INVALID_ENUM =                 	100900;
	public static final int GLU_INVALID_VALUE =                 100901;
	public static final int GLU_OUT_OF_MEMORY =                 100902;
	public static final int GLU_INCOMPATIBLE_GL_VERSION =       100903;
	public static final int GLU_INVALID_OPERATION =             100904;

	/* QuadricDrawStyle */
	public static final int GLU_POINT =                         100010;
	public static final int GLU_LINE =                          100011;
	public static final int GLU_FILL =                          100012;
	public static final int GLU_SILHOUETTE =                   	100013;

	/* QuadricCallback */
	public static final int GLU_ERROR =                        	100103;

	/* QuadricNormal */
	public static final int GLU_SMOOTH =                        100000;
	public static final int GLU_FLAT =                          100001;
	public static final int GLU_NONE =                          100002;

	/* QuadricOrientation */
	public static final int GLU_OUTSIDE =                       100020;
	public static final int GLU_INSIDE  =                       100021;
}
