from collections import Counter
import string
def word_count(text):
	words = filter(lambda c: c not in string.punctuation,text).lower().split()
	cnt = Counter(words)
	return dict(cnt)	
