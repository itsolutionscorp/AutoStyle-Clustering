#/usr/bin/env python
def detect_anagrams(word,phrase):
	results = []
	count = {}
	for mj in range(len(word)):
		if word.upper()[mj] in count:
			count[word[mj].upper()] += 1
		else:
			count[word[mj].upper()] = 1
	for lm in phrase:
		if len(lm) == len(word) and lm.upper() != word.upper():
			altcount = {}
			for mj in range(len(lm)):
				if lm[mj].upper() in altcount:
					altcount[lm[mj].upper()] += 1
				else:
					altcount[lm[mj].upper()] = 1
			if altcount == count:
				results.append(lm)
	return results
