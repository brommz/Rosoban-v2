package model;
import model.Pos.Dir;

public interface ElementVisitor {
	/*pojawila sie wizyta w elemencie - false, jezeli nie moze wizytowac visitor*/
	boolean visitEnter(Element visitor, Dir dir);
	
	/*odchodzi wizyta z elementu - false, jezeli nie moze wizytowac visitor*/
	boolean visitLeave(Element visitor, Dir dir);
	
	/*informuje obiekt, ze visitor chce odwiedzic te miejsce*/
	void visitNotify(Element visitor, Dir dir);
}
