#
# Skeleton file for the Python "Bob" exercise.
#

from __future__ import unicode_literals

def hey(w):
	w = parse(w)

	a = None

	if is_empty(w):
		a = answer_empty()
	elif is_yelling(w):
		a = answer_yelling()
	elif is_question(w):
		a = answer_question()

	if not a:
		a = answer_anything_else()
	return a

def parse(w):
	return w.strip()

'''
Boolean tests so inputs can be cateogirzed

'''

def is_empty(w):
	# Is empty if there is nothing to say after all whitespace is stripped away

	return len(w) == 0

def is_yelling(w):
	# Can ONLY be yelling if inputs without chars that have 'uppercase' state 
	# Inputs that end in an exclamation point and all caps OR
	# If input is in all caps

	numerical = w.lower() == w.upper()
	all_caps = w.upper() == unicode(w)
	exclamation_with_caps = ('!' == w[-1] and w.upper() == unicode(w))
	return (not numerical) and (all_caps or exclamation_with_caps)

def is_question(w):
	# Is a question if the question mark is at the end

	return '?' == w[-1]

'''
Answers to inputs

'''

global a_yelling, a_empty, a_question, a_anything_else
a_yelling, a_empty, a_question, a_anything_else = (
						'Whoa, chill out!', \
						'Fine. Be that way!', \
						'Sure.', \
						'Whatever.'
					)

def answer_question():
	return a_question

def answer_yelling():
	return a_yelling

def answer_empty():
	return a_empty

def answer_anything_else():
	return a_anything_else
