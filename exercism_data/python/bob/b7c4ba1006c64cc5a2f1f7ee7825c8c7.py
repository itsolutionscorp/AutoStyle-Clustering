def _isNumbers(s):
	num = False
	for i in xrange(0,len(s)-1):
		if(s[i].isdigit()):
			num = True
		if(s[i].isalpha()):
			num = False
			break
	return num

def hey(s):
	sp = True

	# a numeric question
	if(_isNumbers(s)):
		if(len(s) > 0 and s[len(s)-1]=='?'):
			return "Sure."

    #normal question
	if(((s != s.upper()) and len(s) > 0) and s[len(s)-1]=='?'):
		return "Sure."

    #empty question
	for i in xrange(1,len(s)):
		if(not s[i].isspace()):
			sp = False
	if (sp==True):
		return "Fine. Be that way!"

	# just numbers, but not a question.
	if(_isNumbers(s)):
		return "Whatever."
	
	# shouting
	if (s==s.upper()):
		return "Whoa, chill out!"

	# empty message or anything else
	if (s==''):
		return "Fine."
	else:
		return "Whatever."
