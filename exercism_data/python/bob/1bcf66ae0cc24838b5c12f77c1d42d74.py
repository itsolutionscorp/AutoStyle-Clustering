#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if not what:
		return 'Fine. Be that way!'
	chars = [x for x in what if x.isalpha()]
	if len(chars) and sum(1 for x in chars if x.isupper()) == len(chars):
		return 'Whoa, chill out!'
	if what[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
