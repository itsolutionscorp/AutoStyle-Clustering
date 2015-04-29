import string
from collections import Counter
def word_count(arg):
	return Counter(arg.lower().translate(None, string.punctuation).split())
