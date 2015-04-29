from collections import Counter
import re

def word_count(text):
	text_list = re.sub(r'\W', ' ',text.lower()).split()
	count = Counter(text_list)
	return count
