import re, sys

l = list("abcdefghijklmnopqrstuvwxyz")
def decode(s):
	return ''.join([ l[25 - l.index(x)] for x in s.replace(" ", "").lower()])

def encode(s):
	c = ""
	sr = re.sub('[\.\s,;]*', '', s).lower()
	for i in sr:
		if i.isdigit():
			c += i
		else:
			c += l[25 - l.index(i)]
	c = ' '.join(c[i:i+5] for i in xrange(0, len(c), 5))

	return c
