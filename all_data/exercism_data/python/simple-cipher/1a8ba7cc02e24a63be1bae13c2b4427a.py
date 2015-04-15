import random
from string import lower

alphabet={i:chr(i) for i in range(97,123)}
numbers={chr(i):i for i in range(97,123)}

class Caesar():
	# def __init__(self,*key):
		# if key:
			# self.key = key[0]
		# else:
			# rand = random.Random()
			# self.key = []
			# for i in range(150):
				# self.key.append(rand.choice(numbers.keys()))
	
	@staticmethod
	def encode(text):
		text = lower(text)
		output = ''
		keys = ['d']
		i = 0
		for letter in text:
			if 96 < ord(letter) < 123:
				offset = numbers[keys[i % len(keys)]] % 97
				charnum = ord(letter) + offset
				if charnum > 122:
					charnum -= 26
				transchar = alphabet[charnum]
				output += chr(charnum)
				i += 1
		return output
	
	@staticmethod
	def decode(text):
		text = lower(text)
		output = ''
		keys = ['d']
		i = 0
		for letter in text:
			if 96 < ord(letter) < 123:
				offset = numbers[keys[i % len(keys)]] % 97
				charnum = ord(letter) - offset
				if charnum < 97:
					charnum += 26
				transchar = alphabet[charnum]
				output += chr(charnum)
				i += 1
		return output


class Cipher():
	def __init__(self,*key):
		if key:
			self.key = key[0]
		else:
			rand = random.Random()
			self.key = []
			for i in range(150):
				self.key.append(rand.choice(numbers.keys()))
	
	@staticmethod
	def makeKey():
		rand = random.Random()
		key = []
		for i in range(150):
			key.append(rand.choice(numbers.keys()))
		return key
	
	def encode(self, text):
		text = lower(text)
		try:
			keys = self.key
		except NameError:
			keys = Cipher.makeKey()
		output = ''
		i = 0
		for letter in text:
			if 96 < ord(letter) < 123:
				offset = numbers[keys[i % len(keys)]] % 97
				charnum = ord(letter) + offset
				if charnum > 122:
					charnum -= 26
				transchar = alphabet[charnum]
				output += chr(charnum)
				i += 1
		return output
		
	def decode(self, text):
		text = lower(text)
		try:
			keys = self.key
		except NameError:
			keys = Cipher.makeKey()
		output = ''
		i = 0
		for letter in text:
			if 96 < ord(letter) < 123:
				offset = numbers[keys[i % len(keys)]] % 97
				charnum = ord(letter) - offset
				if charnum < 97:
					charnum += 26
				transchar = alphabet[charnum]
				output += chr(charnum)
				i += 1
		return output
