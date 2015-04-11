from __future__ import unicode_literals
class bob(object):
	def	hey(self, text):
		yelling = text.isupper()

		if not(text.strip()):
			return "Fine. Be that way!"
		elif text[len(text)-1] == "?" and not(yelling):
			return "Sure."	
		elif yelling:
			return "Woah, chill out!"
		else:
			return "Whatever."		
		
