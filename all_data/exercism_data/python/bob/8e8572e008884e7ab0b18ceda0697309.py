#!/usr/bin/env python

def question():
	while True:
		user_input = raw_input("Enter what do you want to say to bob-->")
		hey(user_input)
	
def hey(string):
	string1 = string.strip();
	if not string1:
		return 'Fine. Be that way!' 
	elif string1.isupper():
		return 'Whoa, chill out!'
	elif string1.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
		
def main():
	question()

if __name__ == '__main__':
	main()
