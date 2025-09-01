public class Process {
	private int id;
	private int instructions;
	
	public Process(int id, int instructions) {
		this.id = id;
		this.instructions = instructions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInstructions() {
		return instructions;
	}

	public void setInstructions(int instructions) {
		this.instructions = instructions;
	}
	
	public void executeInstructions(){
		if(instructions > 0){

			instructions--;
		};
	}
	
	public boolean finished(){
		return instructions == 0;
	}
	
	
	
}