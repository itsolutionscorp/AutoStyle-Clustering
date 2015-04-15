#!/usr/bin/python -tt

#Question ends with '?'
#Yelling is all caps
#Nothing contains no char

def hey(input_str):
	
	def capitalize_str(input_str):
		alpha_present = False
		out = ''
		for x in input_str:
			if x.isalpha():
				out = out+x.upper()
				if not alpha_present:
					alpha_present = True
			else:
				out = out+x
		return out,alpha_present
		
	cap_str,alpha_present = capitalize_str(input_str)
	
	if input_str.strip() == '': #removes all spaces/newline/... and check what is left
		return 'Fine. Be that way!'
	lchar=input_str[-1] #last character of input
	if (cap_str == input_str and alpha_present): #check if string is all caps
		return 'Woah, chill out!'
	if lchar == '?': #check if question (yelling already eliminated)
		return 'Sure.'
	return 'Whatever.'
