# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

import re

class bob(object):

	@staticmethod
	def hey(greeting):
		
		if re.match(r"^\s*$", greeting):
			return 'Fine. Be that way!'		# He says 'Fine. Be that way!' if you address him without actually saying anything.
		
		elif greeting.isupper():
			return 'Whoa, chill out!'		# He answers 'Whoa, chill out!' if you yell at him.
		
		elif greeting[-1:] == '?':
			return 'Sure.'					# Bob answers 'Sure.' if you ask him a question.

		return 'Whatever.'					# He answers 'Whatever.' to anything else.


## OTHER CONDITIONS CONSIDERED:

	# It seems that str.isupper() handles non-US upper case, 
	# whereas I'd have to specify them all in regex:

		# elif re.match(r"[^a-z]+[A-Z][^a-z]+", greeting):
				# return 'Whoa, chill out!'		
	
	# The test case does not consider the existince of an 
	# exclamation mark at the end of a string shouting:

		# elif greeting[-1:] == '!':		
			# return 'Whoa, chill out!'		
		



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
