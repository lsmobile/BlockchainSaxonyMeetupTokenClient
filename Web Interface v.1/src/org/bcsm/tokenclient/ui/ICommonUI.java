package org.bcsm.tokenclient.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

import javax.servlet.ServletContext;

import org.bcsm.tokenclient.business.ICommon;

import com.vaadin.data.Property;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public interface ICommonUI extends ICommon {
	
	final CommonUIState uistate = new CommonUIState ();
	
	default void init() {
		
		try {
			init(uistate.txtLog);

			// Set Receiver for wallet file upload component
			uistate.uploadWalletfile.setReceiver((filename, mimeType) -> {
			        // Create upload stream to write to
			        FileOutputStream fos = null;
			        try {
			            // Get path to servlet's temp directory
			            final File temporaryDirectory = (File) VaadinServlet.getCurrent().getServletContext().getAttribute(ServletContext.TEMPDIR);
 
			            // Concatenate temporaryDirectory with filename and open the file for writing.
			            final File walletfile = new File(temporaryDirectory, filename);
 
			            // Create the output stream
			            fos = new FileOutputStream(walletfile);
 
			            conn.setWalletfile(walletfile);
						updateUIState ();
			        } catch (final java.io.FileNotFoundException e) {
			            Notification.show("Could not open file", Type.ERROR_MESSAGE);
			            log.error("Could not open file", e);
			            return null;
			        }
			        return fos;
			    }
			);
			
			uistate.txtPassword.addValueChangeListener (event -> updateUIState ());
//        this.passwordField.addTextChangeListener(new TextChangeListener() {TODO
//
//			@Override
//			public void textChange(TextChangeEvent event) {
//                checkCanConnect ();
//			}
//		});
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnConnect_buttonClick_(final Button.ClickEvent event) {
		try {
			if (!conn.isConnected()) {
				conn.connect(uistate.txtNode.getValue());
			} else {
				// if (this.eventLogPoleSetUpSubscription != null ||
				// this.eventLogRentedSetUpSubscription != null
				// || this.eventLogReturnedSetUpSubscription != null) {
				// btnEventsSubscribe_buttonClick(event);
				// }
				conn.disconnect();
			}
			updateUIState();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnLogin_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.login(uistate.txtPassword.getValue());
			updateUIState();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnSubscribeEvents_buttonClick_(final Button.ClickEvent event) {
		try {
			if (!conn.isEventSubscribed()) {
				conn.subscribeEvents(uistate.txtSmartContract.getValue());
			}
			else {
				conn.unsubscribeEvents();
			}
			updateUIState();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}

	
	default void btnLoadContract_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.loadContract (uistate.txtSmartContract.getValue());
			updateUIState();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnGetTokens_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.getTokens();
			updateUIState ();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnTransferTokens_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.transferTokens(uistate.txtFromAddress.getValue(), uistate.txtToAddress.getValue(),
					(BigInteger) uistate.txtValue.getConvertedValue());
			updateUIState ();
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void cbFromOwnAddress_valueChange_(final Property.ValueChangeEvent event) {
		if (uistate.cbFromOwnAddress.getValue()) {
			uistate.txtFromAddress.setValue(null);
		}
		updateUIState ();
	}

	
	default void btnSetSecret_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.setSecret (uistate.txtSecret.getValue().getBytes());
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void btnAddMember_buttonClick_(final Button.ClickEvent event) {
		try {
			conn.addMember (uistate.txtNewMember.getValue());
		} catch (final Exception e) {
			log.error(e.getMessage());
		}
	}
	
	default void cbLogTransactionReceipt_valueChange_(final Property.ValueChangeEvent event) {
		conn.logTransactionReceipt = uistate.cbLogTransactionReceipt.getValue();
	}
	
	default void updateUIState () {
		final boolean isConnected = conn.isConnected();
		final boolean isLogged = conn.isLogged();
		final boolean isEventSubscribed = conn.isEventSubscribed();
		final boolean isContractLoaded = conn.isContractLoaded();
		final boolean isOwner = conn.isOwner();
		uistate.txtNode.setEnabled(!isConnected);
		uistate.btnConnect.setCaption(isConnected ? CAPTION_DISCONNECT : CAPTION_CONNECT);
		uistate.uploadWalletfile.setEnabled(isConnected && !isLogged);
		uistate.txtPassword.setEnabled(isConnected && !isLogged);
		uistate.btnLogin.setEnabled(isConnected && !isLogged && conn.canLoggin());
//		uistate.txtSmartContract.setEnabled(isConnected);TODO ?
		uistate.btnSubscribeEvents.setEnabled(isConnected);
		uistate.btnSubscribeEvents.setCaption(!isEventSubscribed ? CAPTION_SUBSCRIBE_EVENTS : CAPTION_UNSUBSCRIBE_EVENTS);
		uistate.btnLoadContract.setEnabled(isLogged && !isContractLoaded);
		uistate.lblBalance.setValue(String.valueOf(conn.getBalance()));
		uistate.btnGetTokens.setEnabled(isContractLoaded);
		uistate.txtFromAddress.setEnabled(isContractLoaded && !uistate.cbFromOwnAddress.getValue());
		uistate.txtToAddress.setEnabled(isContractLoaded);
		uistate.txtValue.setEnabled(isContractLoaded);
		uistate.btnTransferTokens.setEnabled(isContractLoaded);
		uistate.cbFromOwnAddress.setEnabled(isContractLoaded);
		uistate.txtSecret.setEnabled(isOwner);
		uistate.btnSetSecret.setEnabled(isOwner);
		uistate.txtNewMember.setEnabled(isOwner);
		uistate.btnAddMember.setEnabled(isOwner);
	}
}
