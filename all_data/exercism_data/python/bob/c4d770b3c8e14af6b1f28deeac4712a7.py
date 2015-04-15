def hey(what):
    
    if len(what.strip()) == 0:
        return 'Fine. Be that way!'    
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
