/*******************************************************************************
 * Copyright (c) 2014, 2016 Orange.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.onem2m.sdt.home.mocked.module;

import org.onem2m.home.modules.Streaming;
import org.onem2m.sdt.Domain;
import org.onem2m.sdt.datapoints.StringDataPoint;
import org.onem2m.sdt.impl.DataPointException;

public class MockedStreaming extends Streaming {

	public MockedStreaming(String name, Domain domain) {
		super(name, domain,
			new StringDataPoint("url") {
				@Override
				public String doGetValue() throws DataPointException {
					return "my url";
				}
			}, 
			new StringDataPoint("login") {
				@Override
				public String doGetValue() throws DataPointException {
					return "my login";
				}
			}, 
			new StringDataPoint("password") {
				@Override
				public String doGetValue() throws DataPointException {
					return "my password";
				}
			}, 
			new StringDataPoint("format") {
				@Override
				public String doGetValue() throws DataPointException {
					return "HLS";
				}
			}
		);
	}

}
