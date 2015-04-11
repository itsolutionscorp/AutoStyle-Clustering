def checkanagram(word1,word2):
	if word1.lower() == word2.lower():
		return False
	else:
		return sorted(word1.lower()) == sorted(word2.lower())

def detect_anagrams(word,anagrams):
	return [entry for entry in anagrams if checkanagram(word,entry)]
