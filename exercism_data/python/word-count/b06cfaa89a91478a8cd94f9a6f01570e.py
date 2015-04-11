import re

def word_count(phrase):
	wordCount = {}
	phrase = re.sub(r'(\S)\s+(\S)',r'\1 \2',phrase)
	for word in phrase.split(' '):
		if word in wordCount: 
			wordCount[word] += 1
		else:
			wordCount[word] = 1

	if '' in wordCount: wordCount.pop('')	# Handle multiple spaces; more efficient at lower concentrations

	return wordCount


# Word Count #

# Write a program that given a phrase can count the occurrences of each word in that phrase.

# For example for the input `"olly olly in come free"`
	#	olly: 2
	#	in: 1
	#	come: 1
	#	free: 1
