from math import factorial

def row(row_number):
	result = [] 
	half_row = row_number + 1

	if (half_row % 2 == 0):
		half_row /= 2
	else:
		half_row = (half_row / 2) + 1

	for n in range(half_row):
		result.append( str(factorial(row_number) / ( factorial(n) * factorial(row_number - n) )) )
	
	if ((row_number + 1) % 2 == 0):
		result += result[::-1]
	else:
		temp = result[:-1]
		result += temp[::-1]
	return ' '.join(result)

def triangle(rows):
	result = []
	for n in range(rows+1):
		result.append(row(n))
	return result

def is_triangle(to_check):
	template_triangle = triangle(len(to_check)-1) 
	return set(template_triangle) == set(to_check)
