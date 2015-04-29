numerals = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}
numbers = {1:'I', 5:'V', 10:'X', 50:'L', 100:'C', 500:'D', 1000:'M'}

def numeral_to_number(roman_numeral):
	index = 0
	end = len(roman_numeral)
	arabic_num = 0
	while index+1 < end:
		current_numeral = numerals[roman_numeral[index]]
		next_numeral = numerals[roman_numeral[index+1]]
		if next_numeral >= current_numeral:
			arabic_num -= current_numeral
		else:
			arabic_num += current_numeral

		index += 1
	#Check last numeral in sequence of numerals. Fence-post problem
	arabic_num += numerals[roman_numeral[end-1]]
	return arabic_num

#print numeral_to_number('MCMXC')

def numeral(arabic_num):
	divisor = 1000
	roman_numeral = ''
	a_num = arabic_num

	while divisor != 0:
		digit = arabic_num/divisor

		if (arabic_num%divisor) != arabic_num:
			#Update number to work with next digits place on next loop
			if digit == 0:
				arabic_num -= divisor
			else:
				arabic_num -= divisor*digit
			#Create roman numeral for specific digits place
			if digit == 9:
				roman_numeral += numbers[divisor] + numbers[divisor*10]
			elif digit == 4:
				roman_numeral += numbers[divisor] + numbers[divisor*5]
			else:
				if digit >= 5:
					roman_numeral += numbers[divisor*5]
					digit -= 5

				for i in range(digit):
					roman_numeral += numbers[divisor]

		divisor /= 10

	return roman_numeral
