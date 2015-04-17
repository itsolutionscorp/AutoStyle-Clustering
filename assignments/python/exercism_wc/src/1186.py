from collections import Counter
def word_count(phrase):
		word=phrase.strip().split()
		return Counter(word)
