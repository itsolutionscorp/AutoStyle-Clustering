#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if not what or what.isspace():
		return 'Fine. Be that way!'
	else:
		stripped = what.strip()
		if stripped.isupper():
			return 'Whoa, chill out!'
		if stripped.endswith("?"):
			return 'Sure.'
	return 'Whatever.'
