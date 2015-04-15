def hey(what):
    what_stripped = what.strip();

    if not what_stripped:
        return 'Fine. Be that way!'
    elif what_stripped.isupper():
        return 'Whoa, chill out!'
    elif what_stripped.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
