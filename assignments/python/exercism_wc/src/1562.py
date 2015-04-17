def word_count(sentence):
	allWords=sentence.split()
	counts={}
	for word in allWords:
		if word in counts:
			counts[word]+=1 
		else:
			counts[word]=1
	return counts
