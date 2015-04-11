import re;
from collections import Counter;

def word_count(text):
	return Counter(re.split('[\\n\\t\\s]+', text));
