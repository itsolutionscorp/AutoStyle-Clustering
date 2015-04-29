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

	def hey(self, words):
		if words.upper() == words and words.lower() != words:
			reply = "Woah, chill out!"
		elif words.endswith("?"):
			reply = "Sure."
		elif words.strip() == "":
			reply = "Fine. Be that way!"
		else:
			reply = "Whatever."
		return	reply
