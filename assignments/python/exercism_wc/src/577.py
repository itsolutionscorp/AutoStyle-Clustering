from collections import Counter
import re
import string
punctuation = re.compile('[%s]' % re.escape(string.punctuation))
def word_count(phrase):
	phrase = punctuation.sub('', phrase)
	counter = Counter(phrase.lower().split(' '))
	del counter['']
	return counter
