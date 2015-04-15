def numeral(arabic):
	'''
	convert from arabic numbers to Roman Numerals
	'''
	
	romanStrings = (
		(1000,'M'),(900,'CM'),(500,'D'),(400,'CD'),
		(100,'C'),(90,'XC'),(50,'L'),(40,'XL'),
		(10,'X'),(9,'IX'),(5,'V'),(4,'IV'),
		(1,'I')
	)
		
	roman = ''
	for num,romanStr in romanStrings:
		if arabic-num>=0:
			roman += romanStr * (arabic // num)
			arabic = arabic % num

	return roman	

if __name__ == '__main__':
	print(numeral(8641))
	
