#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.rstrip() #removes trailing whitespace
	if what == '': #checks if the string is nothing
		return 'Fine. Be that way!'
	elif what.isupper():  #checks for yelling
		return 'Whoa, chill out!'
	elif what[-1] == '?': #checks for question
		return 'Sure.'
	else: #everthing else
		return 'Whatever.'
