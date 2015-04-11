def parse_octal(num):
	num=num
	validate(num)
	n = len(num)-1
	result=0
	for c in num:
		result+= int(c)* pow(8,n)
		n= n-1
	print result
	return result

def validate(num):
	for i in num:
		if int(i) not in range(0,8):
			raise ValueError, 'Not valide input'
