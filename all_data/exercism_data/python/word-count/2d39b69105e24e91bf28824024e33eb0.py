def word_count(words):
	words = words.strip().replace('\n',' ').split(' ')
	words = filter(len,words)
	counts = {}
	for word in words:
		if word == '': break #dont include spaces as words (when there are multiple adjacent spaces)
		elif word not in counts.keys():
			counts[word] = 1
		else:
			counts[word] +=1
	return counts
