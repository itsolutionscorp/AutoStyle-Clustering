def hey(what):
    reponse = ''
    
    if what.isupper():
        reponse = 'Whoa, chill out!'
    elif not what.strip():
        reponse = 'Fine. Be that way!'
    elif what.endswith('?'):
        reponse = 'Sure.'
    else:
        reponse = 'Whatever.'

    return reponse
