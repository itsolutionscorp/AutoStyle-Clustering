#-*- coding: utf-8 -*-
# M Lenox - Sept 2014 - First exercism.io python exercise
"""Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying
anything.
He answers 'Whatever.' to anything else."""
#http://www.tutorialspoint.com/python/python_strings.htm
#http://www.utf8-chartable.de/unicode-utf8-table.pl?names=-&utf8=dec&addlinks=1
#http://www.pythonforbeginners.com/basics/string-manipulation-in-python
#http://www.tutorialspoint.com/python/string_isspace.htm

import re

def hey(talk):
	test_energy =  re.search('\!$',talk) #$ anchors search to the end of line
	test_shout = talk.isupper()  #more versatile version of test_shout = re.search('[A-Z]{2}', talk)
	test_quest = re.search('\?$', talk)
	test_Bob1 = talk.strip(' \t') =='' #  space before \ important to get escape to work for some reason

	if test_quest and test_shout:
		return 'Whoa, chill out!'
	elif test_quest:
		return 'Sure.'
	elif test_shout:
		return 'Whoa, chill out!'
	elif test_Bob1 :
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
