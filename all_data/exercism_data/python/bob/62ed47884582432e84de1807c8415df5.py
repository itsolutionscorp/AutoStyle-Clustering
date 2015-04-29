def hey(statement):
	if statement.strip() == '':
		return "Fine. Be that way!"
	if statement.isupper():
		return "Whoa, chill out!"
	if statement.endswith('?'):
		return "Sure."
	return "Whatever."

def main():
	statement = raw_input('What do you want to say to Bob? ')
	response = hey(statement)
	print(response)

version = "1.0"

if __name__ == '__main__':
	main()
