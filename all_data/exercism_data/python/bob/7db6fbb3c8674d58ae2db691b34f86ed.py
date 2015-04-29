# bob.py
# Aman Ali
"""
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else.
"""
class Bob:
	def __init__(self):
		pass

	def caps(self,s):
		""" Determine if all caps """
		alpha = False
		for x in s:
			if x.isalpha():
				alpha = True
				if not x.isupper():
					return False
		return alpha

	def hey(self,s):
		""" Main speak function """
		# Check Empty
		if len(s.split()) == 0:
			return 'Fine. Be that way!'
		# Check CAPS
		if self.caps(s):
			return 'Woah, chill out!'
		# Check Question
		if s and s[-1] == '?':
			return 'Sure.'
		# All other cases
		return 'Whatever.'
