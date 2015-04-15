import string

def encode(sentence):
	sentence = sentence.lower()
	sen = sentence.translate(string.maketrans("",""), string.punctuation) # cut punctuation
	sen = sen.split() #split the sentence in words
	[i.strip() for i in sen] # cut  whitespace
	
	z = ''
	counter = 0 
	for word in sen:
		for char in word:
			counter +=1
			if char.isdigit():
				z+=char
			else:
				z += invert_char(char)
			if counter % 5 ==0:
				z+=' '
	return z.rstrip()
	
	

def decode(sentence):
	z = ''
	sen = sentence.split()
	for word in sen:
		for char in word:
			z += invert_char(char)
			
	return z
	
def invert_char(character):
	a = 'a b c d e f g h i j k l m n o p q r s t u v w x y z'.split()
	invers_a = a[::-1]
	return invers_a[a.index(character)]
	
