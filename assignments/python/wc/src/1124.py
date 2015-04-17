import re
def word_count(phrase):
	answer = dict()
	for words in re.findall(r"[\w']+", phrase.lower()):
		if words in answer:
			answer[words] += 1
		else:
			answer[words] = 1
	return answer
