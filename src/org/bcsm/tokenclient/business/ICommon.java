package org.bcsm.tokenclient.business;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.bcsm.tokenclient.ui.ICommonUI;
import org.bcsm.tokenclient.ui.Log2UIAppender;

import com.xdev.ui.XdevTextArea;

public interface ICommon {
	final Logger log = LogManager.getLogger(ICommonUI.class);
	
	final String CAPTION_CONNECT = "Connect";
	final String CAPTION_DISCONNECT = "Disconnect";

	final String TXT_NOT_CONNECTED = "Not connected";
	final String TXT_NOT_CONNECTING = "Connecting...";

	final String CAPTION_SUBSCRIBE_EVENTS = "Subscribe events";
	final String CAPTION_UNSUBSCRIBE_EVENTS = "Unsubscribe events";
	
	final EthereumConnection conn = new EthereumConnection ();

	default void init(final XdevTextArea txtLog) {
		
		try {
			if (txtLog != null) {
				Log2UIAppender logAppender = (Log2UIAppender) log.getAppender(Log2UIAppender.class.getCanonicalName());
				if (logAppender == null) {
					//TODO
					logAppender = new Log2UIAppender ();
					logAppender.setLayout(new PatternLayout("%m%n"));
					log.addAppender(logAppender);
				}
				logAppender.setTxtLog(txtLog);
			}
			
			log.info(TXT_NOT_CONNECTED);
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}


}
