import string


forward = string.lowercase
backward = ''.join(x for x in reversed(forward))

plain_to_cipher = string.maketrans(forward + ',.', backward + '  ')
cipher_to_plain = string.maketrans(backward, forward)

def encode(plaintext):
        plaintext = ''.join(plaintext.lower().split())
	ciphertext = string.translate(plaintext, plain_to_cipher).replace(' ','')
        words = len(ciphertext) / 5 if len(ciphertext) % 5 == 0 \
                    else len(ciphertext) / 5 + 1
        return ' '.join(ciphertext[i*5:i*5+5] for i in range(words))

def decode(ciphertext):
        ciphertext = ''.join(ciphertext.lower().split())
	return string.translate(ciphertext, cipher_to_plain)
