def detect_anagrams(word, options):
	results = []
	wordCount = letter_count(word.lower())
	for test in options:
		testCount = letter_count(test.lower())
		if wordCount == testCount and word.lower() != test.lower():
			results.append(test)
	return results

def letter_count(palabra):
	count = {}
	for each in palabra:
		if each in count.keys():
			count[each] += 1
		else:
			count[each] = 1
	return count
