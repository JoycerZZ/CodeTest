package com.test.Algorithm;

public class RunTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeSolve de = new DeSolve(100 , 48 , 100);
		de.initMatrix();
		de.initPop();
		
		for(int index = 0 ; index < 100 ; index++){
			for(int k = 0 ; k < de.popSize ; k++){
				de.CrossOver(k);
				de.Evaluate(de.oldPopulation[k]);
			}
			System.out.println("第" + index + "代最优评价值为：" + de.bestfitness);
		}
		
		System.out.println("最短距离为:" + de.bestfitness);
		System.out.println("最佳路径为:");
		for(int i = 0 ; i < de.bestfitnessIndival.length ; i++)
			System.out.print(de.bestfitnessIndival[i] + " ");
	}

}
