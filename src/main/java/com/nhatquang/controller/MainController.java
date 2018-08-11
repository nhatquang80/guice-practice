package com.nhatquang.controller;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.nhatquang.dto.BTCClientTransDto;
import com.nhatquang.form.BTCCriteria;
import com.nhatquang.model.BTCClient;
import com.nhatquang.model.BTCTransaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import com.nhatquang.service.ClientTransactionService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Quang Nguyen
 */
public class MainController implements Initializable {

    private ClientTransactionService clientTransactionService;

    @Inject
    public void setClientTransactionService(ClientTransactionService clientTransactionService) {
        this.clientTransactionService = clientTransactionService;
    }

    @FXML
    private TreeTableView<BTCClientTransDto> viewTable;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> idCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> clientIdentifierCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> btcAddressCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> countryCodeCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> avatarCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> itemCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, Number> paymentCol;
    @FXML
    private TreeTableColumn<BTCClientTransDto, String> dateCol;
    @FXML
    private TextField itemText;
    @FXML
    private TextField countryCodeText;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private Button searchButton;
    @FXML
    private Button clearButton;

    TreeItem<BTCClientTransDto> root = new TreeItem<>(new BTCClientTransDto());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // binding columns data
        idCol.setCellValueFactory(param -> param.getValue().getValue().idProperty());
        clientIdentifierCol.setCellValueFactory(param -> param.getValue().getValue().clientIdentifierProperty());
        btcAddressCol.setCellValueFactory(param -> param.getValue().getValue().btcAddressProperty());
        countryCodeCol.setCellValueFactory(param -> param.getValue().getValue().countryCodeProperty());
        avatarCol.setCellValueFactory(param -> param.getValue().getValue().avatarProperty());
        itemCol.setCellValueFactory(param -> param.getValue().getValue().itemProperty());
        paymentCol.setCellValueFactory(param -> param.getValue().getValue().paymentProperty());
        dateCol.setCellValueFactory(param -> param.getValue().getValue().dateProperty());

        // search button action
        searchButton.setOnAction(actionEvent -> {
            String itemStr = itemText.getText();
            String countryCodeStr = countryCodeText.getText();
            LocalDate fromDate = fromDatePicker.getValue();
            LocalDate toDate = toDatePicker.getValue();

            BTCCriteria criteria = new BTCCriteria(itemStr, countryCodeStr, fromDate, toDate, null, null, null);

            List<BTCClient> searchResults = clientTransactionService.getByCriteria(criteria);
            root.getChildren().setAll(buildTreeItemList(searchResults));
        });

        // clear button action
        clearButton.setOnAction(actionEvent -> {
            itemText.setText("");
            countryCodeText.setText("");
            fromDatePicker.setValue(null);
            toDatePicker.setValue(null);
        });

        viewTable.setShowRoot(false);
        viewTable.setRoot(root);
    }

    /**
     *
     * @param clients
     * @return
     */
    private List<TreeItem<BTCClientTransDto>> buildTreeItemList(List<BTCClient> clients) {
        List<TreeItem<BTCClientTransDto>> treeItems = Lists.newArrayList();
        for (BTCClient client : clients) {
            TreeItem<BTCClientTransDto> clientTreeItem = new TreeItem<>(new BTCClientTransDto(
                    client.getId(),
                    client.getClientIdentifier(),
                    client.getBtcAddress(),
                    client.getCountryCode(),
                    client.getAvatar(),
                    null,
                    null,
                    null
            ));
            if (client.getTransactions() != null && client.getTransactions().size() > 0) {
                for (BTCTransaction trans : client.getTransactions()) {
                    TreeItem<BTCClientTransDto> transTreeItem = new TreeItem<>(new BTCClientTransDto(
                            null,
                            null,
                            null,
                            null,
                            null,
                            trans.getItem(),
                            trans.getPayment(),
                            trans.getDate()
                    ));
                    clientTreeItem.getChildren().add(transTreeItem);
                }
            }
            treeItems.add(clientTreeItem);
        }
        return treeItems;
    }
}
