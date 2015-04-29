#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	answer = ""
	caps = False
	question = False
	onlyWhite = True
	for x in range(0, len(what)):
		if what[x].isalpha() and what[x].upper() == what[x]:
			caps = True
		if what[x] != " " and what[x] != '\t':
			onlyWhite = False
	if len(what) < 1 or onlyWhite:
		answer = 'Fine. Be that way!'
		return	answer
	else:
		for x in range(0, len(what)):
			if what[x].isalpha != True or what[x] == '?':
				if what[x] == '?':
					question = True
				elif what[x].isalpha():
					question = False
				if what[x].isalpha() and what[x].upper() != what[x]:
					caps = False
	if (caps):
		answer = 'Whoa, chill out!'
	elif (question):
		answer = 'Sure.'
	else:
		answer = 'Whatever.'
	return answer
