def word_count(line):
	words = line.split()
	counters = {}
	for word in words:
		if word in counters:
			counters[word] = counters[word] + 1
		else:
			counters[word] = 1
	return counters
