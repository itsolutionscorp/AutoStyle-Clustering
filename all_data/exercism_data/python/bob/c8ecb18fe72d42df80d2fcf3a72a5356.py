#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.strip() == '': return 'Fine. Be that way!'
	if len([c for c in what if c.isupper()]) > 0 and len([c for c in what if c.islower()]) == 0: return 'Whoa, chill out!'
	if what.endswith('?'): return 'Sure.'
	return 'Whatever.'
