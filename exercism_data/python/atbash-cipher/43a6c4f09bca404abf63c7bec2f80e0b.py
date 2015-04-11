from string import maketrans

PLAIN = "abcdefghijklmnopqrstuvwxyz"
CIPHER = "zyxwvutsrqponmlkjihgfedcba"

#Substitutes characters in formated input to CIPHER characters, and seperates them with a space every 5 characters.
def encode(input_string):

	#Removes invalid characters and spaces, and translates remaining characters to CIPHER
	translated_input = format_input(input_string).translate(maketrans(PLAIN, CIPHER))

	#returns space seperated string of 5 character slices from translated_input
	return " ".join([translated_input[i:i+5] for i in range(0,len(translated_input),5)])

#Reverses effects of "encode" method, returning restults as one long string
def decode(input_string):
	return format_input(input_string).translate(maketrans(CIPHER, PLAIN))

#Removes all non-alphanumeric characters including mid-string spaces and returns result as all lowercase.
def format_input(input_string):
	return ''.join([char for char in input_string if char.isalnum()]).lower()
