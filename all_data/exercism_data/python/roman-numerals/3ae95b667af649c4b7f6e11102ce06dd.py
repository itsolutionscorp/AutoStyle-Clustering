numbers = {1: 'I', 5: 'V',
			10: 'X', 50: 'L',
			100: 'C', 500: 'D',
			1000: 'M'}
			

def numeral(num):
	digits = [num/1000, (num % 1000)/100, (num % 100)/10, num % 10]
	
	roman = ''
	for i in range(len(digits)):
		place = 4 - i
		digit = digits[i]
		if digit < 4 or place == 4:
			roman += (numbers[10**(place-1)]) * digit
		elif digit == 4:
			roman += numbers[10**(place-1)] + numbers[5 * 10**(place-1)]
		elif 4 < digit < 9:
			roman += numbers[5 * 10**(place-1)] + (digit-5) * numbers[10**(place - 1)]
		elif digit == 9:
			roman += numbers[10**(place-1)] + numbers[10**place]
	return roman
