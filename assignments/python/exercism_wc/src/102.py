from collections import Counter
import re
def word_count(phrase):
	words = re.findall(r'[^\s]+', phrase)
	word_frequencies = {}
	for word in Counter(words).most_common():
		word_frequencies[word[0]] = word[1]
	return word_frequencies
