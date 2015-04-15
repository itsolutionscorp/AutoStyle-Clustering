# transform storing string base of scrabble letter 
# scores into a dict set


def transform(old):
	return {
		char.lower(): scores
		for scores, alpha in old.items()
		for char in alpha
	}
