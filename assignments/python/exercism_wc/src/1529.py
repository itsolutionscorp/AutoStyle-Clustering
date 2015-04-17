import re
def word_count(str):
	wordcount={}
	for word in str.split():
		word=re.sub("[^A-Za-z0-9]", "", word)
		word=word.lower()
		if word == '':
			continue	
		elif word not in wordcount:
			wordcount[word]=1
		else:
			wordcount[word]+=1
	return wordcount
