#
# Skeleton file for the Python "Bob" exercise.
# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import unittest


class ChatResponse:
	def __init__(self):
		self.text = {}

	def buildResponse(self, responses={}):
		self.text = responses

	def addResponseLine(self, response_text, response_key):
		if response_key in self.text and \
			response_text not in self.text[response_key]:
				self.text[response_key].append(response_text)

		if response_key not in self.text:
			self.text[response_key] = []
			self.text[response_key].append(response_text)

	def hey(self, text):
		for key, val in self.text.iteritems():
			if text in val:
				return key
