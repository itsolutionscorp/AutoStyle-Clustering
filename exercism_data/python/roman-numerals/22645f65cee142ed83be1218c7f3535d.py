# misread the problem and accidentally set up a function to convert from number to roman numeral

def roman(n):
	print n
	if len(n) > 1:
		letters = list(n)
	else:
		letters = n
	prev = 1000
	sum = 0
	convert = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}
	for letter in letters:
		if prev < convert[letter]:
			sum = sum - 2*prev
		sum = sum + convert[letter]
		prev = convert[letter]
	return sum

# I feel like there must be a more efficient way to do this problem, but I'm not sure what it would look like..

def numeral(n):
	roman = ""
	while n >= 1000:
		roman = roman + "M"
		n -= 1000
	if n >= 900:
		roman = roman + "CM"
		n = n - 900
	if n >= 500:
		roman = roman + "D"
		n = n - 500
	if n >= 400:
		roman = roman + "CD"
		n = n - 400
	while n >= 100:
		roman = roman + "C"
		n -= 100
	if n >= 90:
		roman = roman + "XC"
		n = n - 90
	if n >= 50:
		roman = roman + "L"
		n = n - 50
	if n >= 40:
		roman = roman + "XL"
		n = n - 40
	while n >= 10:
		roman = roman + "X"
		n -= 10
	if n >= 9:
		roman = roman + "IX"
		n -= 9
	if n >= 5:
		roman = roman + "V"
		n -= 5
	if n >= 4:
		roman = roman + "IV"
		n -= 4
	while n >= 1:
		roman = roman + "I"
		n -= 1
	return roman
