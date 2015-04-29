def numeral(ar):
	remainder = ar
	a_nums = [1000,900,500,400,100,90,50,40,10,9,5,4,1]
	r_nums = ["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"]
	res = ''
	for x in range(len(a_nums)):
		numeral, remainder = divmod(remainder, a_nums[x])
		res += r_nums[x]*numeral
	return res
	
