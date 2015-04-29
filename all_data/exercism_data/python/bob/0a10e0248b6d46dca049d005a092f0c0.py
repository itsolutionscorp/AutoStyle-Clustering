#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#remove whitespace
	what = what.strip()
	#test for empty string.
	if what == '':
		return 'Fine. Be that way!'
	#test for uppercase to see if yelling
	if what.isupper():
		return 'Whoa, chill out!'
	#test for a question mark at the end
	if what[-1:] == '?':
		return 'Sure.'
	#responds to everything else
	else:
		return 'Whatever.'
