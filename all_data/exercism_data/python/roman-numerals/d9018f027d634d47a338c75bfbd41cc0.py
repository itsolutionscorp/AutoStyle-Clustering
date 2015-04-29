roman = {0: {1: 'I', 5: 'V'}, 1: {1: 'X', 5: 'L'}, 2: {1: 'C', 5: 'D'}, 3: {1: 'M'}}

def numeral(n):
	r = []
	for power, digit in enumerate(reversed(str(n))):
			r.append(getRoman(int(digit), power))
	return ''.join(reversed(r))
			

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
		return roman[i][1]+roman[i+1][1]
	else:
		return ''
