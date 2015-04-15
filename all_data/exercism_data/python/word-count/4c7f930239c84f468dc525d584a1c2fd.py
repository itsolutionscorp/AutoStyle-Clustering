''' word_count.py
	created 24 Sept 2014
	by @ jestuber
'''
from string import punctuation

def word_count(phrase):
	#given a phrase, count the occurrences of each word in that phrase.
	
	strip_punctuation = lambda s: s.strip(punctuation)
	
	phrase = phrase.lower() #ignore case
	words = phrase.split() #turn into list of words
	words = map(strip_punctuation, words) #remove punctuation
	words = filter(None, words) #remove ''

	wordcount = {} #dictionary of word=>count
	for word in words:
		if word in wordcount:
			wordcount[word] += 1
		else:
			wordcount[word] = 1


	return wordcount
