#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if what != what.swapcase(): #are there characters that can change case
		if what == what.upper(): # check if all are upper case already
			return 'Whoa, chill out!'
	if what[-1:] == '?':
		return 'Sure.'
	if what == '':
		return 'Fine. Be that way!'
	return 'Whatever.'
	return
