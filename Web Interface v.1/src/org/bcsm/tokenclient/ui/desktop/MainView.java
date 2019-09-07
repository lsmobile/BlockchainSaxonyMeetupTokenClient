
package org.bcsm.tokenclient.ui.desktop;

import org.bcsm.tokenclient.ui.ICommonUI;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevCheckBox;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevPasswordField;
import com.xdev.ui.XdevTabSheet;
import com.xdev.ui.XdevTextArea;
import com.xdev.ui.XdevTextField;
import com.xdev.ui.XdevUpload;
import com.xdev.ui.XdevView;
import com.xdev.util.ConverterBuilder;

public class MainView extends XdevView implements ICommonUI {

	/**
	 * 
	 */
	public MainView() {
		super();
		this.initUI();
		
		uistate.txtNode = this.txtNode;
		uistate.btnConnect = this.btnConnect;
		uistate.uploadWalletfile = this.uploadWalletfile;
		uistate.txtPassword = this.txtPassword;
		uistate.btnLogin = this.btnLogin;
		uistate.txtSmartContract = this.txtSmartContract;
		uistate.btnSubscribeEvents = this.btnSubscribeEvents;
		uistate.btnLoadContract = this.btnLoadContract;
		uistate.btnGetTokens = this.btnGetTokens;
		uistate.cbLogTransactionReceipt = this.cbLogTransactionReceipt;
		uistate.txtLog = this.txtLog;
		
		uistate.lblBalance = this.lblBalance;
		uistate.txtFromAddress = this.txtFromAddress;
		uistate.txtToAddress = this.txtToAddress;
		uistate.txtValue = this.txtValue;
		uistate.btnTransferTokens = this.btnTransferTokens;
		uistate.cbFromOwnAddress = this.cbFromOwnAddress;
		
		uistate.txtSecret = this.txtSecret;
		uistate.btnSetSecret = this.btnSetSecret;
		uistate.txtNewMember = this.txtNewMember;
		uistate.btnAddMember = this.btnAddMember;
		
		init ();
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #btnConnect}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnConnect_buttonClick(final Button.ClickEvent event) {
		btnConnect_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #btnLogin}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnLogin_buttonClick(final Button.ClickEvent event) {
		btnLogin_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnLoadContract}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnLoadContract_buttonClick(final Button.ClickEvent event) {
		btnLoadContract_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnGetTokens}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnGetTokens_buttonClick(final Button.ClickEvent event) {
		btnGetTokens_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnSetSecret}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnSetSecret_buttonClick(final Button.ClickEvent event) {
		btnSetSecret_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnAddMember}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnAddMember_buttonClick(final Button.ClickEvent event) {
		btnAddMember_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevCheckBox}
	 * {@link #cbLogTransactionReceipt}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cbLogTransactionReceipt_valueChange(final Property.ValueChangeEvent event) {
		cbLogTransactionReceipt_valueChange_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnTransferTokens}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnTransferTokens_buttonClick(final Button.ClickEvent event) {
		btnTransferTokens_buttonClick_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevCheckBox}
	 * {@link #cbFromOwnAddress}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cbFromOwnAddress_valueChange(final Property.ValueChangeEvent event) {
		cbFromOwnAddress_valueChange_(event);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnSubscribeEvents}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnSubscribeEvents_buttonClick(final Button.ClickEvent event) {
		btnSubscribeEvents_buttonClick_(event);
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.tabSheet = new XdevTabSheet();
		this.tabConnect = new XdevGridLayout();
		this.txtNode = new XdevTextField();
		this.btnConnect = new XdevButton();
		this.uploadWalletfile = new XdevUpload();
		this.txtPassword = new XdevPasswordField();
		this.btnLogin = new XdevButton();
		this.txtSmartContract = new XdevTextField();
		this.btnSubscribeEvents = new XdevButton();
		this.btnLoadContract = new XdevButton();
		this.cbLogTransactionReceipt = new XdevCheckBox();
		this.txtLog = new XdevTextArea();
		this.tabTransactions = new XdevGridLayout();
		this.lblBalance = new XdevLabel();
		this.btnGetTokens = new XdevButton();
		this.txtFromAddress = new XdevTextField();
		this.txtToAddress = new XdevTextField();
		this.txtValue = new XdevTextField();
		this.btnTransferTokens = new XdevButton();
		this.cbFromOwnAddress = new XdevCheckBox();
		this.tabAdmin = new XdevGridLayout();
		this.txtSecret = new XdevTextField();
		this.btnSetSecret = new XdevButton();
		this.txtNewMember = new XdevTextField();
		this.btnAddMember = new XdevButton();
	
		this.setCaption("");
		this.tabSheet.setStyleName("framed");
		this.txtNode.setCaption("Node:");
		this.txtNode.setValue("https://ropsten.infura.io/sS899But8ce9sP7plpOl");
		this.btnConnect.setCaption("Connect");
		this.uploadWalletfile.setCaption("Upload wallet file:");
		this.uploadWalletfile.setEnabled(false);
		this.txtPassword.setCaption("Password:");
		this.txtPassword.setEnabled(false);
		this.btnLogin.setCaption("Login");
		this.btnLogin.setEnabled(false);
		this.txtSmartContract.setCaption("Smart contract:");
		this.txtSmartContract.setEnabled(false);
		this.txtSmartContract.setValue("0xa316e5f639e3224b60198e869d33f621f9bb2979");
		this.btnSubscribeEvents.setCaption("Subscribe Events");
		this.btnSubscribeEvents.setEnabled(false);
		this.btnLoadContract.setCaption("Load contract");
		this.btnLoadContract.setEnabled(false);
		this.cbLogTransactionReceipt.setCaption("Log transaction receipt");
		this.cbLogTransactionReceipt.setValue(true);
		this.txtLog.setCaption("Log:");
		this.lblBalance.setConverter(ConverterBuilder.stringToBigInteger().build());
		this.lblBalance.setCaption("Balance:");
		this.lblBalance.setValue("0");
		this.btnGetTokens.setCaption("Get tokens");
		this.btnGetTokens.setEnabled(false);
		this.txtFromAddress.setCaption("From address:");
		this.txtFromAddress.setEnabled(false);
		this.txtToAddress.setCaption("To address:");
		this.txtToAddress.setEnabled(false);
		this.txtValue.setConverter(ConverterBuilder.stringToBigInteger().build());
		this.txtValue.setCaption("Value:");
		this.btnTransferTokens.setCaption("Transfer tokens");
		this.btnTransferTokens.setEnabled(false);
		this.cbFromOwnAddress.setCaption("From own address");
		this.cbFromOwnAddress.setEnabled(false);
		this.cbFromOwnAddress.setValue(true);
		this.txtSecret.setCaption("New secret:");
		this.txtSecret.setEnabled(false);
		this.btnSetSecret.setCaption("Set secret");
		this.btnSetSecret.setEnabled(false);
		this.txtNewMember.setCaption("New member:");
		this.txtNewMember.setEnabled(false);
		this.btnAddMember.setCaption("Add member");
		this.btnAddMember.setEnabled(false);
	
		this.tabConnect.setColumns(3);
		this.tabConnect.setRows(5);
		this.txtNode.setWidth(100, Unit.PERCENTAGE);
		this.txtNode.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.txtNode, 0, 0, 1, 0);
		this.tabConnect.setComponentAlignment(this.txtNode, Alignment.BOTTOM_RIGHT);
		this.btnConnect.setWidth(100, Unit.PERCENTAGE);
		this.btnConnect.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.btnConnect, 2, 0);
		this.tabConnect.setComponentAlignment(this.btnConnect, Alignment.BOTTOM_RIGHT);
		this.uploadWalletfile.setSizeUndefined();
		this.tabConnect.addComponent(this.uploadWalletfile, 0, 1);
		this.txtPassword.setWidth(100, Unit.PERCENTAGE);
		this.txtPassword.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.txtPassword, 1, 1);
		this.tabConnect.setComponentAlignment(this.txtPassword, Alignment.BOTTOM_RIGHT);
		this.btnLogin.setWidth(100, Unit.PERCENTAGE);
		this.btnLogin.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.btnLogin, 2, 1);
		this.tabConnect.setComponentAlignment(this.btnLogin, Alignment.BOTTOM_RIGHT);
		this.txtSmartContract.setWidth(100, Unit.PERCENTAGE);
		this.txtSmartContract.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.txtSmartContract, 0, 2);
		this.btnSubscribeEvents.setSizeUndefined();
		this.tabConnect.addComponent(this.btnSubscribeEvents, 1, 2);
		this.tabConnect.setComponentAlignment(this.btnSubscribeEvents, Alignment.BOTTOM_LEFT);
		this.btnLoadContract.setWidth(100, Unit.PERCENTAGE);
		this.btnLoadContract.setHeight(-1, Unit.PIXELS);
		this.tabConnect.addComponent(this.btnLoadContract, 2, 2);
		this.tabConnect.setComponentAlignment(this.btnLoadContract, Alignment.BOTTOM_RIGHT);
		this.cbLogTransactionReceipt.setSizeUndefined();
		this.tabConnect.addComponent(this.cbLogTransactionReceipt, 0, 3);
		this.tabConnect.setComponentAlignment(this.cbLogTransactionReceipt, Alignment.BOTTOM_LEFT);
		this.txtLog.setSizeFull();
		this.tabConnect.addComponent(this.txtLog, 0, 4, 2, 4);
		this.tabConnect.setColumnExpandRatio(0, 10.0F);
		this.tabConnect.setColumnExpandRatio(1, 10.0F);
		this.tabConnect.setColumnExpandRatio(2, 10.0F);
		this.tabConnect.setRowExpandRatio(4, 10.0F);
		this.tabTransactions.setColumns(4);
		this.tabTransactions.setRows(4);
		this.lblBalance.setSizeUndefined();
		this.tabTransactions.addComponent(this.lblBalance, 0, 0);
		this.btnGetTokens.setWidth(100, Unit.PERCENTAGE);
		this.btnGetTokens.setHeight(-1, Unit.PIXELS);
		this.tabTransactions.addComponent(this.btnGetTokens, 3, 0);
		this.tabTransactions.setComponentAlignment(this.btnGetTokens, Alignment.BOTTOM_RIGHT);
		this.txtFromAddress.setWidth(100, Unit.PERCENTAGE);
		this.txtFromAddress.setHeight(-1, Unit.PIXELS);
		this.tabTransactions.addComponent(this.txtFromAddress, 0, 1);
		this.txtToAddress.setWidth(100, Unit.PERCENTAGE);
		this.txtToAddress.setHeight(-1, Unit.PIXELS);
		this.tabTransactions.addComponent(this.txtToAddress, 1, 1);
		this.txtValue.setWidth(100, Unit.PERCENTAGE);
		this.txtValue.setHeight(-1, Unit.PIXELS);
		this.tabTransactions.addComponent(this.txtValue, 2, 1);
		this.btnTransferTokens.setWidth(100, Unit.PERCENTAGE);
		this.btnTransferTokens.setHeight(-1, Unit.PIXELS);
		this.tabTransactions.addComponent(this.btnTransferTokens, 3, 1);
		this.tabTransactions.setComponentAlignment(this.btnTransferTokens, Alignment.BOTTOM_RIGHT);
		this.cbFromOwnAddress.setSizeUndefined();
		this.tabTransactions.addComponent(this.cbFromOwnAddress, 0, 2);
		this.tabTransactions.setColumnExpandRatio(0, 10.0F);
		this.tabTransactions.setColumnExpandRatio(1, 20.0F);
		this.tabTransactions.setColumnExpandRatio(2, 10.0F);
		this.tabTransactions.setColumnExpandRatio(3, 10.0F);
		final CustomComponent tabTransactions_vSpacer = new CustomComponent();
		tabTransactions_vSpacer.setSizeFull();
		this.tabTransactions.addComponent(tabTransactions_vSpacer, 0, 3, 3, 3);
		this.tabTransactions.setRowExpandRatio(3, 1.0F);
		this.tabAdmin.setColumns(2);
		this.tabAdmin.setRows(3);
		this.txtSecret.setWidth(100, Unit.PERCENTAGE);
		this.txtSecret.setHeight(-1, Unit.PIXELS);
		this.tabAdmin.addComponent(this.txtSecret, 0, 0);
		this.btnSetSecret.setWidth(100, Unit.PERCENTAGE);
		this.btnSetSecret.setHeight(-1, Unit.PIXELS);
		this.tabAdmin.addComponent(this.btnSetSecret, 1, 0);
		this.tabAdmin.setComponentAlignment(this.btnSetSecret, Alignment.BOTTOM_RIGHT);
		this.txtNewMember.setWidth(100, Unit.PERCENTAGE);
		this.txtNewMember.setHeight(-1, Unit.PIXELS);
		this.tabAdmin.addComponent(this.txtNewMember, 0, 1);
		this.btnAddMember.setWidth(100, Unit.PERCENTAGE);
		this.btnAddMember.setHeight(-1, Unit.PIXELS);
		this.tabAdmin.addComponent(this.btnAddMember, 1, 1);
		this.tabAdmin.setComponentAlignment(this.btnAddMember, Alignment.BOTTOM_RIGHT);
		this.tabAdmin.setColumnExpandRatio(0, 30.0F);
		this.tabAdmin.setColumnExpandRatio(1, 10.0F);
		final CustomComponent tabAdmin_vSpacer = new CustomComponent();
		tabAdmin_vSpacer.setSizeFull();
		this.tabAdmin.addComponent(tabAdmin_vSpacer, 0, 2, 1, 2);
		this.tabAdmin.setRowExpandRatio(2, 1.0F);
		this.tabConnect.setSizeFull();
		this.tabSheet.addTab(this.tabConnect, "Connect", null);
		this.tabTransactions.setSizeFull();
		this.tabSheet.addTab(this.tabTransactions, "Transactions", null);
		this.tabAdmin.setSizeFull();
		this.tabSheet.addTab(this.tabAdmin, "Admin", null);
		this.tabSheet.setSelectedTab(this.tabConnect);
		this.tabSheet.setSizeFull();
		this.setContent(this.tabSheet);
		this.setSizeFull();
	
		this.btnConnect.addClickListener(event -> this.btnConnect_buttonClick(event));
		this.btnLogin.addClickListener(event -> this.btnLogin_buttonClick(event));
		this.btnSubscribeEvents.addClickListener(event -> this.btnSubscribeEvents_buttonClick(event));
		this.btnLoadContract.addClickListener(event -> this.btnLoadContract_buttonClick(event));
		this.cbLogTransactionReceipt.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				MainView.this.cbLogTransactionReceipt_valueChange(event);
			}
		});
		this.btnGetTokens.addClickListener(event -> this.btnGetTokens_buttonClick(event));
		this.btnTransferTokens.addClickListener(event -> this.btnTransferTokens_buttonClick(event));
		this.cbFromOwnAddress.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				MainView.this.cbFromOwnAddress_valueChange(event);
			}
		});
		this.btnSetSecret.addClickListener(event -> this.btnSetSecret_buttonClick(event));
		this.btnAddMember.addClickListener(event -> this.btnAddMember_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton btnConnect, btnLogin, btnSubscribeEvents, btnLoadContract, btnGetTokens, btnTransferTokens,
			btnSetSecret, btnAddMember;
	private XdevLabel lblBalance;
	private XdevUpload uploadWalletfile;
	private XdevPasswordField txtPassword;
	private XdevTextArea txtLog;
	private XdevTabSheet tabSheet;
	private XdevCheckBox cbLogTransactionReceipt, cbFromOwnAddress;
	private XdevGridLayout tabConnect, tabTransactions, tabAdmin;
	private XdevTextField txtNode, txtSmartContract, txtFromAddress, txtToAddress, txtValue, txtSecret, txtNewMember;
	// </generated-code>

}
