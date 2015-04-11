# /usr/bin/env python

def first(iterable):
	for elem in iterable:
		if elem:
			return elem
	return None

def is_silence(words):
	if not words or words.isspace():
		return "silence"

def is_shout(words):
	if words.isupper():
		return "shout"

def is_question(words):
	if words.endswith('?'):
		return "question"

def is_default(words):
	return "default"

class Bob(object):

	def __init__(self):
		self.name = "Bob"
		self.nature = "lackadaisical"
		self.response_map = {
			"silence" : "Fine. Be that way!",
			"shout"   : "Woah, chill out!",
			"question": "Sure.",
			"default" : "Whatever.",
		}
		self.request_ids = (
			is_silence,
			is_shout,
			is_question,
			is_default,
		)

	def hey(self, words):
		return first(self.responses(words))

	def responses(self, words):
		for _id in self.request_ids:
			yield self.response_map.get(_id(words))
