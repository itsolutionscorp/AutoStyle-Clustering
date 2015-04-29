import string
import itertools

# atbash cipher implementation.
key_forward = string.ascii_lowercase
key_reverse = key_forward[::-1]
key_strip = string.punctuation

# Perform character substitution, with special chars removed.
def transliterate(sourcetext):
	sourcetext = ''.join(word.lower() for word in sourcetext.split())
	transtable = str.maketrans(key_forward, key_reverse, key_strip)
	transtext = sourcetext.translate(transtable)
	return transtext

# decode an atbash cipher, removing all spaces
def decode(ciphertext):
	return transliterate(ciphertext)

# encode using atbash cipher, returning string in groups of five letters
def encode(plaintext):
	ciphertext_groups = []
	ciphertext = transliterate(plaintext)

	# zip_longest takes 5 copies of the same iterator, then appends the next available
	# char to form each group, completing the final group with empty strings.
	ciphertext_groups = zip_longest(*[iter(ciphertext)]*5)

	# The ciphertext_groups is a 2D list. Compress along both axes, with spaces
	# between each step on the second axis, to produce a single string of groups.
	ciphertext = ' '.join(map(lambda chargroup: ''.join(chargroup),ciphertext_groups))

	return ciphertext.strip()
	
