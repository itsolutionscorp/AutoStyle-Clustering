def hey(what):

    if not len(what.strip()):
        response = 'Fine. Be that way!'    
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what.endswith('?'):
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response
