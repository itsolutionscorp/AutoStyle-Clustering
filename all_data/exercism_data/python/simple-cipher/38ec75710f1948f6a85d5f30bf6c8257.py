import string
import random

class Caesar():
	
	def __init__(self):
		self.num = 0

	def encode(self, phrase):
		shift_place = 3
		return self.__change_phrase(phrase, shift_place)

	def decode(self, enc_phrase):
		shift_place = -3
		return self.__change_phrase(enc_phrase, shift_place)

	def __change_phrase(self, phrase, shift_place):
		punc = string.punctuation
		digits = string.digits
		new_phrase = phrase.lower().replace(' ', '')

		return ''.join([chr((ord(letter)+shift_place)%(ord('z')+1)) if ord(letter)+shift_place <= ord('z') else chr(ord('a')-1 + (ord(letter)+shift_place)%ord('z')) for letter in new_phrase if letter not in punc and letter not in digits])

class Cipher():

	def __init__(self, key = None):
		if not key:
			self.key = self.__random_key()
		else:
			for letter in key:
				if letter in string.digits or letter in string.ascii_uppercase:
					raise ValueError('No capital letters or numbers allowed in key.')

			self.key = key

	def encode(self, phrase):
		return self.__change_phrase(phrase, 'enc')

	def decode(self, phrase):
		return self.__change_phrase(phrase, 'dec')

	def __change_phrase(self, phrase, coding = 'enc'):
		shift_distance = 0
		len_key = len(self.key)
		new_phrase = phrase.lower().replace(' ', '')
		return_phrase = ''

		if coding== 'enc':
			parity = 1
		else:
			parity = -1

		ord_z = ord('z')
		ord_a = ord('a')

		for i in range(len(new_phrase)):
			if i >= len_key:
				shift_distance = parity*(ord(self.key[len_key - 1]) - ord('a'))
			else:
				shift_distance = parity*(ord(self.key[i]) - ord('a'))

			int_letter = ord(new_phrase[i])
			if int_letter + shift_distance > ord_z:
				return_phrase += chr((ord_a-1) + (int_letter+shift_distance)%ord_z)
			elif int_letter + shift_distance < ord_a:
				return_phrase += chr((ord_z+1) - ((ord_a)-(int_letter+shift_distance)))
			else:
				return_phrase += chr((ord(new_phrase[i]) + shift_distance))

		return return_phrase

	def __random_key(self):
		key_length = 100
		return ''.join(random.choice(string.ascii_lowercase) for i in range(key_length))

#print Caesar().encode('\'Twas the night before Christmas')
#print Caesar().decode('yhqlylglylfl')
#c = Cipher('dddddddddddddddddddddd')
#print c.encode('venividivici')
