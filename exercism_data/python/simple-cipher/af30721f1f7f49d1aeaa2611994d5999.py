import random
from string import lower

alphabet={i:chr(i+97) for i in range(26)}
numbers={chr(i+97):i for i in range(26)}

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
			if letter in numbers.keys():
				offset = numbers[keys[i % len(keys)]]
				charnum = numbers[letter] + offset
				output += alphabet[charnum % 26]
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
			if letter in numbers.keys():
				offset = numbers[keys[i % len(keys)]]
				charnum = numbers[letter] - offset
				output += alphabet[charnum % 26]
				i += 1
		return output
		

class Caesar(Cipher):
	def __init__(self):
		Cipher.__init__(self,'d')

