_ones='IXCM'
_fives='VLD'

# translates n into roman numerals. Only works with values between 1 and 3999 (inclusive)
def numeral(n):
	answer=''
	for i in range(len(_ones)):
		digit=(n//(10**i))%10
		if digit==4:
			answer = _ones[i] + _fives[i] + answer
		elif digit==9:
			answer = _ones[i] + _ones[i+1] + answer
		elif digit>4:
			answer = _fives[i] + _ones[i]*(digit-5) + answer
		else:
			answer = _ones[i]*digit + answer
	return answer
