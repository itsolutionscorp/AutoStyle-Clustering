# -*- coding: utf-8  -*-
import re

class Bob:
	i = 1
	def Bob(self):
		return 1	
	def hey(self,inString):
		# Question with a number
		if re.search('^\D+\d+\D*\?$',inString):
			return 'Sure.'
		# Question with a numbers only
		if re.search('^\d+\?$',inString):
			return 'Sure.'
		# Silence {prolonged}
		if re.search('^([ ]*)$',inString):
			return 'Fine. Be that way!'
		# Shouting (no !)
		if re.search('^[A-Z ,\']+$',inString):
			return 'Woah, chill out!'
		# Shouting (with !)
		if re.search('^[A-Z ,\']+\!',inString):
			return 'Woah, chill out!'
		# Shouting with special characters
		if re.search('^[A-Z\d\W]+\!$',inString):
			return 'Woah, chill out!'
		# Shouted question
		if re.search('^[A-Z\ ]+\?$',inString):
			return 'Woah, chill out!'
		# Shouting numbers
		if re.search('^[0-9A-Z\ \,]+[\!]$',inString):
			return 'Woah, chill out!'
		# Shouting
		if re.search('^[\D]+\!$',inString):
			return 'Whatever.'
		# Statement
		if re.search('[\w\-\ \,\']+[\!\.]$',inString):
			return 'Whatever.'
		# Question
		if re.search('^\D+\?$',inString):
			return 'Sure.'
		# Only numbers
		if re.search('[\d\ \,]+$',inString):
			return 'Whatever.'
		# check Unicode
		re.UNICODE()
		if re.search('[A-Z]+\!$',inString):
			return 'Woah, chill out!'
		if re.search('[a-z]+\.$',inString):
			return 'Whatever.'
