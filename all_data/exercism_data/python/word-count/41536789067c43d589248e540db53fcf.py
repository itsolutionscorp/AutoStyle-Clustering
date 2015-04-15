from collections import defaultdict
import re


def word_count(string):
	wc = defaultdict(int)
	# Remove special characters, normalize string and
	# split it into a list of words.
	words = re.sub('\W+', ' ', string).lower().split()

	for word in words:
		wc[word] += 1
	return wc
