import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.shape.*;

public class userInterface extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	// --------------------------------------------------

	boolean flag = false;
	Contact co;
	Contact[] co1;
	Files fl = new Files();
	PasswordDialog ps = new PasswordDialog();
	String path = null;

	// --------------------button------------------------------
	BorderPane root = new BorderPane();
	TextArea textarea = new TextArea();
	Menu fileMenu = new Menu("File");
	Menu Contact = new Menu("Contact");
	Button createPDF = new Button("createPDF");
	Button EmailConact = new Button("EmailContact");
	Label tags = new Label("Tags: ");
	SplitMenuButton holders = new SplitMenuButton();
	MenuBar top = new MenuBar();
	HBox hbox = new HBox();
	HBox hbox1 = new HBox();
	VBox vbox = new VBox();

	// ----------------------fileMenu---------------------------------
	MenuItem loadContact = new MenuItem("load contacts file");
	MenuItem loadSavedTemplate = new MenuItem("load saved template");
	MenuItem saveTemplate = new MenuItem("save Template");
	MenuItem saveTemplateAs = new MenuItem("save Template as");

	// -------------------Contact menu----------------------------
	MenuItem openContactManager = new MenuItem("open Contact Manager");

	// ---------------------------holders----------------------------------
	MenuItem title = new MenuItem("[[TITLE]]");
	MenuItem firstName = new MenuItem("[[FIRST_NAME]]");
	MenuItem lastName = new MenuItem("[[LAST_NAME]]");
	MenuItem idNumber = new MenuItem("[[ID_NUMBER]]");
	MenuItem emailAddress = new MenuItem("[[EMAIL_ADDRESS]]");
	MenuItem hW = new MenuItem("[[HW]]");
	MenuItem quizzes = new MenuItem("[[QUIZZES]]");
	MenuItem lab = new MenuItem("[[LAB]]");
	MenuItem midterm = new MenuItem("[[MIDTERM]]");
	MenuItem classTest = new MenuItem("[[CLASS_TESTS]]");
	MenuItem Final = new MenuItem("[[FINAL]]");
	MenuItem total = new MenuItem("[[TOTAL]]");
	MenuItem letterGrade = new MenuItem("[[LETTER_GRADE]]");

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		vbox.setMinSize(600, 500);
		textarea.setMaxSize(600, 500);
		fileMenu.getItems().addAll(loadContact, loadSavedTemplate, saveTemplate, saveTemplateAs);
		Contact.getItems().add(openContactManager);
		holders.getItems().addAll(title, firstName, lastName, idNumber, emailAddress, hW, quizzes, lab, midterm,
				classTest, Final, total, letterGrade);
		top.getMenus().addAll(fileMenu, Contact);
		hbox.getChildren().addAll(createPDF, EmailConact, tags, hbox1, holders);
//			holders.setAlignment(Pos.BASELINE_RIGHT);
		vbox.getChildren().addAll(textarea, hbox);
//			vbox.setAlignment(Pos.BOTTOM_RIGHT);

		holders.setDisable(true);
		Contact.setDisable(true);
		// createPDF.setDisable(true);
		// EmailConact.setDisable(true);
		saveTemplate.setDisable(true);
		saveTemplateAs.setDisable(true);
		// textarea.setDisable(true);
		textarea.setEditable(false);

		vbox.setAlignment(Pos.CENTER);
		// vbox.setMaxSize(600, 600);
		// textarea.setScaleY(400);
		Scene scene = new Scene(root);
		root.setMinSize(300, 300);
		root.setTop(top);
		root.setCenter(vbox);

//			root.setBottom(hbox);

		// -------------------------Action--------------------------------------------

		loadContact.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				FileChooser f = new FileChooser();
				f.setTitle("open a file");
				f.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel", "*.csv"));
//				f.showOpenDialog(s);

				File file = f.showOpenDialog(null);

				if (file != null) {

					holders.setDisable(false);
					Contact.setDisable(false);
//					createPDF.setDisable(false);
//		            EmailConact.setDisable(false);
//		            Contact.setDisable(false);
//		            saveTemplate.setDisable(false);
//		            saveTemplateAs.setDisable(false);
					textarea.setEditable(true);

					Desktop d = Desktop.getDesktop();

					try {
						// System.out.println(file.getPath());
						fl.read(file.getPath());
						ps.read(file.getPath(), textarea.getText());

//						d.open(file);
					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
				}

			}
		});

		loadSavedTemplate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				saveTemplate.setDisable(false);
				saveTemplateAs.setDisable(false);
				FileChooser f = new FileChooser();
				f.setTitle("open a template");
				f.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("file text", "*.txt"));
				StringBuilder sb = null;

				Desktop d = Desktop.getDesktop();
				File selectedFile = f.showOpenDialog(stage);
				path = selectedFile.getPath();
				sb = readFile(selectedFile);
				textarea.setText(sb.toString());

//				try {
//					d.open(file);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

			}
		});

		saveTemplate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				File f = new File(path);

				saveTextToFile(textarea.getText(), f);
				Label message = new Label("File saved,successfully");
				VBox v = new VBox();
				v.getChildren().add(message);
				v.setAlignment(Pos.CENTER);
				Stage newWindow = new Stage();
				Scene s = new Scene(v);
				newWindow.setScene(s);
				newWindow.setX(300);
				newWindow.setY(300);
				newWindow.show();

			}
		});

		saveTemplateAs.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				FileChooser fileChooser = new FileChooser();

				// Set extension filter for text files
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File file = fileChooser.showSaveDialog(stage);

				if (file != null) {
					saveTextToFile(textarea.getText(), file);
				}

			}
		});

		createPDF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				// -----------------------Content of Table-----------------------------------
				if (textarea.getText().trim().length() != 0 && !(holders.isDisable())) {

					TableView table = new TableView();

					TableColumn<String, Contact> c1 = new TableColumn<>("[[Title]]");
					c1.setCellValueFactory(new PropertyValueFactory<>("Title"));

					TableColumn<String, Contact> c2 = new TableColumn<>("[[FIRST_NAME]]");
					c2.setCellValueFactory(new PropertyValueFactory<>("FirstN"));

					TableColumn<String, Contact> c3 = new TableColumn<>("[[LAST_NAME]]");
					c3.setCellValueFactory(new PropertyValueFactory<>("LastN"));

					TableColumn<String, Contact> c4 = new TableColumn<>("[[ID_NUMBER]]");
					c4.setCellValueFactory(new PropertyValueFactory<>("Id"));

					TableColumn<String, Contact> c5 = new TableColumn<>("[[EMAIL_ADDRESS]]");
					c5.setCellValueFactory(new PropertyValueFactory<>("Email"));

					TableColumn<String, Contact> c6 = new TableColumn<>("[[HW]]");
					c6.setCellValueFactory(new PropertyValueFactory<>("HomeWork"));

					TableColumn<String, Contact> c7 = new TableColumn<>("[[QUIZZES]]");
					c7.setCellValueFactory(new PropertyValueFactory<>("Quizzes"));

					TableColumn<String, Contact> c8 = new TableColumn<>("[[LAB]]");
					c8.setCellValueFactory(new PropertyValueFactory<>("Lab"));

					TableColumn<String, Contact> c9 = new TableColumn<>("[[MIDTERM]]");
					c9.setCellValueFactory(new PropertyValueFactory<>("MidTerm"));

					TableColumn<String, Contact> c10 = new TableColumn<>("[[CLASS_TESTS]]");
					c10.setCellValueFactory(new PropertyValueFactory<>("ClassTest"));

					TableColumn<String, Contact> c11 = new TableColumn<>("[[FINAL]]");
					c11.setCellValueFactory(new PropertyValueFactory<>("Final"));

					TableColumn<String, Contact> c12 = new TableColumn<>("[[TOTAL]]");
					c12.setCellValueFactory(new PropertyValueFactory<>("TotalGrade"));

					TableColumn<String, Contact> c13 = new TableColumn<>("[[LETTER_GRADE]]");
					c13.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

					table.getColumns().add(c1);
					table.getColumns().add(c2);
					table.getColumns().add(c3);
					table.getColumns().add(c4);
					table.getColumns().add(c5);
					table.getColumns().add(c6);
					table.getColumns().add(c7);
					table.getColumns().add(c8);
					table.getColumns().add(c9);
					table.getColumns().add(c10);
					table.getColumns().add(c11);
					table.getColumns().add(c12);
					table.getColumns().add(c13);

					co1 = new Contact[fl.co.length];
					for (int i = 1; i < co1.length; i++) {

						table.getItems().add(fl.co[i]);
					}


					Button ok = new Button("OK");
					VBox v = new VBox(table, ok);
					v.setAlignment(Pos.CENTER);
					Scene s = new Scene(v);
					Stage newWindow = new Stage();
					newWindow.setTitle("Information of Contact");
					newWindow.setScene(s);
					newWindow.show();
					// -------------------------save contact in pdf
					// file-----------------------------------------------

					ok.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							FileChooser fileChooser = new FileChooser();

							// Set extension filter for text files
							FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)",
									"*.pdf");
							fileChooser.getExtensionFilters().add(extFilter);

							// Show save file dialog
							File file = fileChooser.showSaveDialog(stage);

							// file.getPath()

							if (file != null) {
								saveTextToFile(textarea.getText(), file);
							}

							fl.makePDF(textarea.getText());

							newWindow.close();
						}
					});
				}
			}
		});

		title.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == title) {
					textarea.appendText(" [[TITLE]] ");

				}
			}
		});

//title.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				if(event.getSource()==title) {
//					textarea.appendText(" [[TITLE]] ");
//
//
//				}
//			}
//		});

		firstName.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == firstName) {
					textarea.appendText(" [[FIRST_NAME]] ");

				}
			}
		});

		lastName.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == lastName) {
					textarea.appendText(" [[LAST_NAME]] ");

				}
			}
		});

		idNumber.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == idNumber) {
					textarea.appendText(" [[ID_NUMBER]] ");

				}
			}
		});

		emailAddress.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == emailAddress) {
					textarea.appendText(" [[EMAIL_ADDRESS]] ");

				}
			}
		});

		hW.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == hW) {
					textarea.appendText(" [[HW]] ");

				}
			}
		});

		quizzes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == quizzes) {
					textarea.appendText(" [[QUIZZES]] ");

				}
			}
		});

		lab.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == lab) {
					textarea.appendText(" [[LAB]] ");

				}
			}
		});

		midterm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == midterm) {
					textarea.appendText(" [[MIDTERM]] ");

				}
			}
		});

		classTest.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == classTest) {
					textarea.appendText(" [[CLASS_TESTS]] ");

				}
			}
		});

		Final.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == Final) {
					textarea.appendText(" [[FINAL]] ");

				}
			}
		});

		total.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == total) {
					textarea.appendText(" [[TOTAL]] ");

				}
			}
		});

		letterGrade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource() == letterGrade) {
					textarea.appendText(" [[LETTER_GRADE]] ");

				}
			}
		});

		EmailConact.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (textarea.getText().trim().length() != 0 && !(holders.isDisable())) {
					if (event.getSource() == EmailConact) {
						try {
							ps.read("", textarea.getText());
						} catch (Exception e) {

						}

						ps.start(stage);
						// fl.start(stage);

					}

				}
			}
		});
		
		
		openContactManager.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Button add = new Button("add");
				Button edit = new Button("edit");
				Button delete = new Button("delete");
				Button save = new Button("save");
				
				TableView table1 = new TableView();
				table1.setEditable(true);
				// edit in table
				TableColumn<String, Contact> c1 = new TableColumn<>("[[Title]]");
				c1.setCellValueFactory(new PropertyValueFactory<>("Title"));
				
			
			
//				c1.setOnEditCommit((CellEditEvent<Contact, String> t) -> {
//		            TablePosition<Contact, String> pos = t.getTablePosition();
//		 
//		            String newFullName = t.getNewValue();
//		 
//		            int row = pos.getRow();
//		            Contact person = t.getTableView().getItems().get(row);
//		 
//		            person.setTitle(newFullName);
//		        });
				

				
				
				
				//				c1.setOnEditCommit((TableColumn.CellEditEvent<Contact,String> event) -> {
//					TablePosition<Contact,String> pos = event.getTablePosition();
//					
//					String title = event.getNewValue();
//					
//					int row = pos.getRow();
//					Contact a = event.getTableView().getItems().get(row);
//					a.setTitle(title);
//				});
//				

				TableColumn<String, Contact> c2 = new TableColumn<>("[[FIRST_NAME]]");
				c2.setCellValueFactory(new PropertyValueFactory<>("FirstN"));

				TableColumn<String, Contact> c3 = new TableColumn<>("[[LAST_NAME]]");
				c3.setCellValueFactory(new PropertyValueFactory<>("LastN"));

				TableColumn<String, Contact> c4 = new TableColumn<>("[[ID_NUMBER]]");
				c4.setCellValueFactory(new PropertyValueFactory<>("Id"));

				TableColumn<String, Contact> c5 = new TableColumn<>("[[EMAIL_ADDRESS]]");
				c5.setCellValueFactory(new PropertyValueFactory<>("Email"));

				TableColumn<String, Contact> c6 = new TableColumn<>("[[HW]]");
				c6.setCellValueFactory(new PropertyValueFactory<>("HomeWork"));

				TableColumn<String, Contact> c7 = new TableColumn<>("[[QUIZZES]]");
				c7.setCellValueFactory(new PropertyValueFactory<>("Quizzes"));

				TableColumn<String, Contact> c8 = new TableColumn<>("[[LAB]]");
				c8.setCellValueFactory(new PropertyValueFactory<>("Lab"));

				TableColumn<String, Contact> c9 = new TableColumn<>("[[MIDTERM]]");
				c9.setCellValueFactory(new PropertyValueFactory<>("MidTerm"));

				TableColumn<String, Contact> c10 = new TableColumn<>("[[CLASS_TESTS]]");
				c10.setCellValueFactory(new PropertyValueFactory<>("ClassTest"));

				TableColumn<String, Contact> c11 = new TableColumn<>("[[FINAL]]");
				c11.setCellValueFactory(new PropertyValueFactory<>("Final"));

				TableColumn<String, Contact> c12 = new TableColumn<>("[[TOTAL]]");
				c12.setCellValueFactory(new PropertyValueFactory<>("TotalGrade"));

				TableColumn<String, Contact> c13 = new TableColumn<>("[[LETTER_GRADE]]");
				c13.setCellValueFactory(new PropertyValueFactory<>("LetterGrade"));

				table1.getColumns().add(c1);
				table1.getColumns().add(c2);
				table1.getColumns().add(c3);
				table1.getColumns().add(c4);
				table1.getColumns().add(c5);
				table1.getColumns().add(c6);
				table1.getColumns().add(c7);
				table1.getColumns().add(c8);
				table1.getColumns().add(c9);
				table1.getColumns().add(c10);
				table1.getColumns().add(c11);
				table1.getColumns().add(c12);
				table1.getColumns().add(c13);

				co1 = new Contact[fl.co.length];
				for (int i = 1; i < co1.length; i++) {

					// text to add 
						TextField addtitle = new TextField();
				        addtitle.setPromptText("Title");
				        addtitle.setMaxWidth(c1.getPrefWidth());
				        
				        TextField addFirstName = new TextField();
				        addFirstName.setMaxWidth(c2.getPrefWidth());
				        addFirstName.setPromptText("First Name");
				        
				        TextField addLastName = new TextField();
				        addLastName.setMaxWidth(c3.getPrefWidth());
				        addLastName.setPromptText("Last Name");
				        
				        TextField addId = new TextField();
				        addId.setPromptText("Id");
				        addId.setMaxWidth(c1.getPrefWidth());
				        
				        TextField addEmail = new TextField();
				        addEmail.setMaxWidth(c2.getPrefWidth());
				        addEmail.setPromptText("Email");
				        
				        TextField addHW = new TextField();
				        addHW.setMaxWidth(c3.getPrefWidth());
				        addHW.setPromptText("HW");
				        
				        TextField addQuiz = new TextField();
				        addQuiz.setPromptText("Quiz");
				        addQuiz.setMaxWidth(c1.getPrefWidth());
				        
				        TextField addLab = new TextField();
				        addLab.setMaxWidth(c2.getPrefWidth());
				        addLab.setPromptText("Lab");
				        
				        TextField addMitTerm = new TextField();
				        addMitTerm.setMaxWidth(c3.getPrefWidth());
				        addMitTerm.setPromptText("Midterm");
				        
				        TextField addClassTest = new TextField();
				        addClassTest.setPromptText("class test");
				        addClassTest.setMaxWidth(c1.getPrefWidth());
				        
				        TextField addFinal = new TextField();
				        addFinal.setMaxWidth(c2.getPrefWidth());
				        addFinal.setPromptText("Final");
				        
				        TextField addTotal = new TextField();
				        addTotal.setMaxWidth(c3.getPrefWidth());
				        addTotal.setPromptText("Total");
				        
				        TextField addletterGrade = new TextField();
				        addletterGrade.setPromptText("letter grade");
				        addletterGrade.setMaxWidth(c1.getPrefWidth());
				        
				      
					
					
					table1.getItems().add(fl.co[i]);
					HBox h = new HBox(addtitle,addFirstName,addLastName,addId,addEmail,addHW,addQuiz,addLab,addMitTerm,addClassTest,addFinal,addTotal,addletterGrade,add,edit,delete,save);
					h.setAlignment(Pos.CENTER);
					VBox v = new VBox(table1,h);
					v.setAlignment(Pos.CENTER);
					Scene s1 = new Scene(v);
					Stage newWindow1 = new Stage();
					newWindow1.setTitle("Contact");
					newWindow1.setScene(s1);
					newWindow1.show();
					
					add.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							table1.getItems().add(new Contact(addtitle.getText(), addFirstName.getText(), addLastName.getText(), addId.getText(), addEmail.getText(), addHW.getText(), addQuiz.getText(), addLab.getText(), addMitTerm.getText(), addClassTest.getText(), addFinal.getText(), addTotal.getText(), addletterGrade.getText()));
						}
					});
					
					delete.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							
							table1.getItems().removeAll(table1.getSelectionModel().getSelectedItems());
							
						}
					});
					
				}
				
				save.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						FileChooser fileChooser = new FileChooser();

						// Set extension filter for text files
						FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
						fileChooser.getExtensionFilters().add(extFilter);

						// Show save file dialog
						File file = fileChooser.showSaveDialog(stage);

						if (file != null) {
//							saveTextToFile(textarea.getText(), file);
							
							saveContactToFile(table1.getItems(), file);
							
						}
						
						
					}
				});
				
			}
		});
		
		
		

		stage.setScene(scene);
		stage.setTitle("MailMerge");
		stage.setMinHeight(500);
		stage.setMinWidth(600);

		stage.show();

	}

	// -------------------Read context from a file
	// ----------------------------------

	public StringBuilder readFile(File selectedFile) {
		StringBuilder sb = new StringBuilder(1024);
		String curLine = "";
		try {
			FileReader fr = new FileReader(selectedFile);
			BufferedReader br = new BufferedReader(fr);

			while (curLine != null) {
				curLine = br.readLine();
				if (!(curLine.equals("null")))
					sb.append(curLine).append("\n");

			}
			br.close();
		} catch (Exception e) {
			// e.getMessage();
		}
		return sb;
	}

	public static boolean makePDF(String messageToBeSent) {
		try {
			// You should change the file name
			OutputStream file = new FileOutputStream(new File("Test.pdf"));

			// keep the following 3 lines unchanged
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			// generate 10 pages
			for (int i = 0; i < 10; i++) {
				document.add(new Paragraph(messageToBeSent));
				// go to new page
				document.newPage();
			}
			document.close();
			file.close();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
	private void saveTextToFile(String content, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(content);
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void saveContactToFile(ObservableList list, File file) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(file);
			writer.println(list);
			writer.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}