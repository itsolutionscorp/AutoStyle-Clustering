import math

def encode(text):
	text=text.lower()
	text=[k for k in text if k in '1234567890abcdefghijklmnopqrtsuvwxyz']
	size=get_size(text)
	new=[]
	for i in range(0,size):
		newstr=''
		k=i
		while k < len(text):
			newstr+=text[k]
			k+=size
		new.append(newstr)
	return ' '.join(new)

def get_size(text):
	return int(math.ceil(len(text)**0.5))
