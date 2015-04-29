def hey(query):
    
    if query.isupper():
        return "Whoa, chill out!"
    elif query.endswith('?'):
        return "Sure."
    elif not strip_control_characters(query).strip():
    	return 'Fine. Be that way!'
    else:
        return "Whatever."

def strip_control_characters(x):
	return "".join([i for i in x if 31 < ord(i) < 127])
