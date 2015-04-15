# wordcount
from collections import Counter

def word_count(string):
	
	x = filter(lambda d: d.isalnum() == False, string)
	f = filter(lambda c: len(c.strip(x)) > 0, string.split())
	words = map(lambda m: m.strip(x).lower(),f)
	cnt = Counter(words)

	return dict(cnt)	
