def decode(s): 
	"""Takes string that has been coded using Atbash Cipher, decodes it"""
	
	s = s.replace(" ", "")
	
	s = atbash(s)
	
	return s

def encode(s): 
	"""Takes string, returns coded version of string using Atbash Cipher"""
	
	s = s.lower()
	s = "".join(e for e in s if e.isalnum())

	s = atbash(s)
	
	s = " ".join(s[i:i+5] for i in range(0, len(s), 5))
	
	return s
	
def atbash(to_code): 
	import string
	alphabet = string.lowercase
	reversed_alphabet = alphabet[::-1]
	
	translation = string.maketrans(alphabet, reversed_alphabet)
	
	to_code = string.translate(to_code, translation)
	
	return to_code
