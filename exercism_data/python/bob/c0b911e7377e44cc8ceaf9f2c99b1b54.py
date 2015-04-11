# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import unittest
import re

def hey(word):
	if word == '' or word.isspace():
		return 'Fine. Be that way!'
	elif "?" in word[-1]:
		return 'Sure.'
	elif re.sub(r'[{Alpha}]', '', word, flags=re.UNICODE).isupper():
		return 'Whoa, chill out!'			
	else:
		return 'Whatever.'
