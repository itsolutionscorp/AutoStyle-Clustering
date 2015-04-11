def hey(input):
	'''Returns a string as required by the unit test bob_test.py.'''
	if input.endswith('?') and not input.isupper():
		return('Sure.')
	elif input.isupper():
		return('Woah, chill out!')
	elif input == '' or input.isspace():
		return('Fine. Be that way!')
	else:
		return('Whatever.')

def main():
	'''Interactive version of the bob.hey() function'''
	while(1):
		print(hey(input("Hey\n>")))

if __name__ == '__main__':
	main()
