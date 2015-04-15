
def word_count(phrase):
	ledger = dict()
	for word in phrase.split():
		key = word.strip()
		if key in ledger:
			ledger[key] += 1
		else:
			ledger[key] = 1
	return ledger
