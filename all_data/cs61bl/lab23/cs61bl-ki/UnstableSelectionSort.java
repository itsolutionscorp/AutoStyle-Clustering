public class UnstableSelectionSort {

	public static void selectionSort(Comparable[] arr) {
		for (int j = arr.length - 1; j > 0; j--) {
			int latestPos = 0;
			for (int k = 1; k <= j; k++) {
				if (arr[latestPos].compareTo(arr[k]) == -1) {
					latestPos = k;
				} else if (arr[latestPos].compareTo(arr[k])== 0){
					latestPos=k;
				}
			}
			if (j != latestPos) {
				Comparable temp = arr[j];
				arr[j] = arr[latestPos];
				arr[latestPos] = temp;
			}
		}
	}
	
	

	
	
	public static void main(String[] args){
		
		Card[] cards = {new Card("A", 1),new Card("B", 1),new Card("A", 3),new Card("A", 2),new Card("D", 1),new Card("A", 10),new Card("A", 13),new Card("A", 12),new Card("C", 3)};
		
		
		
		UnstableSelectionSort.selectionSort(cards);
		for(Card card: cards){
			System.out.print(card.suit+card.value+" ");
		}
		System.out.println();
		UnstableSelectionSort.selectionSort(cards);
		for(Card card: cards){
			System.out.print(card.suit+card.value+" ");
		}
		System.out.println();
		UnstableSelectionSort.selectionSort(cards);
		for(Card card: cards){
			System.out.print(card.suit+card.value+" ");
		}
		System.out.println();
		UnstableSelectionSort.selectionSort(cards);
		for(Card card: cards){
			System.out.print(card.suit+card.value+" ");
		}
		System.out.println();
		
	}
}
