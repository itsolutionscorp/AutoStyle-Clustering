# The test cases for this are borken. I've sent a pull request.

import string


forward_lower = string.lowercase
backward_lower = ''.join(x for x in reversed(forward_lower))

forward_upper = string.uppercase
backward_upper = ''.join(x for x in reversed(forward_upper))

plain_to_cipher = string.maketrans(forward_lower + forward_upper,
		backward_lower + backward_upper)
cipher_to_plain = string.maketrans(backward_lower + backward_upper,
		forward_lower + forward_upper)

def encode(plaintext):
	return string.translate(plaintext, plain_to_cipher)

def decode(ciphertext):
	return string.translate(ciphertext, cipher_to_plain)
