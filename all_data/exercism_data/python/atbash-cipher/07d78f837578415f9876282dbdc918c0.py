def encode(string):
	result = ''
	i = 0
	for char in string:
		if char.isdigit():
			result += char
			i += 1
			if i%5 == 0:
				result += ' '
		elif not char in '.,?! ':
			result += replaceChar(char.lower())
			i += 1
			if i%5 == 0:
				result += ' '
	return result.strip()

def decode(string):
	result = ''
	for char in string:
		if char != ' ':
			result += replaceChar(char.lower())
	return result

def replaceChar(char):
	letters = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(',')
	index = 25-letters.index(char)
	return letters[index]
