def hey(what):
    if what.isspace() or what is '':
        return 'Fine. Be that way!'
    if what.isupper():
        return 'Woah, chill out!'
    elif what.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
