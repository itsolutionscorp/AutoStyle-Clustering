# -*- coding: utf-8 -*-
import string

def hey(sentence):
	sentence = sentence.encode("utf-8")
	phrase = Phrase(sentence)
	if phrase.isSilence():
		return "Fine. Be that way!"
	elif phrase.isQuestion():
		return "Sure."
	elif phrase.isShout():
		return 'Woah, chill out!'	
	else:
		return "Whatever."

class Phrase:
	def __init__(self, phrase):
		self.phrase = phrase

	def isQuestion(self):
		return self.phrase.endswith("?") and not self.isForceful()

	def isShout(self):
		if self.phrase.endswith("!"):
			if self.isForceful():
				return False
			else:
				if(self.calmUmlauts()):
					return False
				else:
					return True
		elif self.allCaps():
			return True
		else:
			if self.isForceful():
				return True
			else:
				return False


	def isSilence(self):
		return self.phrase.strip() == ""

	def isForceful(self):
		if self.phrase.find("Let's")>-1:
			return True
		if self.noLetters():
			return False
		if(self.phrase.endswith("?")):
			for char in self.phrase:
				if char in string.ascii_lowercase:
					return False
			return True
		return False

	def calmUmlauts(self):
		if self.allCaps():
			return False
		for char in self.phrase:
			if char in list(u"äëïöü".encode("utf-8")):
				return True
		return False

	def allCaps(self):
		capsset = list(string.ascii_uppercase.encode("utf-8") + u"ÄËÏÖÜ! ".encode("utf-8"))
		for char in self.phrase:
			print char
			if char not in capsset:
				return False
		return True

	def noLetters(self):
		for char in self.phrase:
			if char in list(string.ascii_letters):
				return False
		return True
