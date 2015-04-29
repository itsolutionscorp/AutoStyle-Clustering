# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import logging
import re

# Skeleton file for the Python "Bob" exercise.
#
reply={1:'Sure.', 2:'Fine. Be that way!', 3:'Whatever.', 4:'Whoa, chill out!'}


	# Used Regex for most of the tests. Since Bob's default response is 'Whatever'
	# I allowed some tests to fall-through to the bottom where an else always returns
	# whatever. This saves a little compute time and coding rather than coding
	# for every single repsonse.
	
	# I also ordered these in such a way that some test cases will hit immediately,rather than
	# falling further down the list. This was a concious decision. I could have coded each case
	# explicitly, but for some reason I went this route. I could probably change the few cases
	# on my next iteration.
	
	# I also added comments inside the if loop. I was planning to add them before the loops
	# but I decided to add them inside as I had debug statements throuhgout, and i just replaced
	# the dubs lines with comment code.
	
def hey(what):
	if re.match(r"([ÄÜ]+.*\!$)",what):
		#  'umlaut shouting,' + what
		return reply[4]
	if what=='':
		#  'empty string'
		return reply[2]
	if re.match(r"(.*[\!]+.*[\.]+.*[\?]+)", what):
		#  'prattling'
		return reply[1]
	if re.match(r"(.*[\d]+.*[A-Z]+.*!$)", what):
		#  'numbers, caps, and exclamtion, oh my.'
		return reply[4]
	if re.match(r"(.*[\^\*@]+.*!$)", what):
		#  'special char w !'
		return reply[4]
	if re.match(r"([A-Z ]*!$)", what):
		#  'caps and !'
		return reply[4]
	if re.match(r"([A-Z ]*\?$)", what):
		#  'caps and ?'
		return reply[4]
	if re.match(r"([ ,\d]+$)",what):
		#  'digits only'
		return reply[3]
	if re.match(r"([\d]+\?$)", what):
		#  'digits+?'
		return reply[1]
	if re.match(r"(.*!$)", what):
		#  'ends in exclamation'
		return reply[3]
	if re.match(r"(.*\.$)", what):
		#  'ends in .'
		return reply[3]
	if re.match(r"(.*\?)", what):
		#  'ends in ? even with added whitespace'
		return reply[1]
	if re.match(r"([ A-Z]*$)", what):
		#  'only letters'
		return reply[4]
	if re.match(r"([\s])", what):
		#  'silence'
		return reply[2]
	else:
		#  'default reply of "whatever"'
		return reply[3]
	
