//Sujay Shankar
//Java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
//Some Variable Reminders for their contents at the end of the code
//indivCount/indivCount2: For Article1/Article2 separated words by spaces in alphabetical order
//Count/Count2:Shows the Count for each respective element in indivCount

public class news{
	
	static class WordAndCount	 {
		private String word;
		private int Num;
		
		public WordAndCount(String word,int Num) {
			this.word=word;
			this.Num=Num;
			
		}
	}
	
	public static void main(String[]args) throws IOException {
		//Reads in Input
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String article1=in.readLine().toUpperCase();
		String article2=in.readLine().toUpperCase();
		//Separates string by spaces to get array of words and then sorts
		//array alphabetically
		String[]AllWords1=article1.split(" ");
		String[]AllWords2=article2.split(" ");
		Arrays.sort(AllWords2);
		Arrays.sort(AllWords1);
		int sizeArticle1=AllWords1.length;
		int sizeArticle2=AllWords2.length;
		
		//ArrayList of Unique Words
		ArrayList<String>indivWords1=new ArrayList<String>();
		ArrayList<String>indivWords2=new ArrayList<String>();
		//ArrayList of the Count of each unique Word
		ArrayList<Integer>Count=new ArrayList<Integer>();
		ArrayList<Integer>Count2=new ArrayList<Integer>();
		
		indivWords1.add(AllWords1[0]);
		indivWords2.add(AllWords2[0]);
		Count.add(1);
		Count2.add(1);
		int indivCount=0;
		int indivCount2=0;
		//Goes through full text and populates unique words and their count
		//into the respective ArrayList
		for(int i=1;i<AllWords1.length;i++) {
			if(AllWords1[i].equals(indivWords1.get(indivCount))) {
				Count.set(indivCount, Count.get(indivCount)+1);
			}
			else {
				indivWords1.add(AllWords1[i]);
				Count.add(1);
				indivCount++;
			}
		}
		//Same thing for second article
		for(int i=1;i<AllWords2.length;i++) {
			if(AllWords2[i].equals(indivWords2.get(indivCount2))) {
				Count2.set(indivCount2, Count2.get(indivCount2)+1);
			}
			else {
				indivWords2.add(AllWords2[i]);
				Count2.add(1);
				indivCount2++;
			}
		}
		//Creating an ArrayList for each article in which we pair the unique
		//words and Count into 1 object to sort later
		ArrayList<WordAndCount>pairs=new ArrayList<WordAndCount>();
		ArrayList<WordAndCount>pairsInitial=new ArrayList<WordAndCount>();
		ArrayList<WordAndCount>pairs2=new ArrayList<WordAndCount>();
		ArrayList<WordAndCount>pairs2Initial=new ArrayList<WordAndCount>();
		for(int i=0;i<indivWords1.size();i++) {
			WordAndCount temp=new WordAndCount(indivWords1.get(i),Count.get(i));
			pairs.add(temp);
			pairsInitial.add(temp);
			
		}
		for(int i=0;i<indivWords2.size();i++) {
		WordAndCount temp2=new WordAndCount(indivWords2.get(i),Count2.get(i));
		pairs2.add(temp2);
		pairs2Initial.add(temp2);
		}
		//Sorting pairs from greatest to least in terms of the Count using an
		//insertion sort
		for(int a=1;a<pairs.size();a++) {
			for(int j=a;j>0;j--) {
				if(pairs.get(j).Num>pairs.get(j-1).Num) {
						WordAndCount temp3=pairs.get(j-1);
						pairs.set(j-1, pairs.get(j));
						pairs.set(j, temp3);
				}
			}
		}
		for(int a=1;a<pairs2.size();a++) {
			for(int j=a;j>0;j--) {
				if(pairs2.get(j).Num>pairs2.get(j-1).Num) {
						WordAndCount temp3=pairs2.get(j-1);
						pairs2.set(j-1, pairs2.get(j));
						pairs2.set(j, temp3);
				}
			}
		}
		//ArrayList of the common words in the two articles and their average ratios among the two articles that they
		//share
		ArrayList<WordAndCount>Common=new ArrayList<WordAndCount>();
		
		//Finding the common words and rank based on average ratio
		//The Meanratio is the average of the ratio of the common word in its respective article.  We then store
		//the Meanratio times 100 and cast it as a int to assign each common word a score to which we can rank.
		for(int i=0;i<pairsInitial.size();i++) {
			for(int j=0;j<pairs2Initial.size();j++) {
				if(pairsInitial.get(i).word.equals(pairs2Initial.get(j).word)) {
					double Meanratio=(((double)(pairsInitial.get(i).Num)/sizeArticle1+(double)(pairs2Initial.get(j)).Num/sizeArticle2)/2);
					int Score=(int)((Meanratio)*1000);
					WordAndCount temp=new WordAndCount(pairsInitial.get(i).word,Score);
					Common.add(temp);
					break;
				}
			}
		}
		
		//Sorting Common from Greatest Mean Ratio Score (Mean Ratio*100) to least
		for(int a=1;a<Common.size();a++) {
			for(int j=a;j>0;j--) {
				if(Common.get(j).Num>Common.get(j-1).Num) {
						WordAndCount temp3=Common.get(j-1);
						Common.set(j-1, Common.get(j));
						Common.set(j, temp3);
				}
			}
		}
		
		//Printing Article1 Words and Count Sorted
		System.out.println("Shows each unique Words in Article or Text Number 1 and the Count of each unique word");

		for(int i=0;i<pairs.size();i++) {
			System.out.print(pairs.get(i).word+" ");
		}
		System.out.println();

		for(int i=0;i<pairs.size();i++) {
			System.out.print(pairs.get(i).Num+" ");
		}
		System.out.println("\n\n");

		//Printing Article2 Words and Count sorted 
		System.out.println("Shows each unique Words in Article or Text Number 2 and the Count of each unique word");
		for(int i=0;i<pairs2.size();i++) {
			System.out.print(pairs2.get(i).word+" ");
		}
		System.out.println();

		for(int i=0;i<pairs2.size();i++) {
			System.out.print(pairs2.get(i).Num+" ");
		}
		System.out.println("\n");
		
		//Printing the Common Words in the articles and their MeanRatio times 1000
		System.out.println("Words shared between both texts and their rank it terms of their Mean Ratio Score between both texts ");
		System.out.println("The Mean Ratio Score is the average of the ratios of the word in each respective article.  This average is then multiplied by 1000.   ");

		for(int i=0;i<Common.size();i++) {
			System.out.print(Common.get(i).word+" ");
		}
		System.out.println();

		for(int i=0;i<Common.size();i++) {
			System.out.print(Common.get(i).Num+" ");
		}
		
		
		in.close();
	}
}
