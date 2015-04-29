def detect_anagrams(wordRef, propositions):	
	result = []
	for maybe in propositions:
		isIt = True
		if ( len(maybe) == len(wordRef) and not maybe.lower() == wordRef.lower() ):
			for letter in maybe.lower():
				if not letter in wordRef.lower() or wordRef.lower().count(letter) != maybe.lower().count(letter):
					isIt = False
					break
			if isIt: result = result + [maybe]
	return result
