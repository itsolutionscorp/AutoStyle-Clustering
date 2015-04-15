from random import randint

class Cipher(object):

	def __init__(self, *key):
		if key:
			key = key[0]
			if key == key.lower() and key.isalpha():
				self.key = key
			else:
				raise ArgumentError("Your input key should be a string containing only lowercase letters.")
		else:
			self.key = ""
			for i in range(100):
				self.key += chr(randint(ord("a"),ord("z")))

	def encode(self, string):
		enc_string = ""
		for i in xrange(len(string)):
			char = string[i].lower()
			i = i % len(self.key)
			if char.isalpha():
				enc_string += chr(((ord(char) + (ord(self.key[i]) - ord("a")) - ord("a")) % (ord("z") + 1 - ord("a"))) + ord("a"))
		return enc_string

	def decode(self, string):
		dec_string = ""
		for i in xrange(len(string)):
			char = string[i].lower()
			i = i % len(self.key)
			if char.isalpha():
				dec_string += chr(((ord(char) - (ord(self.key[i]) - ord("a")) - ord("a")) % (ord("z") + 1 - ord("a"))) + ord("a"))
		return dec_string

class Caesar(Cipher):

	def __init__(self):
		super(self.__class__, self).__init__("d")
