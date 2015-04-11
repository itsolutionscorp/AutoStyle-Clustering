#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#if what == 
	for i in range(0, len(what)):
		if what[i] == '?': 
			return "Sure."

	if (len(what) >= 0) & (what == what.upper()):
		return 'Whoa, chill out!'
	
	else:
		return 'Whatever.'
