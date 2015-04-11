def detect_anagrams(word, anagrams):
#function takes in a word and a list of words
#and returns the correct anagram(s) from the list
#in a new list
	
	list_of_anagrams = []
	for possible in anagrams:
		
		#eliminate words which are obviously not anagrams
		if word.lower() == possible.lower() or len(word)!= len(possible):
			is_anagram = False
		else:
			is_anagram = True
			
		#get lower cases of the lists to ensure test is case insensitive
		lower_possible = possible.lower()
		lower_word = word.lower()
		
		for letter in lower_word:
			if lower_word.count(letter) != lower_possible.count(letter):
				is_anagram = False
				
		if is_anagram == True:
			list_of_anagrams.append(possible)
	
	
	return list_of_anagrams
