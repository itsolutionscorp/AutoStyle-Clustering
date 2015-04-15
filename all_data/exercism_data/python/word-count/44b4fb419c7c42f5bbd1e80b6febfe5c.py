def word_count(words):
	wordsSplit=words.split()
	results={}
	for word in wordsSplit:
		if word=='':
			next
		if word in results:
			results[word]+=1
		else:
			results[word]=1
	return results
	
