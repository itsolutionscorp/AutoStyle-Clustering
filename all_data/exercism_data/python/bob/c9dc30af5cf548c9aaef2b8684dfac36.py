#
# Skeleton file for the Python "Bob" exercise.
#
import string

def hey(what):
  # remove leading/trailing whitespace
	what_mod = string.strip(what)

	# if string is empty after removing whitespace
	if len(what_mod) < 1:
		return 'Fine. Be that way!'

	else:
		# allCaps tests whether the string is equal to its uppercase
		# equivalent. However, this test is insufficient, as a string with
		# all non-letter characters would return True in this case.
		# Therefore, we must also make sure that the string actually
		# contains capital letters.
		allCaps = unicode(what_mod) == unicode(what_mod).upper()
		oneCap = False # will change to true if capital letter is found
		if allCaps:
			# Go through each character and ensure presence of letters
		  for w in what_mod:
			  if w in string.uppercase:
				  oneCap = True
				  break
		if allCaps and oneCap:
			return 'Whoa, chill out!'
		elif what_mod[len(what_mod)-1] == '?': # last character is '?'
			return 'Sure.'
	return 'Whatever.'
