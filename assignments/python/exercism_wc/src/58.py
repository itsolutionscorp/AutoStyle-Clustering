from collections import Counter
import re
def word_count(text):
	text = re.sub(r'\W', ' ',text)
	text_list = text.lower().split()
	count = Counter(text_list)
	return count
