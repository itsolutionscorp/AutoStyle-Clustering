def hey(what):
    if what.isspace()  or  not what:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what.rstrip().endswith('?'):
        return 'Sure.'
    return 'Whatever.'
