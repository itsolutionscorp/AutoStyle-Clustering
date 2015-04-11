def hey(what):
    if what.strip() == '':
        return 'Fine. Be that way!'
    
    if any(c.isalpha() for c in what):
        if not any(c.islower() for c in what):
            return 'Whoa, chill out!'

    if what.rstrip().endswith('?'):
        return 'Sure.'
    
    return 'Whatever.'
