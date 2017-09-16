package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.misc.BASE64Encoder;


/**
 * 	Karamba!!!
 * 
 * 	Olhe lá no programa .net e compare para ver pq eu gosto mais de c#..... fala sério com tanta verbosidade!!!
 * 	O pior é que eu gosto de java tb..... parece craque isso aqui!!
 * @author wquin
 *
 */
public class FingerCompare {
	
	JButton btnImg1 = new JButton("Carregar Imagem1");
	JButton btnImg2 = new JButton("Carregar Imagem2");
	JButton btnCompare = new JButton("Compare");
	
	ImageIcon img1;
    JLabel label1;
    
    ImageIcon img2;
    JLabel label2;
	
    BufferedImage image1, image2;
    
	JPanel painel = new JPanel();
	
	JFrame janela = new JFrame("FingerPrint teste");
	
	public FingerCompare(){
		
		painel.add(btnImg1);
		painel.add(btnImg2);
		painel.add(btnCompare);
		
		janela.add(painel);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
	    janela.setSize(540, 540);
		janela.setVisible(true);
		
		btnImg1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				image1 = EscolhedorDeImagem.escolhe();
				img1 = new ImageIcon(image1);
				label1 = new JLabel(img1);
			}
		});
		btnImg2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				image2 = EscolhedorDeImagem.escolhe();
				img2 = new ImageIcon(image2);
				label2 = new JLabel(img2);
			}
		});
		
		btnCompare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean check = compareImagens(image1, image2);
				
				JOptionPane.showMessageDialog(null, check ? "Match!" : "NO Match!");
			}
		});
		
	}
	
	public boolean compareImagens(BufferedImage img1, BufferedImage img2){
		
		String img1String = encodeToString(img1);
		String img2String = encodeToString(img2);
		
		System.out.println(img1String);
		System.out.println(img2String);
		
		return img1String.equals(img2String);
		
	}
	
	public static String encodeToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();
            
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
 
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	public static void main(String[] args) {
		FingerCompare program = new FingerCompare();
	}
	
}

class EscolhedorDeImagem {

	  public static BufferedImage escolhe() {
	    try {
	      JFileChooser chooser = new JFileChooser();
	      chooser.setFileFilter(new FileNameExtensionFilter("Apenas imagens", "jpg","bmp","png"));
	      int retorno = chooser.showOpenDialog(null);

	      if (retorno == JFileChooser.APPROVE_OPTION) {
	    	  BufferedImage img = ImageIO.read(chooser.getSelectedFile());
	        return img;
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	  }
	}
