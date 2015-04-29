# -*- coding: utf-8 -*-

class Bob:
	def hey(self, text):
		if text is None or len(text.strip()) == 0: return "Fine. Be that way."
		if text == text.upper(): return "Woah, chill out!"
		if text.endswith('?'): return "Sure."
		return "Whatever."
