<!DOCTYPE html>
<html>
<head>
<title>Mobility Project</title>


<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="include/board.css">
<script src="javascript/board.js"></script>

<!--  	<link rel="stylesheet" href="include/extjs/resources/css/ext-all.css" />
	<link rel="stylesheet" href="include/styles.css">
	<script src="include/extjs/adapter/ext/ext-base.js"></script>
	<script src="include/extjs/ext-all-debug.js"></script>
	<script>
		Ext.onReady(function() {
			//add data store here
			var store = new Ext.data.Store({
				url: '/tutorial/rest/issuers',
				reader: new Ext.data.JsonReader({
					root: 'issuers',
					id: 'ticker'
				}, [
					'ticker',
					'issuerName',
					'issuerType',
					'country' 
				])
			});
			store.load();
			
			
			var ds_model = Ext.data.Record.create([
				'ticker',
				'issuerName',
				'issuerType',
				'country'
			]);
			
			var ticker_edit = new Ext.form.TextField();
			var name_edit = new Ext.form.TextField();
			var type_edit = new Ext.form.TextField();
			var country_edit = new Ext.form.TextField();
			
			var sm2 = new Ext.grid.CheckboxSelectionModel();
			var grid = new Ext.grid.EditorGridPanel({
		        id:'button-grid',
		        store: store,
		        cm: new Ext.grid.ColumnModel([
					new Ext.grid.RowNumberer(),
		            {header: "Ticker", dataIndex: 'ticker', sortable: true},
					{id: 'name', header: "Issuer Name", dataIndex: 'issuerName', sortable: true, editor: name_edit},
					{header: "Issuer Type", dataIndex: 'issuerType', sortable: true, width: 75, editor: type_edit},
					{header: "Country", dataIndex: 'country', sortable: true, width: 75, editor: country_edit}
		        ]),
		        
		        selModel: new Ext.grid.RowSelectionModel({
		            singleSelect: false
		        }),
		        
		        listeners: {
		        	afteredit: function(e) {
			        	var _ticker = e.record.data.ticker;
			        	var _issuerName = (e.field == 'issuerName') ? e.value : e.record.data.issuerName;
			        	var _issuerType = (e.field == 'issuerType') ? e.value : e.record.data.issuerType;
			        	var _country = (e.field == 'country') ? e.value : e.record.data.country;
			        	
			        	var restURL = '/tutorial/rest/issuer/update/' + _ticker;
			        	var conn = new Ext.data.Connection();
	            		conn.request({
		            		url: restURL,
		            		method: 'PUT',
		            		params: {
		            			ticker: _ticker,
			            		issuerName: _issuerName,
			            		issuerType: _issuerType,
			            		country: _country
		            		},
		            		success: function(a, response) {
		            			e.record.commit();
                            },
                            failure: function(a, response) {
                                Ext.Msg.alert("Failed", response.result.message);
                                e.record.reject();
                            }
	            		});
		        	}
		       	},
			
		        viewConfig: {
		            forceFit:true
		        },

		        // inline toolbars
		        tbar:[{
		            text:'Add Issuer',
		            tooltip:'Add a new Issuer',
		            icon: 'images/addIssuer16.png',
		            cls: 'x-btn-text-icon',
		            handler: function() {
		            	var form = new Ext.form.FormPanel({
		    		        baseCls: 'x-plain',
		    		        labelWidth: 75,
		    		        name: 'MyForm',
		    		        url: '/tutorial/rest/issuer/addIssuer',
		    		        defaultType: 'textfield',

		    		        items: [{
		    		            fieldLabel: 'Ticker',
		    		            id: 'ticker', 
		    		            name: 'ticker',
		    		            xtype: 'textfield',
		    		            maxLength: 10,
		    		            allowBlank:false,
		    		            width: 100,
		    		            listeners: {
		    		                afterrender: function(field) {
		    		                  field.focus(false, 200);
		    		                }
		    		              }
		    		        },{
		    		            fieldLabel: 'Issuer Name',
		    		            id: 'issuerName',
		    		            name: 'issuerName',
		    		            allowBlank:false,
		    		            anchor: '100%'  // anchor width by percentage
		    		        }, {
		    		        	fieldLabel: 'Issuer Type',
		    		        	id: 'issuerType',
		    		            name: 'issuerType',
		    		            maxLength: 10,
		    		            width: 90
		    		        }, {
		    		        	fieldLabel: 'Country',
		    		        	id: 'country',
		    		            name: 'country',
		    		            maxLength: 20,
		    		            width: 150
		    		        }]
		    		    });
		    			
		    			var window = new Ext.Window({
		    		        title: 'Add New Issuer',
		    		        width: 350,
		    		        height:180,
		    		        minWidth: 350,
		    		        minHeight: 180,
		    		        layout: 'fit',
		    		        plain:true,
		    		        bodyStyle:'padding:5px;',
		    		        buttonAlign:'center',
		    		        resizable: false,
		    		        items: form,

		    		        buttons: [{
		    		            text: 'Save Issuer',
		    		            handler: function () {
		    		            	var formTicker = Ext.get('ticker').getValue();
		    		            	var formName = Ext.get('issuerName').getValue();
		    		            	var formType = Ext.get('issuerType').getValue();
		    		            	var formCountry = Ext.get('country').getValue();
		    		            	
		    		            	if (form.getForm().isValid()) {
			    		            	form.getForm().submit({
			    		            		method: 'POST',
			            					url: '/tutorial/rest/issuer/addIssuer',
			                                success: function(a, response)
			                                {
				                              	grid.getStore().insert(
				          				            	0,
				          				            	new ds_model({
				          				            		ticker: formTicker,
				  		            						issuerName: formName,
				  		            						issuerType: formType,
				  		            						country: formCountry
				          				            	})
				           				        );
				    		            		window.close();
			                                },
			                                failure: function(a, response)
			                                {
			                                    Ext.Msg.alert("Failed", response.result.message);
			                                     
			                                }
			                            });
		    		            	}
		    		            }
		    		        },{
		    		            text: 'Cancel',
		    		            handler: function () {
		    		            	if (window) {
		    		            		window.close();
		    		            	}
		    		            }
		    		        }]
		    		    });
		    			
		            	window.show();
		            }
		        },'-',{
		            text:'Remove Issuer',
		            tooltip:'Remove the selected issuer',
		            icon: 'images/removeIssuer16.png',
		            cls: 'x-btn-text-icon',
		            handler: function() {
		            	var sm = grid.getSelectionModel();
		            	var sel = sm.getSelected();
		            	if (sm.hasSelection()) {
		            		Ext.Msg.show({
		            			title: 'Remove Issuer',
		            			buttons: Ext.MessageBox.YESNOCANCEL,
		            			msg: 'Remove ' + sel.data.issuerName + '?',
		            			fn: function(btn) {
		            				if (btn == 'yes') {
		            					var conn = new Ext.data.Connection();
		            					var restURL = '/tutorial/rest/issuer/delete/' + sel.data.ticker;
		            					conn.request({
		            						method: 'DELETE',
			            					url: restURL,
			            					success: function(resp,opt) {
			            						grid.getStore().remove(sel);
			            					},
			            					failure: function(resp,opt) {
				            					Ext.Msg.alert('Error', 'Unable to delete issuer');
				            				}
			            				});
		            				}
		            			}
		            		});
		            	};
		            }
		        }],

		        width: 600,
		        height: 350,
		        collapsible: true,
		        frame: true,
		        clicksToEdit: 2,
		        animCollapse: false,
		        title:'Issuer Grid Panel for MongoDB Access',
		        iconCls:'icon-grid',
		        renderTo: document.body
		    });
		});
		
	</script>
	
	-->
	
</head>
<body>
<nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/tutorial/">Mobility Project</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Link1</a></li>
            <li><a href="#">Link2</a></li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
    
	<div id="mygrid">
	<div id="wrapper">
	<div id="columns">
	</div>
</div>
	</div>
</body>
</html>