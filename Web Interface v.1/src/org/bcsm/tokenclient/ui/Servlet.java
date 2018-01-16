
package org.bcsm.tokenclient.ui;

import javax.servlet.annotation.WebServlet;

import org.bcsm.tokenclient.ui.desktop.DesktopUI;

import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;
import com.xdev.communication.XdevServlet;

@WebServlet(value = "/*", asyncSupported = true)
public class Servlet extends XdevServlet {
	public Servlet() {
		super();
	}

	@Override
	protected void initSession(final SessionInitEvent event) {
		super.initSession(event);

		event.getSession().addUIProvider(new ServletUIProvider());
	}

	/**
	 * UIProvider which provides different UIs depending on the caller's device.
	 */
	private static class ServletUIProvider extends UIProvider {
		@Override
		public Class<? extends UI> getUIClass(final UIClassSelectionEvent event) {
//			final ClientInfo client = ClientInfo.getCurrent();
//			if (client != null) {
//				if (client.isMobile()) {
//					return PhoneUI.class;
//				}
//				if (client.isTablet()) {
////					return TabletUI.class;
//				}
//			}
			return DesktopUI.class;
		}
	}
}