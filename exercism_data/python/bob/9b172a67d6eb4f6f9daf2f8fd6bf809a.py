
def isQ(s):
    return s.strip()[-1] == '?'

def isS(s):
    return len(s.strip()) == 0

def isE(s):
    return s.isupper()

def hey(sentence):
    if isS(sentence):
	return 'Fine. Be that way!'
    elif isE(sentence):
	return 'Whoa, chill out!'
    elif isQ(sentence):
	return 'Sure.'
    else:
	return 'Whatever.'
