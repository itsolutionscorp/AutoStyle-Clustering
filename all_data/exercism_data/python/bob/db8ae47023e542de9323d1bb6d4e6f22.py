import string
import re

'''
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else.
'''
class Bob:
	def __init__(self): pass
	def hey(self, text):
		if all( x==y.upper() for x,y in zip(text, text)) and any( l.isalpha() for l in text): return "Woah, chill out!"
		elif all( l in string.whitespace for l in text): return "Fine. Be that way!"
		elif text[-1] == "?": return "Sure."
		else: return "Whatever."
