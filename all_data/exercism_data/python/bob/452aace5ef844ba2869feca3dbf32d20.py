def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip() == '':
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
