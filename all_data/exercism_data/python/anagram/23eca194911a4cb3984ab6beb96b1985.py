def detect_anagrams(what, wordlist):
	what = what.lower()
	return [word for word in wordlist if word.lower() != what 
			and sorted(what) == sorted(word.lower())]
