def word_count(entry):
	words=entry.split()
	countwords=[]
	wordcount={}
	for word in words:
		if(word not in countwords):
			countwords.append(word)
			wordcount[word]=words.count(word)
	return wordcount
