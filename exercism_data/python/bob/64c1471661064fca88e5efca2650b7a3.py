# -*- coding: utf-8 -*

def hey(x):
	x = x.strip()
	l = len(x)
	if l == 0:
		return 'Fine. Be that way!'
	if x.isupper():
		return 'Whoa, chill out!'
	if x[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
