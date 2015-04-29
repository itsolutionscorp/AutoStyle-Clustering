def on_square(number):
	return 2 ** (number - 1)

def total_after(number):
	sum = on_square(number)	
	if number != 1:
		sum += total_after(number-1)		
	return sum
