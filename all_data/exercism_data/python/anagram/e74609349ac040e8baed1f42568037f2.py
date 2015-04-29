def detect_anagrams(word, wlist):
	toReturn = []

	word = word.lower()

	for i in wlist:
		if i == word:
			break

		current = i.lower()

		for j in word:
			if j in current:
				current = current[:current.find(j)] + current[(current.find(j)+1):]
			else:
				break

		if current == "":
			toReturn.append(i)

	return toReturn
