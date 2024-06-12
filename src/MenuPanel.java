import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import classes.Carrinho;
import services.GerenciarDados;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

public class MenuPanel extends JPanel {
    private Color corFundo = Color.decode("#06ADBF");
    private Color corTexto = Color.decode("#0B4359");
    private Color corLinhaMenu = Color.BLACK;
    private Color corJanelaAtual = Color.MAGENTA;
    private Font fontePadrao = new Font("SansSerif", Font.PLAIN, 20);

    public MenuPanel(Integer permissao, JFrame parent, GerenciarDados gerencia, Carrinho carrinho) {
        setLayout(null);
        setBackground(corFundo);

        JLabel labelTitulo = new JLabel("Pizzaria");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 38));
        labelTitulo.setBounds(5, 5, 150, 40);

        JLabelWithLine linhaMenu = new JLabelWithLine("");
        linhaMenu.setBounds(0, 35, 800, 20);
        linhaMenu.setLineColor(corLinhaMenu);
        linhaMenu.setName("linha");
        
        JLabelWithLine labelCardapio = new JLabelWithLine("Cardápio");
        labelCardapio.setBounds(170, 20, 85, 25);
        labelCardapio.setLineColor(corJanelaAtual);
        labelCardapio.setFont(fontePadrao);
        
        JLabel labelCarrinho = new JLabel("Carrinho");
        labelCarrinho.setBounds(565, 20, 80, 25);
        labelCarrinho.setFont(fontePadrao);
        
        JLabel labelPerfil = new JLabel("Perfil");
        labelPerfil.setBounds(645, 20, 55, 25);
        labelPerfil.setFont(fontePadrao);

        JLabel labelLogout = new JLabel("Logout");
        labelLogout.setBounds(700, 20, 65, 25);
        labelLogout.setFont(fontePadrao);

        ArrayList<JLabel> labels = new ArrayList<>();

        if (permissao == 0) {
            JLabel labelCriarPizza = new JLabel("CriarPizza");
            labelCriarPizza.setBounds(465, 20, 100, 25);
            labelCriarPizza.setFont(fontePadrao);

            JLabel labelCriarIngrediente = new JLabel("CriarIngrediente");
            labelCriarIngrediente.setBounds(315, 20, 150, 25);
            labelCriarIngrediente.setFont(fontePadrao);

            labelCriarPizza.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CriarPizza criarPizza = new CriarPizza(parent, gerencia);

                    criarPizza.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            gerencia.carregarProdutos();
                        }
                    });
                }
            });

            labelCriarIngrediente.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JDialog criarIngrediente = new CriarIngrediente(parent, gerencia);

                    criarIngrediente.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            gerencia.carregarIngredientes();
                        }
                    });
                }
            });

            Collections.addAll(labels, labelCriarPizza, labelCriarIngrediente);
        }
        
        labelCarrinho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog exibirCarrinho = new ExibirCarrinho(parent, gerencia, carrinho);

                exibirCarrinho.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        gerencia.carregarCarrinhos();
                    }
                });
            };
        });

        labelCardapio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.getContentPane().removeAll();
                new Cardapio(gerencia.getUsuarios().get(carrinho.getUsuarioId()), parent, gerencia);
                parent.revalidate();
                parent.repaint();
            }
        });

        Collections.addAll(labels, labelTitulo, linhaMenu, labelLogout, labelCardapio, labelCarrinho, labelPerfil);

        labels.forEach(label -> {
            label.setForeground(corTexto);

            if (label.getName() == null) {
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        label.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        label.setForeground(corTexto);
                    }
                });
            }

            add(label);
        });

        labelLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.setVisible(true);
                SwingUtilities.getWindowAncestor(MenuPanel.this).dispose();
            }
        });
    }

    public Color getCorFundo() {
        return corFundo;
    }

    public Color getCorTexto() {
        return corTexto;
    }

    public Color getCorLinhaMenu() {
        return corLinhaMenu;
    }

    public Color getCorJanelaAtual() {
        return corJanelaAtual;
    }

    public Font getFontePadrao() {
        return fontePadrao;
    }
}