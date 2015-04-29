def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif not what.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
