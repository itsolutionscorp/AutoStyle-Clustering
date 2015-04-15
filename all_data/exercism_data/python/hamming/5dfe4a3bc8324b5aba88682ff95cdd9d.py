def distance(strand1, strand2):
	score = 0
	pairs = zip(strand1, strand2)
	for p in pairs:
		if p[0] != p[1]:
			score += 1
	return score
