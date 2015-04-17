from collections import Counter
import re
def word_count(text):
	return Counter(re.sub(r'\W', ' ',text.lower()).split())
