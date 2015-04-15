_digits='0123456789abcdef'

# evaluates string as a number in hexadecimal.
# raises ValueError if string is not correctly formatted
def hexa(string):
	answer=0
	for i in range(len(string)):
		answer+=_digits.index(string[-i-1].lower())*(1<<(4*i))
	return answer
