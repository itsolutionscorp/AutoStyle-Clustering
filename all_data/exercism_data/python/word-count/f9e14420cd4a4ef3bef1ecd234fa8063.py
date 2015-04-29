import re
from collections import defaultdict

def word_count(text):
	count = defaultdict(int)
	for word in text.split():
		count[word] += 1
	return count
