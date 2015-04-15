#
# Wordcount
#
def word_count(phrase):

	# Initialize
	dict = {}

	# Check string is different to nothing	
	phrase = phrase.strip()
	if phrase != "":

		# Making array 
		phrase = phrase.replace('\n',' ')
		phrase_array_all = phrase.split(' ')

		# Delete duplicates
		phrase_array = list(set(phrase_array_all))

		# Looking count the occurrences
		for word in phrase_array:
			
			if word.strip() != "":			
				# count ocurrences
				for word_checking in phrase_array_all:
					if word_checking.strip() == word:
						if word in dict.keys():
							dict[word] += 1
						else:
							dict[word] = 1

	return dict
