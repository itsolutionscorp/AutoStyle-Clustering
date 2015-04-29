from collections import Counter

def word_count(self):
	c = Counter(self.split())
	return c
