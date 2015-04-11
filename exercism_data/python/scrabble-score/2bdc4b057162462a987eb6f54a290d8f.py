def score(word, letterscore=1, wordscore=1):
	word = ''.join([x.lower() for x in word if x.isalpha()])
	score = 0
	for letter in word:
		if letter in 'aeioulnrst':
			result=1
		if letter in 'dg':
			result=2
		if letter in 'bcmp':
			result=3
		if letter in 'fhvwy':
			result=4
		if letter in 'k':
			result=5
		if letter in 'jx':
			result=8
		if letter in 'qz':
			result=10
		result=result*letterscore
		score += result
	score = score * wordscore
	return score
