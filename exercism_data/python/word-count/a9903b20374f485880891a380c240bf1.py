# this program takes a sentence as input and returns the word counts for each word

import re


def word_count(sentence):
	dirty_words = sentence.split()

	return count_words(dirty_words)

	
def clean(word):
	return re.sub("[\W]","",word).lower()
	
def count_words(words):
	cleaned_words = [clean(x) for x in words]
	counted_words = {}
	for word in cleaned_words:
		#empty string is falsy
		if word:
			try: 
				counted_words[word] = counted_words[word] + 1	
			except KeyError:
				counted_words[word] = 1

	return counted_words
