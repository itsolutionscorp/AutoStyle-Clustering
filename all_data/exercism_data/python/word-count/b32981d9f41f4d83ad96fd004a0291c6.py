import string
import re

def word_count(sentence):
	splited_sentence = re.sub("[^\w:&@$%^&!]", " ",sentence).split()
	d = {}
	for i in splited_sentence:
		d[i] = splited_sentence.count(i)
	return d
