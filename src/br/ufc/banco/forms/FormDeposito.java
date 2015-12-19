package br.ufc.banco.forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class FormDeposito extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumConta;
	private JTextField txtDeposito;
	/**
	 * Create the frame.
	 */
	public FormDeposito() {
		setType(Type.UTILITY);
		setTitle("DEP\u00D3SITO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 228, 192);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNmeroDaConta = new JLabel("N\u00DAMERO DA CONTA");
		
		txtNumConta = new JTextField();
		txtNumConta.setColumns(10);
		
		JLabel lblDepsito = new JLabel("DEP\u00D3SITO");
		
		txtDeposito = new JTextField();
		txtDeposito.setColumns(10);
		
		JButton btnCancelar = new JButton("CANCELAR");
		
		JButton btnDepositar = new JButton("DEPOSITAR");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtNumConta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addComponent(txtDeposito, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNmeroDaConta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblDepsito))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDepositar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNmeroDaConta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtNumConta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDepsito)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDeposito, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnDepositar))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}

}
