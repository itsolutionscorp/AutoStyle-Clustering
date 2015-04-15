"""
Bob, the lackadaisical teenager.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

def hey(sentence):
	"""Returns Bob's response to a sentence."""
	if sentence.endswith("?") and not sentence.isupper():
		# A question, and not an angry one.
		retval = "Sure."
	elif sentence.isupper():
		# Uppercase is only used for shouting on the internet.
		retval = "Whoa, chill out!"
	elif not sentence.strip():
		# If what you address him with doesn't have any substance.
		retval = "Fine. Be that way!"
	else:
		# Everything else is all the same for teenagers.
		retval = "Whatever."
	return retval
