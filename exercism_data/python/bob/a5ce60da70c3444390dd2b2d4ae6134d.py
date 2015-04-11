#!/usr/bin/env python
import re
import unicodedata

class Bob(object):

	def is_question(self, prompt):
		if self.is_shout(prompt):
			return False
		else:
			return prompt[-1] == "?"

	def is_blank(self, prompt):
		return prompt.strip() == ""

	def is_shout(self, prompt):
		if prompt[-2] == "!":
			return True
		contains_lowercase = len(re.findall("[a-z]", prompt)) > 0
		contains_uppercase = len(re.findall("[A-Z]", prompt)) > 0
		return contains_uppercase and not contains_lowercase


	def hey(self, prompt):
		if isinstance(prompt, unicode):
			prompt = unicodedata.normalize('NFKD', prompt).encode('ascii','ignore')
		if self.is_blank(prompt):
			return "Fine. Be that way!"
		elif self.is_question(prompt):
			return "Sure."
		elif self.is_shout(prompt):
			return "Woah, chill out!"
		else:
			return "Whatever."
