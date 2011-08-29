/*
 * Copyright (c) 2011, DataLite. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package cz.datalite.zk.annotation.processor;

import cz.datalite.zk.annotation.invoke.Invoke;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zk.ui.Component;

/**
 * <p>Parent processor class implementing basic interface and provides decomposition
 * of set of invokes to single invokes. After processing single invokes then 
 * the invokes are again group up.</p>
 *
 * @author Karel Čemus <cemus@datalite.cz>
 */
public abstract class AbstractProcessor<T> implements Processor<T> {

    public List<Invoke> process( T annotation, List<Invoke> inner, Method method, Component master, Object controller ) {
        List<Invoke> invokes = new ArrayList<Invoke>();
        for ( Invoke invoke : inner ) {
            invokes.add( process( annotation, invoke, method, master, controller ) );
        }
        return invokes;
    }

    public abstract Invoke process( T annotation, Invoke invoke, Method method, Component master, Object controller );
}