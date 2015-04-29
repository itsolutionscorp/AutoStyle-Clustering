from collections import Counter as cnt
def detect_anagrams(anagram, clist):
	flist = []
	anaCount = cnt(anagram.lower())
	for word in clist:
		p = cnt(word.lower())
		if (p - anaCount) == cnt("") and (anaCount - p) == cnt("") and anagram.lower() != word.lower():
			flist.append(word)
	return flist
