#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	def isQuestion():
		if what.endswith('?'):
			nonlocal result
			result = 'Sure.'
	def isYelling():
		if what.isupper():
			nonlocal result
			result = 'Whoa, chill out!'
	def isSayingAnything():
		if what.isspace() or (not what):
			nonlocal result
			result = 'Fine. Be that way!'
	result = 'Whatever.'
	what = what.strip()
	isQuestion()
	isYelling()
	isSayingAnything()
	return result
