import re

alphabet = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z']

def encode(string):
	"""Encodes string with The Atbash cipher"""
	if type(string) is not str:
		raise TypeError("Input needs to be a string")
	return ''.join(" "+letter if (i>0 and i%5==0) else letter for i, letter in enumerate(__gen_cipher(string)))
		
def decode(string):
	"""Decodes string encoded with The Atbash cipher"""
	if type(string) is not str:
		raise TypeError("Input needs to be a string")
	return ''.join(letter for letter in __gen_cipher(string))

def __gen_cipher(string):
	"""Creates The Atbash cipher generator"""
	return (alphabet[25-alphabet.index(ch)] if ch in alphabet else ch for ch in re.sub('[^0-9a-z]',"",string.lower()))
