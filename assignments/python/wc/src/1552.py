from collections import Counter
import re
def word_count(phrase):
	rex = re.compile(r'\W+')
	return Counter(rex.sub(' ', phrase).lower().split())
