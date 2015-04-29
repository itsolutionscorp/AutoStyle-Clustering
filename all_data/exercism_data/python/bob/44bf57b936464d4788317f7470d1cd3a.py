import string

def hey(str):
	response = "Whatever.";
	if  str.isupper():
		response = "Woah, chill out!"
	elif str.endswith("?"):
		response = "Sure."
	elif str.isspace() or str == "":
		response = "Fine. Be that way!"
	return response;
