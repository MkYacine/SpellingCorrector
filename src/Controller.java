import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {
	private Model model;
	
	public Controller(Model model) {
		this.model=model;
	}
	
	public class OpenClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.open();
			
		}
		
	}
	public class SaveClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.save();
			
		}
		
	}
	public class DictClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.dict();
		}
		
	}
	public class CheckClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.check();
		}
		
	}
	public class CorrectClass implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			model.correct();
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class ChangeClass implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.change();
			
		}
	}
	public class HighlightFlag implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			model.flag();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}