# anagram.py

def detect_anagrams(word, possible):
	returns=[]
	word=word.lower()
	for anagram in possible:
		aTemp=anagram.lower()
		if aTemp == word:
			continue
		found = True
		for letter in word:
			if letter in aTemp:
				aTemp = aTemp.replace(letter,'',1)
			else:
				found = False
				break
		if found and len(aTemp) == 0:
			returns.append(anagram)
	return returns
