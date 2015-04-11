# -*- coding: utf-8 -*-

import re

# 3 cases:
# 1. Question if ending by a "?"
# 2. Full caps, at least one letter. Take into account special characters too
# 3. Blank if no character/letter/special characters.
# 4. Else

# Dictionnary: sentence_type -> answer
answers = {
	'question': 'Sure.',
	'yell': 'Whoa, chill out!',
	'nothing': 'Fine. Be that way!',
	'anything_else': 'Whatever.'
}

def hey(sentence):
	sentence_type = analyze(sentence)
	return respond(sentence_type)

def analyze(sentence):

	if re.match(r'.*\?$', sentence):
		sentence_type = 'question'
	elif sentence.replace(' ', '').replace('\t', '') == '':
		sentence_type = 'nothing'
	# else if re.match("^[\p{Lu}]$", sentence):
		# sentence_type = 'yell'
	else:
		sentence_type = 'anything_else'

	return sentence_type

def respond(sentence_type):
	return answers[sentence_type]
