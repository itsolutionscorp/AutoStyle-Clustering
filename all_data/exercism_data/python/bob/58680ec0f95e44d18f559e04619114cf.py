# /usr/bin/env python


class Bob(object):
	"""
	I suspect that the most elegant solution will be a regex,
	but this seems to be a reasonable start.

	Better might be a sequence of predicates so extra responses can be easily sorted.
	These predicates could be simple ifs or a regex
	"""

	def __init__(self):
		self.name = "Bob"
		self.nature = "lackadaisical"

	def hey(self, words):
		if self._is_silence(words):
			reply = "Fine. Be that way!"
		elif self._is_shouting(words):
			reply = "Woah, chill out!"
		elif self._is_question(words):
			reply = "Sure."
		else:
			reply = "Whatever."
		return	reply

	def _is_silence(self, words):
		return not words or words.isspace()

	def _is_shouting(self, words):
		return words.isupper()

	def _is_question(self, words):
		return words.endswith('?')
