#given a word, this program will return a list of angrams.


def detect_anagrams(target, words):
	canon = sorted(target.lower())
	return [word for word in words if sorted(word.lower()) == canon and target != word.lower()]

		
