# -*- coding: utf-8 -*-
import re

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if len(what) == 0:
		return "Fine. Be that way!"
	else:
		contains_lowercase_letter = re.compile('[a-z]+').search(what) is not None
		all_letters_are_capitalized = not contains_lowercase_letter and re.compile('[A-Z]+').search(what) is not None
		ends_with_question_mark = what[-1] == '?'

		if all_letters_are_capitalized and u'ä' not in what:
			return "Whoa, chill out!"
		elif not all_letters_are_capitalized and ends_with_question_mark:
			return "Sure."
		else:
			return "Whatever."
