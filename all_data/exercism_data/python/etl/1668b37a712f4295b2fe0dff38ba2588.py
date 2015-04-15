def transform(score_to_letters):
	letter_to_score = {}
	for score, letters in score_to_letters.iteritems():
		letter_to_score.update({l.lower(): score for l in letters})
	return letter_to_score
