import itertools, string, re

alpha='abcdefghijklmnopqrstuvwxyz123456789'
cipher='zyxwvutsrqponmlkjihgfedcba123456789'

def encode(mssg):
	new = zip(alpha,cipher)
	mssg = mssg.lower().replace(' ','').translate(None,string.punctuation)
	output =[]
	for i in mssg:
		y = [''.join(v) for k,v in new if k == i]
		output = output + y
	post_cipher = ''.join(output)
	remainder = 0
	remainder = len(post_cipher)%5
	if remainder != 0 and len(post_cipher) < 5:
		last = post_cipher[-remainder:]
		first = post_cipher[:-remainder]
		split_cipher = ' '.join(re.findall('.....',first)) + last
	elif remainder != 0:
		last = post_cipher[-remainder:]
		first = post_cipher[:-remainder]
		split_cipher = ' '.join(re.findall('.....',first)) + ' ' + last
	else:
		split_cipher = ' '.join(re.findall('.....',post_cipher))
	return split_cipher

def decode(mssg):
	new = zip(cipher,alpha)
	mssg = mssg.lower().replace(' ','')
	output=[]
	for i in mssg:
		y = [''.join(v) for k,v in new if k == i]
		output = output + y
	post_cipher = ''.join(output)
	return post_cipher
