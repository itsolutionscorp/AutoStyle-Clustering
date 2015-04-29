#
# "Bob"
#

def hey(what):
	# yelling
	if what.isupper():
		return 'Whoa, chill out!'
	what = what.strip()
	# ...says nothing
	if what == '':
		return "Fine. Be that way!"
	# question
	if what[-1:] == '?':
		return 'Sure.'
	# statement
	return 'Whatever.'
