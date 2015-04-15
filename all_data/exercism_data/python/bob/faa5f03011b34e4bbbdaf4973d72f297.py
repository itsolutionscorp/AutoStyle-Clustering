def char_count(str):
	if len(str) > 0:
		i = 0
		for char in str:
			if char.isalpha() or char.isnumeric():
				i+=1
		return i
	else:
		return 0


def hey(str):
	if char_count(str) == 0:
		return "Fine. Be that way!"
	elif str.isupper() or (str[-1] == "!" and str[-2].isupper()):
		return "Whoa, chill out!"		
	elif str[-1] == "?":
		return "Sure."
	else:
		return "Whatever."
		
