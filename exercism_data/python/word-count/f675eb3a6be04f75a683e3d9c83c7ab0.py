import re
from collections import defaultdict

def word_count(text):
	count = defaultdict(int)
	for word in re.split('\n|\s+', text):
		count[word] += 1
	return count
