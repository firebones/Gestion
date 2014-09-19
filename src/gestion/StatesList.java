package gestion;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class StatesList extends JFrame 
{
  protected JList m_statesList;

  public StatesList() {
  	super("Swing List [Base]");
	setSize(500, 240);

	String [] states = {
		"AK\tAlaska\tJuneau",
		"AL\tAlabama\tMontgomery",
		"AR\tArkansas\tLittle Rock",
		"AZ\tArizona\tPhoenix",
		"CA\tCalifornia\tSacramento",
		"CO\tColorado\tDenver",
		"CT\tConnecticut\tHartford",
		"DE\tDelaware\tDover",
		"FL\tFlorida\tTallahassee",
		"GA\tGeorgia\tAtlanta",
		"HI\tHawaii\tHonolulu",
		"IA\tIowa\tDes Moines",
		"ID\tIdaho\tBoise",
		"IL\tIllinois\tSpringfield",
		"IN\tIndiana\tIndianapolis",
		"KS\tKansas\tTopeka",
		"KY\tKentucky\tFrankfort",
		"LA\tLouisiana\tBaton Rouge",
		"MA\tMassachusetts\tBoston",
		"MD\tMaryland\tAnnapolis",
		"ME\tMaine\tAugusta",
		"MI\tMichigan\tLansing",
		"MN\tMinnesota\tSt.Paul",
		"MO\tMissouri\tJefferson City",
		"MS\tMississippi\tJackson",
		"MT\tMontana\tHelena",
		"NC\tNorth Carolina\tRaleigh",
		"ND\tNorth Dakota\tBismarck",
		"NE\tNebraska\tLincoln",
		"NH\tNew Hampshire\tConcord",
		"NJ\tNew Jersey\tTrenton",
		"NM\tNew Mexico\tSantaFe",
		"NV\tNevada\tCarson City",
		"NY\tNew York\tAlbany",
		"OH\tOhio\tColumbus",
		"OK\tOklahoma\tOklahoma City",
		"OR\tOregon\tSalem",
		"PA\tPennsylvania\tHarrisburg",
		"RI\tRhode Island\tProvidence",
		"SC\tSouth Carolina\tColumbia",
		"SD\tSouth Dakota\tPierre",
		"TN\tTennessee\tNashville",
		"TX\tTexas\tAustin",
		"UT\tUtah\tSalt Lake City",
		"VA\tVirginia\tRichmond",
		"VT\tVermont\tMontpelier",
		"WA\tWashington\tOlympia",
		"WV\tWest Virginia\tCharleston",
		"WI\tWisconsin\tMadison",
		"WY\tWyoming\tCheyenne"
		};

	m_statesList = new JList(states);
	TabListCellRenderer renderer = new TabListCellRenderer();
	renderer.setTabs(new int[] {50, 200, 300});
	m_statesList.setCellRenderer(renderer);

  	JScrollPane ps = new JScrollPane();
	ps.getViewport().add(m_statesList);
	getContentPane().add(ps, BorderLayout.CENTER);

	WindowListener wndCloser = new WindowAdapter()
	{
                @Override
		public void windowClosing(WindowEvent e) 
		{
			System.exit(0);
		}
	};
	addWindowListener(wndCloser);
	
	setVisible(true);
  }
}

// NEW
class TabListCellRenderer extends JLabel implements ListCellRenderer
{
    protected static Border m_noFocusBorder;
    protected FontMetrics m_fm = null;
    protected Insets m_insets = new Insets(0, 0, 0, 0);

    protected int m_defaultTab = 50;
    protected int[] m_tabs = null;

    public TabListCellRenderer()
    {
	super();
	m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	setOpaque(true);
	setBorder(m_noFocusBorder);
    }

    @Override
    public Component getListCellRendererComponent(JList list,
		Object value, int index, boolean isSelected, boolean cellHasFocus)     
    {         
	setText(value.toString());

	setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
	setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
		
	setFont(list.getFont());
	setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);

	return this;
    }

    public void setDefaultTab(int defaultTab) { m_defaultTab = defaultTab; }

    public int getDefaultTab() { return m_defaultTab; }

    public void setTabs(int[] tabs) { m_tabs = tabs; }

    public int[] getTabs() { return m_tabs; }

    public int getTab(int index)
    {
	if (m_tabs == null)
	   return m_defaultTab*index;
		
	int len = m_tabs.length;
	if (index >= 0 && index < len)
	   return m_tabs[index];

	return m_tabs[len-1] + m_defaultTab*(index-len+1);
    }


    @Override
    public void paint(Graphics g)
    {
	m_fm = g.getFontMetrics();
	
	g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
	getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());

	g.setColor(getForeground());
	g.setFont(getFont());
	m_insets = getInsets();
	int x = m_insets.left;
	int y = m_insets.top + m_fm.getAscent();

	StringTokenizer	st = new StringTokenizer(getText(), "\t");
	while (st.hasMoreTokens()) 
	{
	      String sNext = st.nextToken();
	      g.drawString(sNext, x, y);
		x += m_fm.stringWidth(sNext);

		if (!st.hasMoreTokens())
			break;
		int index = 0;
		while (x >= getTab(index))
	  	      index++;
		x = getTab(index);
	}
   }

}