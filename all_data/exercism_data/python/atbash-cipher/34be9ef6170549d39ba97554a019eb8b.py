alphabet = '0123456789abcdefghijklmnopqrstuvwxyz9876543210'
mapping = {a: b for a, b in zip(alphabet, alphabet[::-1])}

def decode(ciphertext):
	return ''.join(mapping[letter] for letter in ciphertext if letter in alphabet)

def encode(plaintext):
	plaintext = [letter for letter in plaintext.lower() if letter in alphabet]
	ciphertext = ''.join(mapping[letter] if i % 5 else ' ' + mapping[letter] 
	                     for i, letter in enumerate(plaintext))
	return ciphertext.strip()
