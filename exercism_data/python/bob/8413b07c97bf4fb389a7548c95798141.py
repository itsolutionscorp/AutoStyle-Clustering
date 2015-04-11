import sys
import re

# st = sys.argv[1]

EVERYTHING_ELSE = "Fine. Be that way!"
SHOUT			=	"Whoa, chill out!"
QUESTION		=	"Sure."
WHATEVER		= 	"Whatever."

def hey(st):
	
	if len(st) == 0:
		return "Fine. Be that way!"
		
	lastChar = st[-1]
	st = st[:-1]
	
	if lastChar == "?":
		if st.upper() == st and len(re.sub(r"\d","",st)) != 0:
			return SHOUT
		return QUESTION
	
	if len(re.sub(r"\d","",st)) == 0:
		return EVERYTHING_ELSE
	
	if len(re.sub(r"\s", "", st)) == 0:
		return EVERYTHING_ELSE
	
	if len(re.sub(r"[\W\d]","",st)) == 0:
		return WHATEVER
			
	if st.upper() == st and len(re.sub(r"\s", "", st)) != 0:
		return SHOUT
									

			
	return WHATEVER
	
# print hey(st)
