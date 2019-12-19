class Man {
	int age;
	int height;
	int weight;

	public Man() {
		System.out.println("Man Class Called");
	}

	public void walk() {
		System.out.println("Walking..");
	}

	public void eat() {
		System.out.println("Eating..");
	} 
}
class Father extends  Man{
	int children;

	public Father() {
		System.out.println("Father Class Called");
	}

	public void care() {
		System.out.println("Caring..");
	}
}
class Employee extends Father {
	int salary;

	public Employee() {
		System.out.println("Employee class called");
	}

	public void code() {
		System.out.println("Coding..");
	}
}
class Test {
	public static void main(String[] args) {
		Man obj = new Employee();
	}
}