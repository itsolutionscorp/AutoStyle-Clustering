#
# Skeleton file for the Python "Bob" exercise.
#
# -*- coding: utf-8 -*-
def hey(what):
	what = unicode(what)
	what = what.strip()


	umulaut = [u""]
	for n in range(192,246):
		umulaut.append((unichr(0 + n)))
	# print umulaut


# Question
	if what[-1::1] == "?" and\
	what.isupper() == False:
		return 'Sure.'
# Exclamation 
	elif what[-1::1] == "!" and\
	"Let's" not in what and\
	any(what[x] in umulaut for x in range(len(what))) != True or\
	what.isupper() == True:
		return 'Whoa, chill out!'
# Nothing
	elif len(what) == 0:
		return 'Fine. Be that way!'
# Everything else
	else:
		return 'Whatever.'

# print hey(raw_input('Say something to Paul: '))
