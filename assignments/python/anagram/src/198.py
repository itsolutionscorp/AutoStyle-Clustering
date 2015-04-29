def detect_anagrams(word, list):
	answer = []
	wordList = []
	for letter in word:
		wordList.append(letter.lower())
	wordList.sort()
	tempWord = ''.join(wordList)
	n = 0
	for item in list:
		itemList = []
		for letter in item:
			itemList.append(letter.lower())
		itemList.sort()
		tempItem = ''.join(itemList)
		if tempWord == tempItem and word != item:
			answer.append(list[n])
		n += 1
	return answer
