#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
	if all_caps_detected(what):
		return 'Whoa, chill out!'
	elif question_mark_detected(what):
		return 'Sure.'
	elif empty_sentence(what):
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'

def all_caps_detected(sentence):
	return bool(unicode.isupper(sentence))

def question_mark_detected(sentence):
	return bool(re.search('.*\?(\s+)?$', sentence))

def empty_sentence(sentence):
	return bool(re.search('^(\s+)?$', sentence))
