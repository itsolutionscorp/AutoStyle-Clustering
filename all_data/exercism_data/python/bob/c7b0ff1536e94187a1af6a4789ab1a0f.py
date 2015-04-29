def hey(str):
    if str.isupper():
    	return "Woah, chill out!"   
    if str.endswith('?'):
    	return "Sure."
    if str.strip() == "":
    	return "Fine. Be that way!"
    return "Whatever."
