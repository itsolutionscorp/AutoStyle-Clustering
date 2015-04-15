import string
# atbash cipher implementation
key_forward = "abcdefghijklmnopqrstuvwxyz"
key_reverse = "zyxwvutsrqponmlkjihgfedcba"

# decode an atbash cipher, removing all spaces
def decode(ciphertext):
	ciphertext = ''.join(word for word in ciphertext.split())
	plaintext = ciphertext.translate(str.maketrans(key_forward, key_reverse))
	return plaintext

# encode using atbash cipher, returning string in groups of five letters
def encode(plaintext):
	ciphertext_groups = []
	plaintext = ''.join(word for word in plaintext.lower().split())
	ciphertext = plaintext.translate(str.maketrans(key_reverse, key_forward, string.punctuation))
	for i in range(int(len(ciphertext)/5)):
		ciphertext_groups.append(ciphertext[i*5:i*5+5])
	ciphertext_groups.append(ciphertext[int(len(ciphertext)/5)*5:len(ciphertext)+1])

	ciphertext = ' '.join(ciphertext_groups)

	return ciphertext.strip()
	
