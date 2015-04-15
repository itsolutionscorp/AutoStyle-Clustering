#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	yelling=False

	what = what.replace(' ','')
	what = what.replace('\t','')

	if len(what)==0:
		return 'Fine. Be that way!'

	#Check for lower case characters and characters other than spaces
	for letter in what:
		if letter.isupper():
			yelling=True
		elif letter.islower():
			yelling=False
			break

	if yelling:
		return 'Whoa, chill out!'
	elif what.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
