#bob.py

import string
import re

	#@classmethod
def hey(sentence):
	#interpreter = SentenceInterpreter(sentence)
	#print sentence
	if sentence == ''or re.match("\s+\\t",sentence):
		return 'Fine. Be that way!'

	elif sentence.isupper():
		return 'Woah, chill out!'

	elif sentence.endswith('?') :
		return 'Sure.'
	else:
		return 'Whatever.'
