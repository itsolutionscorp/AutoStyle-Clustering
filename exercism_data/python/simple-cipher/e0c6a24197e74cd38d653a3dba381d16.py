import math
import re
from string import ascii_lowercase 

alphabet = list( ascii_lowercase )

def leave_only_letters(text):
	return re.sub(r'[^a-zA-Z]', '', text.lower())

class Caesar(object):
	def __helper(self, text, encode=True):
		result = []
		for l in leave_only_letters(text):
			if encode:
				result.append(alphabet[ (alphabet.index(l) + 3) % 26 ])
			else:
				result.append(alphabet[ (alphabet.index(l) - 3) % 26 ])
		return ''.join(result)

	def encode(self, text):
		return self.__helper(text)

	def decode(self, text):
		return self.__helper(text, False)

class Cipher(object):
	def __init__(self, key='a'):
		self.key = key
	
	def __helper(self, text, encode=True):
		result = []
		key = self.key
		if len(key) < len(text):
			multiplier = float(len(text)) / len(key) 
			key = key * int(math.ceil(multiplier))

		for x in range(len(text)):
			pos = alphabet.index(key[x])
			if encode:
				result.append(alphabet[ (alphabet.index(text[x]) + pos) % 26 ])
			else:
				result.append(alphabet[ (alphabet.index(text[x]) - pos) % 26 ])
		return ''.join(result)


	def encode(self, text):
		return self.__helper(text)

	def decode(self, text):
		return self.__helper(text, False)
