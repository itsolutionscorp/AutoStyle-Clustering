
def isQ(s):
    return s.strip()[-1] == '?'

def isS(s):
    return len(s.strip()) == 0

def isE(s):
    return s.upper() == s

def onlyNum(s):
    return all(31 < ord(x) < 58 for x in s)

def hey(sentence):
    if isS(sentence):
	return 'Fine. Be that way!'
    elif isQ(sentence):
	if isE(sentence) and not onlyNum(sentence[:-1]):
	    return 'Whoa, chill out!'
	return 'Sure.'
    elif isE(sentence):
	if onlyNum(sentence):
	    return 'Whatever.'
	return 'Whoa, chill out!'
    else:
	return 'Whatever.'
