from string import ascii_letters, maketrans, ascii_lowercase, punctuation

def encode(seq):

	endcodeTable = maketrans(ascii_letters, ascii_lowercase[::-1] * 2)
	ss = seq.translate(endcodeTable, punctuation + ' ')
	return " ".join(ss[i:i+5] for i in range(0, len(ss), 5))  # i's are [0, 5, 10, ...]


def decode(seq):
	decodeTable = maketrans(ascii_lowercase, ascii_lowercase[::-1])
	return seq.translate(decodeTable, ' ')
