#
# Skeleton file for the Python "Bob" exercise.
#
def hey(s):
	if(len(s)>0):
		if(s.isupper()): #Always before question condition as "CAPITALS?" falls in this category
			return "Whoa, chill out!"
		if(s.isspace()): 
			return "Fine. Be that way!"
		k=s.strip()
		if(k[len(k)-1] == '?'): 
			return "Sure."
		return "Whatever."
	else:
		return "Fine. Be that way!" #special case for empty string
