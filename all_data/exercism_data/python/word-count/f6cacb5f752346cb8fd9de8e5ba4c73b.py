import re

def word_count(phrase):
	counts = {}
	matches = re.findall('\S+', phrase)
	for match in matches:
		if match not in counts:
			counts[match] = 1
		else:
			counts[match] += 1

	return counts
