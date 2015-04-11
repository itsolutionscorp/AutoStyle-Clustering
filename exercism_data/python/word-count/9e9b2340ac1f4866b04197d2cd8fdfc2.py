#!/usr/bin/env python
# -*- coding: utf-8 -*-



def word_count(text):
	# import string constants
	import string
	
	# lowercase everything
	text = str.lower(text)

	# split the received words into a list
	wordlist = str.split(text)

	#initialize a dict and a new list
	dict = {}
	newlist = []
	
	# loop through list and write cleaned up words to a new list
	# create the dict and spit it out in the end
	for word in wordlist:
		word = word.strip(string.punctuation)
		if word:
			newlist.append(word)
			dict[word] = newlist.count(word)
	return dict	
