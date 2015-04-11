#!/usr/bin/env python
# -*- coding: utf-8 -*-

from string import punctuation as punct

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		result = {}
		words = filter(None, filter(lambda x: x not in punct, self.phrase.lower()).split(' '))
		for word in words:
			if not word in result:
				result[word] = words.count(word)
		return result
