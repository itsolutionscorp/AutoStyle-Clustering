translate = {
             0: '',
             1: 'I',
             5: 'V',
             10: 'X',
             50: 'L',
             100: 'C',
             500: 'D',
             1000: 'M'
             }
 
def digit(digit, pos):
	digit = int(digit)
	pow = 10 ** pos
	if digit % 5 == 4:
		return translate[pow] + translate[pow * (digit+1)]
	else:
		first = translate[0 if digit < 5 else 5 * pow]
		return first + translate[pow] * (digit % 5)
		
def numeral(arabic):
	text = '%04d' % (arabic)
	return ''.join([digit(text[i], 3-i) for i in range(4)])
