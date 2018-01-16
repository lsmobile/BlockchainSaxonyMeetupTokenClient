package org.bcsm.tokenclient.ui;

import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevCheckBox;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevPasswordField;
import com.xdev.ui.XdevTextArea;
import com.xdev.ui.XdevTextField;
import com.xdev.ui.XdevUpload;

public class CommonUIState {
	
	public XdevLabel lblBalance;
	public XdevButton btnConnect, btnLogin, btnSubscribeEvents, btnLoadContract,
		btnGetTokens, btnSetSecret, btnAddMember, btnTransferTokens;
	public XdevUpload uploadWalletfile;
	public XdevPasswordField txtPassword;
	public XdevTextArea txtLog;
	public XdevTextField txtNode,txtSmartContract, txtSecret, txtNewMember,
		txtFromAddress, txtToAddress, txtValue;
	public XdevCheckBox cbLogTransactionReceipt, cbFromOwnAddress;
	
}
