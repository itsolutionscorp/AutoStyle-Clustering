#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	allCaps = what.isupper()
	lastPunc = ''
	if what.isspace() or what == '':
		return 'Fine. Be that way!'
	for i in what:
		if i == '.' or i == '?' or i == '!':
			lastPunc = i
	if allCaps: 
		return 'Whoa, chill out!'
	elif lastPunc == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
