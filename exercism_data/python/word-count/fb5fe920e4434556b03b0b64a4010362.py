#!/usr/bin/python

def word_count(phrase):
	
	shred = phrase.split()
	
	counts = {}
	
	for word in shred:
	  if not word in counts:
	    counts[word] = 1
	  else:
	    counts[word] = counts[word] + 1
	    
	return counts
