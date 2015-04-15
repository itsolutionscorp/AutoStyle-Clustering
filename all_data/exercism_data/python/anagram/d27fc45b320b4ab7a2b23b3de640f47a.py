def _matching_occurance(words):
	word1, word2 = words
	return all([word1.count(l) == word2.count(l) for l in frozenset(word1)])

def _matching_letters(words):
	word1, word2 = words
	return frozenset(word1) == frozenset(word2)

def _not_equal(words):
	word1, word2 = words
	return word1 != word2

def detect_anagrams(match_word, possibilities):
	passing = set([p.lower() for p in possibilities])
	test_list = zip(passing, ([match_word.lower()] * len(possibilities)))
	for filter_fn in (_not_equal, _matching_occurance, _matching_letters):
		passing = passing & set([x for x, y in filter(filter_fn, test_list)])

	# Resort list matches to pass the tests
	passing = [x for x in possibilities if x.lower() in passing]
	return passing
