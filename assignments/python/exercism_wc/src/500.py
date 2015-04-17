def word_count(phrase):
	words=phrase.split()
	counts={}
	for key in words:
		if(key not in counts):
			counts[key]=1
		else:
			counts[key]+=1
	return counts
