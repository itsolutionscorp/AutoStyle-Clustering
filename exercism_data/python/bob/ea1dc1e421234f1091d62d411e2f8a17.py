def hey(arg):

	if not arg or arg[-1]==" " or arg[-1]=="\t":
		return "Fine. Be that way!"

	if arg.isupper(): 
		return "Whoa, chill out!"

	if arg[-1]=="?":
		return "Sure."

	return "Whatever."
	
