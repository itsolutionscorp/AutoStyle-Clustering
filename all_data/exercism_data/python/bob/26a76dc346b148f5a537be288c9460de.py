# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

	l = what.strip()

	if len(l) == 0:
		return 'Fine. Be that way!'
	elif l.isupper():
		return 'Whoa, chill out!'
	elif l.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'

# Once I found out about the str.isapper() method, I decided to completely
# scrap regex. As dgarver noted on the first iteration, hard coding chars
# is a bad idea. Specifying char ranges also wouldn't work because the
# it would be just way too many ranges. Unicode has great tools to look
# for char categories, but this functionality is not supported by re.
# It is supported by the regex module which will soon replave re.
