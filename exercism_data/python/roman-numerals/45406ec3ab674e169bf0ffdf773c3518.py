##thanks to nathass

def numeral(arabic):
	remainder = arabic
	aNums = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
	rNums = ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"]
	resolution = ''
	for i in range(len(aNums)):
		multiplier, remainder = divmod(remainder, aNums[i])
		resolution += rNums[i]*multiplier
	return resolution
	
print numeral(201)
