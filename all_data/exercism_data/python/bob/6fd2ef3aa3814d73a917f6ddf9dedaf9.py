#!/usr/bin/env python

class Bob:
	def hey(self, text):
		if (text.split() == []): return 'Fine. Be that way!'
		if (text == text.upper() and text != text.lower()): return 'Woah, chill out!'
		if (text[-1] == '?'): return 'Sure.'
		return 'Whatever.'
