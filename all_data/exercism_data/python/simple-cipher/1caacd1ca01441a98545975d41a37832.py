import re
import random
import string

class Caesar:

	def __init__(self):
		self.shift = 3

	def encode(self, message):
		message = message.lower()
		message = re.sub("[^a-z]", "", message)
		caesar = ""
		for c in range(len(message)):
			caesar += chr((ord(message[c]) - 97 + self.shift) % 26 + 97)
		return(caesar)

	def decode(self, caesar):
		message = ""
		for c in range(len(caesar)):
			message += chr((ord(caesar[c]) - 97 - self.shift) % 26 + 97)
		return(message)

class Cipher:
	
	def __init__(self, key = None):
		if key == None:
			self.key = "".join( [random.choice(string.ascii_letters[:26] ) for i in range(100)])
		else:
			for i in range(len(key)):
				if key[i].isupper() or key[i].isdigit():
					raise ValueError("Illegal characters in key.")
			self.key = key

	def encode(self, message):
		message = message.lower()
		message = re.sub("[^a-z]", "", message)
		cipher = ""
		for c in range(len(message)):
			shift = self.getShift(c)
			cipher += chr((ord(message[c]) - 97 + shift) % 26 + 97)
		return(cipher)

	def decode(self, cipher):
		message = ""
		for c in range(len(cipher)):
			shift = self.getShift(c)
			message += chr((ord(cipher[c]) - 97 - shift) % 26 + 97)
		return(message)

	def getShift(self, index):
		return(ord(self.key[index % len(self.key)]) - 97)
