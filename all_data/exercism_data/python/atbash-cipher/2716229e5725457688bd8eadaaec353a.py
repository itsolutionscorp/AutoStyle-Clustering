from string import * 

def encode(text):
	text = text.lower().translate(maketrans("abcdefghijklmnopqrstuvwxyz","zyxwvutsrqponmlkjihgfedcba"), punctuation + " ")

	c = 0
	for i in range(5, len(text), 5):
		text = text[:i + c] + ' ' + text[i + c:]
		c += 1
	return text

def decode(text):
	text = text.lower().translate(maketrans("zyxwvutsrqponmlkjihgfedcba", "abcdefghijklmnopqrstuvwxyz"), punctuation + " ")
	return text
