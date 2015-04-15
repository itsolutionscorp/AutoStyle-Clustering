def sort(s):
	return "".join(sorted(s))

def detect_anagrams(word,wordlist):
	target = sort(word.lower())
	return [w for w in wordlist if sort(w.lower()) == target and w.lower() != word.lower()]
