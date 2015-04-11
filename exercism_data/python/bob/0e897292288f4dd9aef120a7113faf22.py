# Raymond Ho
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	# Empty input.
	if not what or what == '':
		return 'Fine. Be that way!'
	# Yell at Bob
	if what.isupper():
		return 'Whoa, chill out!'
	# Ask Bob a question.
	elif what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
