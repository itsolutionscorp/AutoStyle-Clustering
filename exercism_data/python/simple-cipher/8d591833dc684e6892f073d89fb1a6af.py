from random import choice

letters = 'abcdefghijklmnopqrstuvwxyz'

class Caesar(object):

	def encode(self, secret):
		result = ''
		for letter in secret:
			if letter.lower().isalpha():
				result += letters[(letters.find(letter.lower())+3)%26]
		return result


	def decode(self, gibberish):
		result = ''
		for letter in gibberish:
			if letter.lower().isalpha():
				result += letters[(26+letters.find(letter.lower())-3)%26]
		return result


class Cipher(object):

	default = list(choice(letters) for x in range(0,100))
	randomKey = ''
	for letter in default:
		randomKey += letter

	def __init__(self, key = randomKey):
		if key.isalpha() and key.islower():
			self.key = key
		else:
			raise ArgumentError

	def encode(self, secret):
		result = ''
		i = 0
		for letter in secret:
			if letter.lower().isalpha():
				offset = letters.find(self.key[i%len(self.key)])
				result += letters[(letters.find(letter.lower())+offset)%26]
				i += 1
		return result


	def decode(self, gibberish):
		result = ''
		i = 0
		for letter in gibberish:
			if letter.lower().isalpha():
				offset = letters.find(self.key[i%len(self.key)])
				result += letters[(26+letters.find(letter.lower())-offset)%26]
				i += 1
		return result
