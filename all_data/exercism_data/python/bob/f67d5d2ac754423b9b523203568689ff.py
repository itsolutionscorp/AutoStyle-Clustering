#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper():
		return 'Whoa, chill out!'
	elif what.isspace() or what == "" :
		return 'Fine. Be that way!'
	elif what[len(what)-1] == '?':
		return 'Sure.'
	elif what[len(what)-1] == ' ':
		for i in range(len(what)-2, -1, -1):
			if what[i] == '?':
				return 'Sure.'
			elif what[i] != ' ':
				break
	else:
		return 'Whatever.'
