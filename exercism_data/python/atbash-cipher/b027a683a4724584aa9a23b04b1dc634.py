#/usr/bin/env python
from string import maketrans, translate, lowercase, punctuation, whitespace
def encode(words):
	trans = maketrans(lowercase, lowercase[::-1])
	result = translate(words.lower(), None, punctuation+whitespace)
	result = translate(result, trans)
	result = " ".join([result[i:i+5] for i in range(0,len(result),5)])
	return result
def decode(words):
	trans = maketrans(lowercase[::-1], lowercase)
	result = translate(words, None, whitespace)
	result = translate(result, trans)
	return result
