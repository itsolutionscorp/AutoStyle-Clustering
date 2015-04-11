"""
Contains functions to encode and decode 
strings to/from the Atbash Cipher.
"""

from string import *

alphabet = 'abcdefghijklmnopqrstuvwxyz'
reverse_alphabet = 'zyxwvutsrqponmlkjihgfedcba'

def encode(instring):
	"""
	Encodes string in Atbash cipher
	"""
	
	instring = format_in(instring)
	coded = ''
	for char in instring:
		translated = translate(char)
		coded += translated
	
	coded = insertspaces(coded)
	
	return coded
	
def decode(instring):
	"""
	Decodes string in Atbash cipher
	"""
	
	instring = removespaces(instring)
	decoded = ''
	for char in instring:
		translated = translate(char)
		decoded += translated
	
	return decoded
	
def translate(char):
	"""
	Translates a character to/from Atbash
	"""
	trans = maketrans(alphabet,reverse_alphabet)
	if 96 < ord(char) < 123:
		return char.translate(trans)
	else:
		return char

def format_in(instring):
	"""
	Formats input strings before encoding
	"""
	
	formatted = ''
	for char in instring:
		char = lower(char)
		if char != ' ' and char not in punctuation:
			formatted += char
	return formatted
	
def insertspaces(instring):
	"""
	Helps format output of encoding
	"""
	
	numspaces = int(float(len(instring)) / 5.)
	segments = []
	for i in range(numspaces):
		segments.append(instring[5*(i):5 * (i+1)])
	segments.append(instring[(numspaces) * 5:])
	finished = ' '.join(segments).strip()
	return finished

def removespaces(instring):
	"""
	Removes spaces from input for decoding
	"""
	output = ''
	for char in instring:
		if char != ' ':
			output += char
	return output
