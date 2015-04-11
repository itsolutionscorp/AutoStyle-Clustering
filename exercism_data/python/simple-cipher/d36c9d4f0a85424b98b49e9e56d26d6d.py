from random import choice
from itertools import repeat, chain

# a Vigenere cipher
class Cipher(object):
	key_length=128
	_alphabet="abcdefghijklmnopqrstuvwxyz"

	def __init__(self,key=None):
		if key==None:
			self._key=''.join([ choice(Cipher._alphabet) for i in range(Cipher.key_length) ])
		else:
			self._key=key

	@property
	def key(self):
		return self._key

	# adds two characters according to their position in the class's _alphabet
	@classmethod
	def _add_characters(cls,a,b):
		return cls._alphabet[(cls._alphabet.find(a)+cls._alphabet.find(b))%len(cls._alphabet)]

	@classmethod
	def _subtract_characters(cls,a,b):
		return cls._alphabet[(cls._alphabet.find(a)-cls._alphabet.find(b))%len(cls._alphabet)]

	def encode(self,plaintext):
		answer=''
		key_characters=chain.from_iterable(repeat(self.key))
		for c in plaintext.lower():
			if c in Cipher._alphabet:
				answer+=Cipher._add_characters(c,next(key_characters))
		return answer

	def decode(self,ciphertext):
		answer=''
		key_characters=chain.from_iterable(repeat(self.key))
		for c in ciphertext:
			answer+=Cipher._subtract_characters(c,next(key_characters))
		return answer

# a Caesar shift cipher
class Caesar(Cipher):
	def __init__(self):
		super(Caesar,self).__init__("d")
