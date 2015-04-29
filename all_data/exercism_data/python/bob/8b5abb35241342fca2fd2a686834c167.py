def hey(string):
		if len(string) > 0:
			if string[-1] == '?' and not string.isupper():
				return "Sure."
			if '!' in string and string.isupper() or string.isupper():
					return "Whoa, chill out!"
		if string == '' or string.strip() == '':
			return "Fine. Be that way!"
		return "Whatever."
