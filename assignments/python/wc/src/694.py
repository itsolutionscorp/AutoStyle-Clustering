from collections import Counter
import re
def word_count(phrase):
	rex = re.compile(r'\W+')
	result = rex.sub(' ', phrase).lower()
	return Counter(result.split())
