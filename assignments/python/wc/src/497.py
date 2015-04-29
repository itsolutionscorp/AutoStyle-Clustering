import re
from collections import Counter
def word_count(phrase):
	return Counter(re.findall('[a-zA-Z0-9]+', phrase.lower()))
