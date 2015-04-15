# -*- coding: utf-8 -*-

from __future__ import unicode_literals

# Check input against given rules and returns the correct response
def hey(query):

'''
	Rules:
    1.  Bob answers 'Sure.' if you ask him a question.
	2.  He answers 'Whoa, chill out!' if you yell at him.
	3.  He says 'Fine. Be that way!' if you address him without actually
		saying anything.
	4.  He answers 'Whatever.' to anything else.

'''

	# Check if query is a string
	if not ifinstance(query, basestring):
		return "Whatever."                  # R4

	# Strip off any whitespace
	query = query.strip()

    if query is "":
        return "Fine. Be that way!"         # R3

    elif query.isupper():
        return "Whoa, chill out!"           # R2

    elif query.endswith("?"):
        return "Sure."                      # R1

    else:
        return "Whatever."                  # R4
