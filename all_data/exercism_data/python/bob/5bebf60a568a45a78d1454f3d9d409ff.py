# -*- coding: utf-8 -*-

class bob(object):

	"""Bob is a lackadaisical teenager. In conversation, his responses are very limited."""

	@staticmethod
	def hey(greeting):
		
		"""Respond to provided greeting."""

		if greeting.isspace() or greeting == '':
			return 'Fine. Be that way!'		# He says 'Fine. Be that way!' if you address him without actually saying anything.
		
		if greeting.isupper():
			return 'Whoa, chill out!'		# He answers 'Whoa, chill out!' if you yell at him.
		
		if greeting[-1:] == '?':
			return 'Sure.'					# Bob answers 'Sure.' if you ask him a question.

		return 'Whatever.'					# He answers 'Whatever.' to anything else.


# OTHER CONDITIONS CONSIDERED:

	# It seems that str.isupper() handles non-US upper case,
	# whereas I'd have to specify them all in regex:

		# elif re.match(r"[^a-z]+[A-Z][^a-z]+", greeting):
				# return 'Whoa, chill out!'
	
	# The test case does not consider the existince of an
	# exclamation mark at the end of a string shouting:

		# elif greeting[-1:] == '!':
			# return 'Whoa, chill out!'
		
	# Revision 1: Replaced with greeting.isspace() per
	# the advice of abl, found a 1/3 performance boost:

		# import re
		# if re.match(r"^\s*$", greeting):


# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that 
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,W293		- indentation contains tabs, blank line contains whitespace
		# max-line-length = 120	 

	# I didn't go overboard on the docstrings conventions given the simplicity of the code.


# ## Instructions

# Run the test file, and fix each of the errors in turn. When you get the
# first test to pass, go to the first pending or skipped test, and make
# that pass as well. When all of the tests are passing, feel free to
# submit.

# Remember that passing code is just the first step. The goal is to work
# towards a solution that is as readable and expressive as you can make
# it.

# Please make your solution as general as possible. Good code doesn't just
# pass the test suite, it works with any input that fits the
# specification.

# Have fun!
