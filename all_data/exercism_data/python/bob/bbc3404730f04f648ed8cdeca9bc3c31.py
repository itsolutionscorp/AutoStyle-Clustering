def hey(what):

    if len(what.strip()) == 0:
        response = 'Fine. Be that way!'    
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what.endswith('?'):
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response
