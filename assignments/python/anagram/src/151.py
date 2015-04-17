def detect_anagrams(what, wordlist):
	what = what.lower()
	return [word for word in wordlist if sorted(what) == sorted(word.lower()) and word.lower() != what]
