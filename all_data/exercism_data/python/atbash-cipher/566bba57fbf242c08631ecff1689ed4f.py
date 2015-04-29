import string

alpha = string.ascii_lowercase 
ahpla = alpha[::-1] # extended slice 
spacePunc = string.punctuation + ' '

def encode(seq):

	endcodeTable = string.maketrans(alpha, ahpla)
	ss = seq.lower().translate(endcodeTable, spacePunc)
	return " ".join(ss[i:i+5] for i in range(0, len(ss), 5))  # i's are [0, 5, 10, ...]


def decode(seq):
	decodeTable = string.maketrans(ahpla, alpha)
	return seq.lower().translate(decodeTable, ' ')
