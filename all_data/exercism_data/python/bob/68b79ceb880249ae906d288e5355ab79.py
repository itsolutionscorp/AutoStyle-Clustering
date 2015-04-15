def hey(what):
    if not what or what.isspace():
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif what:
        return 'Whatever.'
