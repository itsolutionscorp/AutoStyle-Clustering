import string
from collections import Counter
def word_count(phrase):
	s = ''.join(ch for ch in phrase if ch not in set(string.punctuation)).lower().split()
	return dict(Counter(s))
