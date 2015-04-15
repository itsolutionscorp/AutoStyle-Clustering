class Cipher:

	def __init__(self, cipher = "d"):
		self.cipher = cipher

	def encode(self, word):
		word = word.lower()
		alphabet = "abcdefghijklmnopqrstuvwxyz"
		encoded =""
		count = 0
		for letter in word:
			position = count % len(self.cipher)
			offset = alphabet.find(self.cipher[position])
			if alphabet.find(letter) > -1:
				newlettervalue = (alphabet.find(letter) + offset) % 26
				encoded += alphabet[newlettervalue]
			count += 1
		return encoded

	def decode(self, word):
		word = word.lower()
		alphabet = "abcdefghijklmnopqrstuvwxyz"
		decoded =""
		count = 0
		for letter in word:
			position = count % len(self.cipher)
			offset = alphabet.find(self.cipher[position])
			if alphabet.find(letter) > -1:
				newlettervalue = (alphabet.find(letter) - offset) % 26
				decoded += alphabet[newlettervalue]
			count += 1
		return decoded

class Caesar(Cipher):
	def __init__(self, cipher = "d"):
		self.cipher = cipher
