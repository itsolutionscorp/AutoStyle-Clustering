import collections
import re

def word_count(phrase):
	print re.findall('\w+', phrase)
	words = re.findall('\w+', repr(phrase))
	print words
	c = collections.Counter()
	phrase = phrase.replace("\n", " ")
	lista = phrase.split(" ")
	c.update(lista)
	print c
	if c[''] > 0:
		del c['']
	return c
