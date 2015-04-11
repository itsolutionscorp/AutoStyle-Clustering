import string

def encode(plaintext):
	atbash = dict(zip(string.lowercase + string.digits, string.lowercase[::-1] + string.digits ))
	plaintext = plaintext.lower()
	ciphertext = []
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
	atbash = dict(zip(string.lowercase[::-1] + string.digits, string.lowercase + string.digits))
	ciphertext = ciphertext.lower()
	plaintext = []
	
	for i in ciphertext:
		if i in atbash:
			plaintext.append(atbash[i])
	
	return "".join(plaintext)
