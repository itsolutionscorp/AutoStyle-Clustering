from collections import Counter

def word_count(phrase):

		#####Break String into list	
		word=phrase.strip().split()
		return Counter(word)
