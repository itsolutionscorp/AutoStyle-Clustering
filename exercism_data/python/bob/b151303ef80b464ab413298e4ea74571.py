# -*- coding: utf-8 -*-
"""
Created on Sat Feb 22 16:35:41 2014

@author: hcabral
"""

class Bob:
	def __init__(self):
		pass
	
	def hey(self, msg):
		if msg.isupper():
			return 'Woah, chill out!'
		if msg.endswith('?'):
			return 'Sure.'
		if not msg.strip():
			return 'Fine. Be that way!'
		return 'Whatever.'
