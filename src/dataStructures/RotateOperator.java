package dataStructures;

public class RotateOperator extends Operator{
	
	public RotateOperator(){
		
	}
	
	public State rotate(PokemonState state, boolean left, boolean right){

		int dir = changeDirection(state.getDirection(), left, right);
		State result =  new PokemonState(state.getLocation(),state.getTimeToHatch(),state.getUncollectedPokemons(),dir);
		return result;
	}

	static int changeDirection(int direction, boolean left, boolean right){
		int result = 0;
		
		if(left)
		{
			switch (direction)
			{
			case 0:
				result = 3;
				break;
			case 1:
				result = 0;
				break;
			case 2:
				result = 1;
				break;
			case 3:
				result = 2;
				break;
			default :
				System.out.println("error");
				break;
			}
		}
		else if (right)
		{
			switch (direction)
			{
			case 0:
				result = 1;
				break;
			case 1:
				result = 2;
				break;
			case 2:
				result = 3;
				break;
			case 3:
				result = 0;
				break;
			default :
				System.out.println("error");
				break;
			}
		}
		
		return result;
	}	
}
