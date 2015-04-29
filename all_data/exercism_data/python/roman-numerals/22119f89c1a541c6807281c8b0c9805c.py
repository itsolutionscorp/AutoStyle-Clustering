def numeral(num):
	values = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
	chars = "M,CM,D,CD,C,XC,L,XL,X,IX,V,IV,I".split(',')
	romNum = ''
	for i,value in enumerate(values):
		while num >= value:
			romNum += chars[i]
			num -= value
	
	return romNum
