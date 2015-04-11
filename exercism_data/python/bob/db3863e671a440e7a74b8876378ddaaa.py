def hey(s):
	if s.strip() == "":
		return "Fine. Be that way!"
	elif isYell(s):
		return "Whoa, chill out!"
	elif s[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
		
def isYell(s): 
	containsLetter = False
	for c in s:
		if c.isalpha():
			containsLetter = True
			if c == c.lower():
				return False #There is a lowercase letter -> not a yell
	
	return containsLetter #Returns true if s contains no lowercase letters
							#but does contain at least one letter
