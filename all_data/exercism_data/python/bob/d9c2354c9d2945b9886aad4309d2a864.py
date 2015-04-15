#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	counter = 0
	for char in what:
		if char == ' ':
			counter += 1
        
	if what == '':
		return 'Fine. Be that way!'
	elif float(float(counter) / len(what) > 0.5):
		return 'Fine. Be that way!'
	elif what == what.upper() and what.lower() != what.upper():
		return 'Whoa, chill out!'
	elif what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
