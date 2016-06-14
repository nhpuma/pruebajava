package Problema04;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Prestamo extends JFrame implements ActionListener{
	//Declaración de variables
	private JLabel lblMontoPrestamo;
	private JTextField txtMontoPrestamo;
	private JScrollPane scpScroll;
	private JTextArea txtArea;
	private JButton btnBorrar;
	private JButton btnProcesar;
	
	//Declaración de variables globales
	double montoPrestamo, tasaInteres, montoMensual, montoInteres; 
	int cuotas;
	
	//lanza la aplicación
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Prestamo frame = new Prestamo();
				frame.setVisible(true);
			}
		});		
	}
	
	//Crea la GUI
	public Prestamo(){
		setTitle("Préstamo de dinero by nhpuma");
		setBounds(100, 100, 450, 214);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblMontoPrestamo = new JLabel("Monto del Préstamo:");
		lblMontoPrestamo.setBounds(10, 13, 115, 14);
		getContentPane().add(lblMontoPrestamo);
		
		txtMontoPrestamo = new JTextField();
		txtMontoPrestamo.setBounds(125, 13, 90, 20);
		getContentPane().add(txtMontoPrestamo);
		txtMontoPrestamo.setColumns(10);
				
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(246, 9, 89, 23);
		getContentPane().add(btnBorrar);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 9, 89, 23);
		getContentPane().add(btnProcesar);
		
		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 44, 414, 120);
		getContentPane().add(scpScroll);
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtArea);
	}

	//Direcciona eventos de tipo ActionEvent
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnProcesar){
			actionPerformedBtnProcesar(e);
		}
		if (e.getSource() == btnBorrar){
			actionPerformedBtnBorrar(e);
		}
	}

	//Proceso la pulsación del botón Procesar
	private void actionPerformedBtnBorrar(ActionEvent e) {
		txtMontoPrestamo.setText("");
		txtArea.setText("");
		txtMontoPrestamo.requestFocus();
	}

	//Procespo la pulsación del botón Borrar
	private void actionPerformedBtnProcesar(ActionEvent e) {
		ingresarDatos();
		obtenerNumeroCuotas(montoPrestamo);
		obtenerTasaInteres();
		obtenerMontoInteresTotal();
		obtenerCuotaMensual();
		mostrarResultados();
	}

	private void obtenerMontoInteresTotal() {
		montoInteres = tasaInteres*montoPrestamo*cuotas;
	}

	private void obtenerCuotaMensual() {
		montoMensual = (montoPrestamo+montoInteres)/cuotas;
	}

	private void obtenerTasaInteres() {
		if (montoPrestamo > 10000)
			tasaInteres = 0.03;
		else
			tasaInteres = 0.05;
	}

	private void obtenerNumeroCuotas(double montoPrestamo2) {
		
		if(montoPrestamo2 <= 5000)
			cuotas = 2;
		else if(montoPrestamo2 > 5000 && montoPrestamo2 <= 10000)
			cuotas = 4;
		else if(montoPrestamo2 > 10000 && montoPrestamo2 <= 15000)
			cuotas = 6;
		else if (montoPrestamo2 >= 15000)
			cuotas = 10;
	}

	private void mostrarResultados() {
		txtArea.setText("");
		imprimir("Monto ingresado: "+montoPrestamo);
		imprimir("Cuotas: "+cuotas);
		imprimir("Tasa de Interés: "+ tasaInteres);
		imprimir("Monto mensual: "+montoMensual);
		imprimir("Interés Totalx: "+montoInteres);
	}
	//Imprime una linea de texto icnluyendo un salto de linea al final
	private void imprimir(String cad){
		txtArea.append(cad+" \n");
	}

	//Efectúa la entrada de datos
	private void ingresarDatos() {
		montoPrestamo = Double.parseDouble(txtMontoPrestamo.getText());
	}

}
