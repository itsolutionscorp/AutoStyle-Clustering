inv_scores = {
	1: 'AEIOULNRST',
	2: 'DG',
	3: 'BCMP',
	4: 'FHVWY',
	5: 'K',
	8: 'JX',
	10: 'QZ'
}

scores = {
	letter:key
	for key, val in inv_scores.items()
	for letter in val.lower()
}


def score(word):

	return sum(
		scores[letter]
		for letter in word.lower()
		if letter in scores
	)
