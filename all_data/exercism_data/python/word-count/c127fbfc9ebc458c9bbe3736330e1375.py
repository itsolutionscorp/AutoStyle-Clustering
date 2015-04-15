from collections import defaultdict
import re

def word_count(sentence):
	if len(sentence)<1:
		return {0}

	else:
		dct = defaultdict(int)
		# sentence = sentence.split(" ")
		sentence = re.split('\n| *',sentence)
		for eachword in sentence:
			dct[eachword] += 1

		return dict(dct)
