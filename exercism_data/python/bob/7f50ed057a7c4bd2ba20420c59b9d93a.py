def hey(what):
	if what.isupper():
		return 'Whoa, chill out!'
	elif what.endswith('?'):	
		return 'Sure.'
	elif what.strip() == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
def main():
    while True:
        statement = raw_input('What do you want to say to Bob? ')
        response = hey(statement)
        print(response)

if __name__ == '__main__':
    main()
