from collections import Counter
import re
def word_count(string):
	string = re.sub("[^\w]", " ", string).lower()
	phrase = Counter(string.split())
	return dict(phrase)
