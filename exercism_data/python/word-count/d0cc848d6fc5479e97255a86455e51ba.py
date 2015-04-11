def word_count(sentence):
	splitSentence=sentence.split()
	words={}
	for word in splitSentence:
		if word in words:
			words[word]+=1
		else:
			words[word]=1
	return words
