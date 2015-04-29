def score(word):
	return sum(_iterscores(word))

def _iterscores(word):
	""" Generate score values for letters in `word`
	"""
	for letter in word.lower():
		yield scoretable.get(letter, 0)

def _transform(score_to_letters):
	""" Helper to init scoretable
	"""
	letter_to_score = {}
	for score, letters in score_to_letters.iteritems():
		letter_to_score.update({l.lower(): score for l in letters})
	return letter_to_score

scoretable = _transform({
	1: 'AEIOULNRST',
	2: 'DG',
	3: 'BCMP',
	4: 'FHVWY',
	5: 'K',
	8: 'JX',
	10: 'QZ',
})
