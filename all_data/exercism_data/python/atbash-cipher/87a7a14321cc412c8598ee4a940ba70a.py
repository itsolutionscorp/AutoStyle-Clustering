import string

ttable = str.maketrans(string.ascii_lowercase+'0123456789', string.ascii_lowercase[::-1]+'0123456789',' ')

def encode(plaintext):
	plaintext = ''.join([ch for ch in plaintext.lower() if ch in string.ascii_lowercase+'0123456789']).translate(ttable)
	plaintext = ' '.join([plaintext[ii:ii+5] for ii in range(0,len(plaintext),5)])
	return plaintext
	
def decode(codedtext):
	return codedtext.translate(ttable)
	
if __name__ == '__main__':
	print(decode('vcvix rhn'))
	print(encode("Testing, 1 2 3, testing."))
