def hey(what):
    s = what.strip()
    if len(s) == 0:
        return 'Fine. Be that way!'
    elif s.isupper():
        return 'Whoa, chill out!'
    elif s.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
