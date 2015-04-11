import pdb

def word_count(phrase):
	#Create dictionary
	words_dict = {}
	
	#Get list of words, spaces are included
	words = [word for line in phrase.splitlines() for word in line.split()]
	for word in words:
		if word: #If not space
			if word in words_dict:
				words_dict[word] += 1
			else:
				words_dict[word] = 1
	return words_dict
