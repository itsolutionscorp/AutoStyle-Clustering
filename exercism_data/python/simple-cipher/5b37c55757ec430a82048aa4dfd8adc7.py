import string
import random

class Caesar:
	alphabet = 'abcdefghijklmnopqrstuvwxyz'
	cipher = 'defghijklmnopqrstuvwxyzabc'	
	encoding = string.maketrans (alphabet, cipher)
	decoding = string.maketrans (cipher, alphabet)
	def encode(self, message):	
		return message.lower().translate(self.encoding, string.punctuation + string.digits + string.whitespace)		
	def decode(self, message):	
		return message.lower().translate(self.decoding)

class Cipher:
	alphabet = string.ascii_lowercase
	def __init__(self, key=None):
		if key is None:
			self.key = ''.join(self.alphabet[random.randint(0, 25)] for i in range(0, 101))
		elif not key.islower() or not key.isalpha():
			raise ValueError("Incorrect cipher key!")		
		else:
			self.key = key
	def encode(self, message):
		#check for key shorter than message to be encoded
		#and pad with first key char
		#///any other suggestions appreciated
		if len(self.key) < len(message):
			self.key = self.key[0] * len(message)
		new_message = ''
		for pos, c in enumerate(message):			
			new_message += self.alphabet[(self.alphabet.index(c) + self.alphabet.index(self.key[pos])) % 26]
		return new_message
	def decode(self, message):
		new_message = ''
		for pos, c in enumerate(message):			
			new_message += self.alphabet[(self.alphabet.index(c) - self.alphabet.index(self.key[pos])) % 26]
		return new_message
