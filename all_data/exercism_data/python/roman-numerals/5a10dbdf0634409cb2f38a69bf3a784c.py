DIGITS = {
	0 : [],
	1 : [2],
	2 : [2,2],
	3 : [2,2,2],
	4 : [2,1],
	5 : [1],
	6 : [1,2],
	7 : [1,2,2],
	8 : [1,2,2,2],
	9 : [2,0]
}

SYMBOLS = list("MDCLXVI")

def numeral(n):
	last3 = []
	last3 = map(int, "%03d" % (n%1000))

	offset = 0
	digits = [0] * (n / 1000)

	for d in last3:
		digits += [x+offset for x in DIGITS[d]]
		offset += 2

	return ''.join(map(SYMBOLS.__getitem__, digits))
