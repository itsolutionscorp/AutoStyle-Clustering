plain= 'abcdefghijklmnopqrstuvwxyz'
cipher='zyxwvutsrqponmlkjihgfedcba'

def encode(string):
	sew=string.lower()
	encoded=[]
	counter=0
	for x in sew:
		u=plain.find(x)
		if u==-1 and counter%5==0:
			counter=0
		if x.isdigit():
			encoded.append(x)
			counter+=1
		if u!=-1:
			counter+=1
			encoded.append(cipher[u])
		if counter%5==0 and counter!=0:
			encoded.append(' ')
	if encoded[-1]==' ':
		del encoded[-1]
	encoded=''.join(encoded)
	return encoded

def decode(string):
	sew= string.split(' ')
	sew=''.join(sew)
	sew=list(sew)
	decoded=[]
	for x in sew:
		u=cipher.find(x)
		decoded.append(plain[u])
	return ''.join(decoded)
