#!/usr/bin/python -tt

def word_count(sentence):
	count={}
	
	for w in sentence.split():
		if w in count:
			count[w]+=1
		else:
			count[w]=1
			
	return count
