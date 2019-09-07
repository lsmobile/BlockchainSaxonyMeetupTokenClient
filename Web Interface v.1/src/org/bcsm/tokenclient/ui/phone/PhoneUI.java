
package org.bcsm.tokenclient.ui.phone;

import org.bcsm.tokenclient.ui.desktop.MainView;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.xdev.ui.XdevUI;
import com.xdev.ui.navigation.XdevNavigator;

@Push(value = PushMode.MANUAL, transport = Transport.LONG_POLLING)
@Theme("BCSMTokenClient")
public class PhoneUI extends XdevUI {
	public PhoneUI() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final VaadinRequest request) {
		this.initUI();
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated
	 * by the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.navigator = new XdevNavigator(this, this);

		this.navigator.addView("", MainView.class);

		this.setSizeFull();
	} // </generated-code>

	// <generated-code name="variables">
	private XdevNavigator navigator; // </generated-code>
}