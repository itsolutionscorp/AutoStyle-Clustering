def detect_anagrams(original, possibles):
	original_word=original
	results=[]
	for possible_word in possibles:
		if possible_word.upper()!=original_word.upper():
			if len(possible_word) == len(original_word):
				original_list=list(original_word.upper())
				for letter in possible_word.upper():
					if original_list != []:
						if letter in original_list:
							original_list.remove(letter)
						else: 
							next
				if original_list == []:
					results.append(possible_word)
	return results
