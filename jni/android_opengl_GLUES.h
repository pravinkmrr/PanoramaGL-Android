/*
 * This file is part of the GLUES library for Android.
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
 *
 * glues: OpenGL ES 1.0 CM port of part of GLU by Mike Gorchak <mike@malva.ua>
 */

#include <jni.h>
/* Header for class android_opengl_GLUES */

#ifndef _Included_android_opengl_GLUES
#define _Included_android_opengl_GLUES
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     android_opengl_GLUES
 * Method:    gluCylinderAndroid
 * Signature: (Ljavax/microedition/khronos/opengles/GL10;Landroid/opengl/GLUquadric;FFFIIIIIIZ)V
 */
JNIEXPORT void JNICALL Java_android_opengl_GLUES_gluCylinderAndroid
  (JNIEnv *, jclass, jobject, jobject, jfloat, jfloat, jfloat, jint, jint, jint, jint, jint, jint, jboolean);

/*
 * Class:     android_opengl_GLUES
 * Method:    gluDiskAndroid
 * Signature: (Ljavax/microedition/khronos/opengles/GL10;Landroid/opengl/GLUquadric;FFIIIIIIZ)V
 */
JNIEXPORT void JNICALL Java_android_opengl_GLUES_gluDiskAndroid
  (JNIEnv *, jclass, jobject, jobject, jfloat, jfloat, jint, jint, jint, jint, jint, jint, jboolean);

/*
 * Class:     android_opengl_GLUES
 * Method:    gluPartialDiskAndroid
 * Signature: (Ljavax/microedition/khronos/opengles/GL10;Landroid/opengl/GLUquadric;FFIIFFIIIIZ)V
 */
JNIEXPORT void JNICALL Java_android_opengl_GLUES_gluPartialDiskAndroid
  (JNIEnv *, jclass, jobject, jobject, jfloat, jfloat, jint, jint, jfloat, jfloat, jint, jint, jint, jint, jboolean);

/*
 * Class:     android_opengl_GLUES
 * Method:    gluSphereAndroid
 * Signature: (Ljavax/microedition/khronos/opengles/GL10;Landroid/opengl/GLUquadric;FIIIIIIZ)V
 */
JNIEXPORT void JNICALL Java_android_opengl_GLUES_gluSphereAndroid
  (JNIEnv *, jclass, jobject, jobject, jfloat, jint, jint, jint, jint, jint, jint, jboolean);

/*
 * Class:     android_opengl_GLUES
 * Method:    gluPerspective
 * Signature: (Ljavax/microedition/khronos/opengles/GL10;FFFF)V
 */
JNIEXPORT void JNICALL Java_android_opengl_GLUES_gluPerspective
  (JNIEnv *, jclass, jobject, jfloat, jfloat, jfloat, jfloat);

#ifdef __cplusplus
}
#endif
#endif
