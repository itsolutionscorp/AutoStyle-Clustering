def word_count(line):
	wordcount={}
	line = line.translate(None, ",?.!/;:&@$%^&")
	for word in line.lower().split():
		if word not in wordcount:
			wordcount[word] = 1
		else:
			wordcount[word] += 1
	return wordcount