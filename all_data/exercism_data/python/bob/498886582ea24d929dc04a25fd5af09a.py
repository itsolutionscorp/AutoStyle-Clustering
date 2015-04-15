#!/usr/bin/env python
# -*- coding: utf-8 -*-

class Bob(object):

	def hey(self, text):
		response = ''
		if not text.strip():
			return "Fine. Be that way!"
		elif text.isupper():
			return "Woah, chill out!"
		elif text.endswith('?'):
			return "Sure."
		else:
			return "Whatever."
