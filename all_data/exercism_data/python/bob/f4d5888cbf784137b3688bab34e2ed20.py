#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (what == None or what == '' or what.isspace()):
	return 'Fine. Be that way!'
    if what.isupper() or (what.endswith("?") & what.isupper()):
	return 'Whoa, chill out!'
    if what.strip().endswith("?"):
	return 'Sure.'
    return 'Whatever.'
