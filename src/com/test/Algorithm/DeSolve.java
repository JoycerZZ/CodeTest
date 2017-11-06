package com.test.Algorithm;

import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DeSolve {

	public int popSize ; //��Ⱥ��С

	public int iterTimes ; //��������
	
	public static double mutationPro = 0.8 ; //�������
	
	public static double stretchingFactor = 0.7 ; //��������
	
	public int cityNum ; //��������������룬����������Ⱦɫ�峤��
	
    public int[][] oldPopulation; //��ʼ����Ⱥ
    
    public int[][] newPopulation; // �µ���Ⱥ���Ӵ���Ⱥ 
    
    public int[] fitness ; //ÿ������ֵ
    
    public double bestfitness ; //������Ӧֵ
    
    public int[] bestfitnessIndival ; //���Ÿ���
    
    public double[][] distanceMatrix ; //�ڵ�������
     
    
	public DeSolve(int popSize, int cityNum , int iterTimes) {
		this.popSize = popSize;
		this.iterTimes = iterTimes;
		this.cityNum = cityNum;
		
		fitness = new int[popSize];
		bestfitnessIndival = new int[cityNum];
		bestfitness = Double.MAX_VALUE;
	}

	//��ʼ������
	public void initMatrix(){
		distanceMatrix = new double[this.cityNum][this.cityNum];
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse("att48.xml");
			NodeList vertexList = document.getElementsByTagName("vertex");

			for(int i = 0 ; i < vertexList.getLength() ; i++){
				Node vertex = vertexList.item(i);
				
				NodeList childNodes = vertex.getChildNodes();
				
				for(int k = 0 ; k < childNodes.getLength() ; k++){
					if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
						Element tag = (Element) childNodes.item(k);
						
						double distance = Double.parseDouble(tag.getAttribute("cost"));
						int colum = Integer.parseInt(childNodes.item(k).getFirstChild().getNodeValue());
						
						distanceMatrix[i][colum] = distance;
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ʼ����Ⱥ
	public void initPop(){
		this.oldPopulation = new int[this.popSize][this.cityNum];
		for(int i = 0 ; i < this.popSize ; i++){
			for(int j = 0 ; j < this.cityNum ; ){
				Random random = new Random();
				oldPopulation[i][j] = random.nextInt(this.cityNum);
				int k ;
				for(k = 0 ; k < j ; k++){
					if(oldPopulation[i][k] == oldPopulation[i][j]) break;
				}
				if(k == j) j++;
			}
		}
		
		for(int k = 0 ; k < this.popSize ; k++){
			double fitness = this.Evaluate(oldPopulation[k]);
			if(fitness < this.bestfitness){
				bestfitness = fitness;
				bestfitnessIndival = oldPopulation[k];
			}
		}
	}
	
	//�������
	public void Mutation(){
		
	}
	
	//�������
	public void CrossOver(int k){
		int count = 0 ;//��������
		Random random = new Random();
		count = random.nextInt(cityNum);
		
		for(int i = 0 ; i < count ; i++){
			int index1 = random.nextInt(cityNum);
			int index2 = random.nextInt(cityNum);
			while(index1 == index2){
				index2 = random.nextInt(cityNum);
			}
			
			int temp = oldPopulation[k][index1];
			oldPopulation[k][index1] = oldPopulation[k][index2];
			oldPopulation[k][index2] = temp;
		}
	}
	
	
	//��������
	public double Evaluate(int[] chorm){
		double fitnessTemp = 0.0 ;
		for(int i = 1 ; i < chorm.length ; i++){
			fitnessTemp += distanceMatrix[chorm[i]][chorm[i - 1]];
		}
		
		fitnessTemp += distanceMatrix[chorm[cityNum - 1]][chorm[0]];;
		return fitnessTemp ;
	}
}
