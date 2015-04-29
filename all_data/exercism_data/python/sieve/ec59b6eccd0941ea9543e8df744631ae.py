def sieve(input_number):

	dict_of_numbers = {n:'prime' for n in range(2,input_number+1)}

	for n in dict_of_numbers:
		if dict_of_numbers[n] == 'prime':
			for m in range(2, input_number/2):
				if n*m <= len(dict_of_numbers)+1:
					dict_of_numbers[n*m] = 'not_prime'

	return [n for n in dict_of_numbers if dict_of_numbers[n] == 'prime']
