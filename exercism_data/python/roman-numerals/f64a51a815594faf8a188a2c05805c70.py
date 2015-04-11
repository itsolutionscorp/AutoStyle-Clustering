roman = {1: {1: 'I', 5: 'V'}, 10: {1: 'X', 5: 'L'}, 100: {1: 'C', 5: 'D'}, 1000: {1: 'M'}}

def numeral(n):
	r = ''
	for p in range(3,-1,-1):
		if n >= pow(10,p):
			t = int(round(n/pow(10,p)))
			r += getRoman(t, pow(10,p))
			n = n-(t*pow(10,p))
	return r
			

def getRoman(x,i):
	if x <= 3:
		return ''.join(roman[i][1] for n in range(0,x))
	elif x == 4:
		return roman[i][1]+roman[i][5]
	elif x == 5:
		return roman[i][5];
	elif x > 5 and x <= 8:
		return roman[i][5]+getRoman(x-5, i)
	elif x == 9:
		return roman[i][1]+roman[i*10][1]
	else:
		return ''
