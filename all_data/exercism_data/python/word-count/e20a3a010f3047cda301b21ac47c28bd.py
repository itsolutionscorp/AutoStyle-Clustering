# Provide a string of text, return a dictonary with counts of the word

def word_count(sentence):
    # Make a list of words out of the sentence
	wordlist = sentence.split() 
	# Loop through the list and count the words. Use dict to constuct the dictonary
	counts = dict((word,wordlist.count(word)) for word in wordlist) 
	return counts
	
