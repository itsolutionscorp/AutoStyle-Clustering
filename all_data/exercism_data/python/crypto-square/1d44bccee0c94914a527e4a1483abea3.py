from math import sqrt

def encode(sentence):
	r = []
	for i in formSquare(sentence).values():
		r.append(''.join(i))
	return ' '.join(r)

def formSquare(sentence):
	sentence = ''.join([x.lower() for x in sentence if x.isalnum()])
	n = int(sqrt(len(sentence)))
	if n**2 < len(sentence):
		n += 1
		
	r = {}
	for ind,x in enumerate(sentence):
		if ind%n in r.keys():
			r[ind%n].append(x)
		else:
			r[ind%n] = [x]
	return r

if __name__ == '__main__':
	print encode("je ne sais pas!!! comment vous l'uarie1 psir")
