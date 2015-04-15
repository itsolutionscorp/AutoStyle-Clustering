def detect_anagrams(word, word_list):
	possible_anagram = ""
	for x in word_list:
		if len(x) == len(word):
			no_anagram = False
			for character in x:
				word_case_check = word.upper()
				x_case_check = x.upper()
				character_case_check = character.upper()
				if word_case_check.count(character_case_check) != x_case_check.count(character_case_check):
					no_anagram = True	
				if character.upper() not in word.upper():
					no_anagram = True	
				if word_case_check == x_case_check:
					no_anagram = True		
			if no_anagram == False:
				possible_anagram += x + " "
			no_anagram = False	
		else:
			pass	
			no_anagram = False
	return possible_anagram.split()


	
