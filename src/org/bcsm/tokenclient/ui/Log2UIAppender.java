package org.bcsm.tokenclient.ui;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.xdev.ui.XdevTextArea;

public class Log2UIAppender extends AppenderSkeleton {
	
	protected XdevTextArea txtLog = null;
	
//	public Log2UIAppender () {
//		super();
//		setName(getClass().getCanonicalName());
//	}

	public XdevTextArea getTxtLog() {
		return this.txtLog;
	}

	public void setTxtLog(final XdevTextArea txtLog) {
		this.txtLog = txtLog;
	}

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(final LoggingEvent event) {
		if (this.txtLog != null) {
			this.txtLog.setValue(this.txtLog.getValue() + this.layout.format(event));
		}
	}
}
