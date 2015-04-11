# atbash_cipher.py
from string import lowercase, maketrans, punctuation, whitespace

def encode(phrase):
	phrase = phrase.lower()
	trans = maketrans(lowercase, lowercase[::-1])
	phrase = phrase.translate(trans, whitespace + punctuation)
	return " ".join(phrase[i:i+5] for i in range(0, len(phrase), 5))
	
	
	
def decode(phrase):
	trans = maketrans(lowercase[::-1], lowercase)
	return phrase.translate(trans, whitespace + punctuation)
