import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class Okno extends JFrame implements KeyListener, ActionListener
{
	public boolean zapis=true;
	//KEY!!!!!!!!
	JTextField typingArea = new JTextField(20);
	
	JLabel predkosc_info;
	JLabel obroty_info;
	JLabel przebieg_info;
	JLabel przebieg_calkowity_info;
	JLabel srednia_info;
	JLabel tekst_srednia;
	Autko moje=new Autko();
		
	public void paint(Graphics g)
	{
		//Wyświetlamy prędkość na prędkosciomierzu
		float predkosc_max=200;
		g.clearRect(190, 90, 110, 110);//hm?
		
		g.setColor(Color.blue);
		g.drawArc(190, 90, 110, 110, 180,360);
		g.setColor(Color.red);
		
		for(int i=0;i<20;i++)
			g.drawArc(200+i,100+i,90-2*i,90-2*i,(int)(180-180*((moje.getPredkosc())/predkosc_max)),2);
	
		float obroty_max=3500;
		g.clearRect(300, 90, 110, 110);//hm?
		
		g.setColor(Color.blue);
		g.drawArc(300, 90, 110, 110, 180,360);
		g.setColor(Color.red);
	
		
		for(int i=0;i<20;i++)
			g.drawArc(310+i,100+i,90-2*i,90-2*i,(int)(180-180*((moje.getObroty())/obroty_max)),2);
		
		//additional
		String tekst_predkosc="";
		String tekst_obroty="";
		String tekst_przebieg="";
		String tekst_przebieg_calkowity="";
		tekst_predkosc=String.valueOf(moje.getPredkosc());
		tekst_obroty=String.valueOf(moje.getObroty());
		
		tekst_przebieg=String.valueOf(moje.przebieg_jazdy);
		
		tekst_przebieg_calkowity = String.valueOf(moje.przebieg_calkowity);
		//tekst_srednia.setText("Predkosc srednia:");
		srednia_info.setText(String.valueOf(moje.komputer.predkosc_srednia));
		predkosc_info.setText(tekst_predkosc);
		obroty_info.setText(tekst_obroty);
		przebieg_info.setText(tekst_przebieg);
		przebieg_calkowity_info.setText(tekst_przebieg_calkowity);
		
		
if(moje.is_kier_lewy()){
	for(int i=0;i<3;i++)
{
	g.setColor(Color.red);
	for(int j=150;j<170;j+=4)g.drawRect(450,j,50,5);

	try {
		Thread.sleep(100);
		} catch(InterruptedException e) {
		}
	g.setColor(this.getBackground()); 
	for(int j=150;j<170;j+=4)g.drawRect(450,j,50,5);

	try {
		Thread.sleep(100);
		} catch(InterruptedException e) {
		} 	
}
	
}
else if(moje.is_kier_prawy()){
	 	
	for(int i=0;i<3;i++)
{
g.setColor(Color.red);
for(int j=150;j<170;j+=4)g.drawRect(100,j,50,5);

	try {
		Thread.sleep(100);
		} catch(InterruptedException e) {
		} 	
	g.setColor(this.getBackground()); 
	for(int j=150;j<170;j+=4)g.drawRect(100,j,50,5);

	try {
		Thread.sleep(100);
		} catch(InterruptedException e) {
		} 	
}
		
}
	}
	
	//KEY!!!!!!!!
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyChar()=='a')
		moje.skrecaj(-1);
		else if(arg0.getKeyChar()=='d')
			moje.skrecaj(1);
		else if(arg0.getKeyChar()=='w')
		{
			moje.gazuj();
			repaint();
		}
			
		else if(arg0.getKeyChar()=='s')
				moje.hamuj();

		repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		moje.skrecaj(0);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
JMenu komputerPokladowy;
JMenuItem komp;
	JMenuItem przebiegPodrozy;
	public Okno ()
	{
		this.moje=new Autko();
		
		

		//ustawienie rozmiaru okna i jego polozenia
		Toolkit zestaw = Toolkit.getDefaultToolkit();
		
		
		//KEY!!!!!!!!
		typingArea = new JTextField(20);
	    typingArea.addKeyListener(this);
	    getContentPane().add(typingArea, BorderLayout.PAGE_START);
	    //KEY!!!!!!!!
	    
	    
		Dimension rozmiarEkranu = zestaw.getScreenSize();
		int wysokosc = rozmiarEkranu.height - WYSOKOSC;
		int szerokosc = rozmiarEkranu.width - SZEROKOSC;
		
		setSize(SZEROKOSC, WYSOKOSC);
		setLocation(szerokosc/2, wysokosc/2);
		
		setTitle("Uproszczony model tablicy rozdzielczej samochodu osobowego");
		setResizable(false);
		
		Image ikonka = zestaw.getImage("ikonka.gif");
		setIconImage(ikonka);
		
		//Tworzenie Paska menu i...
		JMenuBar pasekMenu = new JMenuBar();
		setJMenuBar(pasekMenu);
		
		JMenu menuPlik = new JMenu("Plik");
		menuPlik.setMnemonic('L');
		menuPlik.setMnemonic('l');
		pasekMenu.add(menuPlik);
		
		przebiegPodrozy = new JMenuItem("Odczyt zapisanego przebiegu");
		menuPlik.add(przebiegPodrozy);
		przebiegPodrozy.addActionListener(this);
		
		JMenuItem automatycznyPilot = new JMenuItem("Automatyczny Pilot");
		menuPlik.add(automatycznyPilot);
		menuPlik.addSeparator();
		
		JMenuItem ustawienia = new JMenuItem("Ustawienia");
		menuPlik.add(ustawienia);
		menuPlik.addSeparator();
		
		JMenuItem zamknij = new JMenuItem("Zamknij ALT+F4");
		menuPlik.add(zamknij);
		
		//--------
		komputerPokladowy = new JMenu("Komputer pokladowy");
		komputerPokladowy.setMnemonic('K');
		pasekMenu.add(komputerPokladowy);
		komp = new JMenuItem("odpal");
		komputerPokladowy.add(komp);
		
		
		//-------
		JMenu menuInfo = new JMenu("Autorzy");
		pasekMenu.add(menuInfo);
		
		JMenuItem autor1 = new JMenuItem("Artur");
		JMenuItem autor2 = new JMenuItem("Krzysiek");
		menuInfo.add(autor1);
		menuInfo.add(autor2);
		menuInfo.addSeparator();
		
		//---------
		JMenu menuPomocy = new JMenu("Pomoc");
		menuPomocy.setMnemonic('P');
		pasekMenu.add(menuPomocy);
		
		JMenuItem oProgramie = new JMenuItem("O programie");
		menuPomocy.add(oProgramie);
		
		JMenuItem plikPomocy = new JMenuItem("Plik pomocy");
		menuPomocy.add(plikPomocy);
		
		setLayout(null);

		JLabel pred_et=new JLabel("Predkosc:");
		pred_et.setBounds(300, 280, 200, 20);
		
		predkosc_info=new JLabel("0");
		predkosc_info.setBounds(300, 300, 200, 20);
		add(pred_et);
		add(predkosc_info);
				
		JLabel obroty_et=new JLabel("Obroty:");
		obroty_et.setBounds(380, 280, 200, 20);
		
		obroty_info=new JLabel("0");
		obroty_info.setBounds(380, 300, 200, 20);
		add(obroty_et);
		add(obroty_info);
		
		JLabel przebieg_et=new JLabel("Przebieg:");
		przebieg_et.setBounds(460,280,200,20);

		JLabel przebieg_calkowity_et=new JLabel("Przebieg calkowity:");
		przebieg_calkowity_et.setBounds(100,280,200,20);
		
		przebieg_calkowity_info=new JLabel("Przebieg calkowity:");
		przebieg_calkowity_info.setBounds(100,300,200,20);
			
		srednia_info=new JLabel("0");
		srednia_info.setBounds(230,300,200,20);
		
		tekst_srednia=new JLabel("Srednia:");
		tekst_srednia.setBounds(230,280,200,20);
		
		
		przebieg_info=new JLabel("0");
		przebieg_info.setBounds(460,300,200,20);
		add(tekst_srednia);
		add(srednia_info);
		add(przebieg_et);
		add(przebieg_info);
		add(przebieg_calkowity_et);
		add(przebieg_calkowity_info);
		repaint();
		
	}
	public static final int SZEROKOSC = 640;
	public static final int WYSOKOSC = 400;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==przebiegPodrozy){
			
			if(zapis==false)
			zapis=true;
			
			else
				zapis=false;
			
			
		}
		if(arg0.getSource()==komp)
		{
			moje.komputer.enable();
		}
	}

		   };
	   
