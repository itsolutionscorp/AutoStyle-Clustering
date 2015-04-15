import string

def encode(plaintext):
	alphabet = string.lowercase
	reverse_alphabet = alphabet[::-1]
	atbash = dict(zip(alphabet + string.digits, reverse_alphabet + string.digits))
	ciphertext = []
	plaintext = plaintext.lower()
	j = 0
	
	for i in plaintext:
		if j == 5:
			ciphertext.append(' ')
			j = 0
		if i in atbash:
			ciphertext.append(atbash[i])
			j += 1
	
	return "".join(ciphertext).strip()
	
def decode(ciphertext):
	alphabet = string.lowercase
	reverse_alphabet = alphabet[::-1]
	atbash = dict(zip(reverse_alphabet + string.digits, alphabet + string.digits))
	ciphertext = ciphertext.lower()
	plaintext = []
	
	for i in ciphertext:
		if i in atbash:
			plaintext.append(atbash[i])
	
	return "".join(plaintext)
