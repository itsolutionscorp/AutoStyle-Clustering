def detect_anagrams(x,y):
	word = 0
	big_bin = []
	for c in y:
		word = list(c.lower())
		bin = 0
		for i in x.lower():
			for b in range(len(word)):
				if i == word[b]:
					word.pop(b)
					bin += 1
					break
		if bin == len(x) and len(c) == len(x) and c.lower() != x.lower() and not word:
			big_bin.append(c)
	return big_bin
