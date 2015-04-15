def word_count(phrase):
	counts={}
	for key in phrase.split():
		if(key not in counts):
			counts[key]=1
		else:
			counts[key]+=1
	return counts
