import re
from collections import Counter
def word_count(text):
	return Counter(re.split(r'[\n\t\s]+', text))
