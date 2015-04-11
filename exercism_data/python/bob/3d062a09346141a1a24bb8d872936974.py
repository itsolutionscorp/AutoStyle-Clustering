#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	
	s = what.strip()
	if s  == '':
		return 'Fine. Be that way!'

	elif s == s.upper():

		if s.lstrip('0123456789!?.,<> ') != '':
			return 'Whoa, chill out!'
	
	if what[-1] == '?':
		return 'Sure.'






	
	return 'Whatever.'
