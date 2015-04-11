import string

puncs = string.punctuation
digits = string.digits

def hey(s):
	s=s.strip()
	areUpper=[l.isupper() for l in s]
	areUpPunDigSp=[l.isupper() or l in puncs+digits+' ' for l in s]
	if s=='':
		return "Fine. Be that way!"
	if any(areUpper) and all(areUpPunDigSp):
		return "Whoa, chill out!"
	if s[-1]=='?':
		return "Sure."
	return "Whatever."
