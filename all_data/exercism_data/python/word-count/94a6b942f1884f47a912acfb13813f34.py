###################################
# Function counts the words in sentence
# inputs: string
# returns: dictionary of each individual word and the
#		number of times it appears in the sentence
def word_count(phrase):
	#create list from sentence
	phrase = phrase.split()
	#create dictionary for counting words
	count = {} 	
	
	for i in phrase:
		#increase word count if word has already appeared
		if i in count:
			count[i] += 1
		#add a word if it is not in the dictionary
		else:
			count[i] = 1
	
	return count
