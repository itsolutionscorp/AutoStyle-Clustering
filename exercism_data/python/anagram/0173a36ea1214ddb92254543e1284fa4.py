from collections import Counter

def detect_anagrams(target, sourcelist):
	answer = []

	for word in sourcelist:

		if Counter(list(word.lower())) == Counter(list(target.lower())):

			if not word.lower() == target.lower():

				answer.append(word)

	return answer
