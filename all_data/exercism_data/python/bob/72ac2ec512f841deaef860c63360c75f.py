def hey(text):
    if not text or text.isspace():
		return 'Fine. Be that way!'
    if text.isupper():
        return 'Whoa, chill out!'
    if text[-1]=='?':
        return 'Sure.'
    else:
        return 'Whatever.'
        
