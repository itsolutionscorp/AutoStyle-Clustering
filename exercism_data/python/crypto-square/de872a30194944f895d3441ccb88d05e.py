from string import letters, digits
from math import sqrt, ceil
from itertools import count
def encode(message):
	msg = ''.join([x for x in message.lower() if x in letters or x in digits])
	square = []
	result = []
	sq = sqrt(len(msg))
	if sq.is_integer():
		for x in range(0,int(sq)):
			square.append(msg[x*int(sq):(x+1)*int(sq)])
	else:
		for x in count(len(msg)):
			if sqrt(x).is_integer():
				num = sqrt(x)
				break
		for x in range(int(ceil(len(msg)/num))):
			square.append(msg[x*int(num):(x+1)*int(num)])
	if square:
		for x in range(len(square[0])): 
			for y in square:
				if len(y) > x: result.append(y[x])
			result.append(' ')
	return ''.join(result).strip()
