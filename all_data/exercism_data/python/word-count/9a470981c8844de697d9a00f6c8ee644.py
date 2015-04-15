''' word_count.py
	created 24 Sept 2014
	by @ jestuber
'''
import string

def word_count(phrase):
	#given a phrase, count the occurrences of each word in that phrase.
	wordcount = {} #dictionary of word=>count

	phrase = phrase.translate(None, string.punctuation) #remove punctuation
	phrase = phrase.lower() #ignore case
	words = phrase.split() #turn into list of words


	for word in words:
		if word in wordcount:
			wordcount[word] += 1
		else:
			wordcount[word] = 1


	return wordcount
