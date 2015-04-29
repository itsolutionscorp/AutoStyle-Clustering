def count_letters(word):
	res = {}

	for c in word:
		res[c] = res.get(c,0) + 1

	return res

def detect_anagrams(word, cands):
	word = word.lower()
	l = len(word)
	ll = count_letters(word.lower())

	res = []

	for c in cands:
		d = c.lower()
		if (len(c) == l) and (d != word) and (count_letters(d) == ll):
			res.append(c)

	return res


