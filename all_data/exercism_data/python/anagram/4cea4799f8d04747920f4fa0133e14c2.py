def is_anagram_of(word, cwrd, swrd):
	caps = word.upper()
	return cwrd != caps and swrd == sorted(caps)

def detect_anagrams(word, words):
	cwrd = word.upper()
	swrd = sorted(cwrd)
	return list(filter(lambda s: is_anagram_of(s, cwrd, swrd), words))
