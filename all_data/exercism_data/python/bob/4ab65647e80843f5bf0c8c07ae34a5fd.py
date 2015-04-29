#
# Python "Bob" exercise.
#
uppercase_letters = [chr(i) for i in range(65, 91)]
def hey(what):
	what = what.strip()	
	
	if len(what) == 0:
		return "Fine. Be that way!"
	
	if what == what.upper() and any(letter in uppercase_letters for letter in what):
		return "Whoa, chill out!"
	
	if what[-1] == '?':
		return "Sure."
	
	return "Whatever."
