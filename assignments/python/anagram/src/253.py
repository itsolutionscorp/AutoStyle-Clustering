def detect_anagrams(origin, possible):
	answer = []
	letter_match_count = 0
	for word in possible:
		if origin.upper() == word.upper() or word.find(origin) != -1 or len(word) != len(origin):
			pass
		else:
			letter_match_count=0
			for letter in origin:
				if word.lower().count(letter.lower()) == origin.lower().count(letter.lower()):
					letter_match_count+=1
				else:
					break
			if letter_match_count == len(word):
				answer.append(word)
	return answer
