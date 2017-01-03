package mp2.util;

class Parent{
	static String fieldSta = "Parent static field1";
	String fieldFin = "Parent final field1";
	String fieldPr = "Parent private field1";
	String field1 = "Parent field1";
	String field2 = field1;
	Parent(){
		System.out.println("Parent constructor");
	}
	{
		System.out.println("Parent non static");
	}
	static{
		System.out.println("Parent static");
	}
	void maethod(){
		System.out.println("Parent method");
	}
	public static String getFieldSta() {
		return fieldSta;
	}
	public String getFieldFin() {
		return fieldFin;
	}
	public String getFieldPr() {
		return fieldPr;
	}
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	
}
class Child extends Parent{
	static String fieldSta = "Child static field1";
	String fieldFin = "Child final field1";
	String fieldPr = "Child private field1";
	String field1 = "Child field1";
	String field2 = field1;
	Child(){
		System.out.println("Child constructor");
	}
	{
		System.out.println("Child non static");
	}
	static{
		System.out.println("Child static");
	}
	void maethod(){
		System.out.println("Child method");
	}
	public static String getFieldSta() {
		return fieldSta;
	}
	public String getFieldFin() {
		return fieldFin;
	}
	public String getFieldPr() {
		return fieldPr;
	}
	public String getField1() {
		return field1;
	}
	public String getField2() {
		return field2;
	}
	
}
public class LinkingTest {

	public static void main(String[] args) {
		Parent pc = new Child();
		System.out.println();
		System.out.println(pc.field1);
		System.out.println(pc.field2);
		System.out.println(pc.fieldFin);
		System.out.println(pc.fieldPr);
		System.out.println(pc.fieldSta);
		System.out.println();
		System.out.println(pc.getField1());
		System.out.println(pc.getField2());
		System.out.println(pc.getFieldFin());
		System.out.println(pc.getFieldPr());
		System.out.println(pc.getFieldSta());

	}

}
