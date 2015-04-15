from string import maketrans, punctuation
def encode(phrase):
	intab = 'abcdefghijklmnopqrstuvwxyz'
	outab = 'zyxwvutsrqponmlkjihgfedcba'
	# remove whitespace and punctuation
	updatedString = phrase.translate(maketrans("",""), punctuation)
	updatedString = "".join(updatedString.split()).lower()
	updatedString = updatedString.translate(maketrans(intab, outab))
	newString = ''
	for i,char in enumerate(updatedString):
		if (i+1)%5 == 0:
			newString = newString + char + ' '
		else:
			newString += char
	return newString.rstrip()
	# make lowercase
	# encode using maketrans
	# include spaces between letters every 5

def decode(codedPhrase):
	outab = 'abcdefghijklmnopqrstuvwxyz'
	intab = 'zyxwvutsrqponmlkjihgfedcba'
	updatedString = "".join(codedPhrase.split())
	updatedString = updatedString.translate(maketrans(intab, outab))
	return updatedString

print(encode("The quick brown fox jumps over the lazy dog."))
