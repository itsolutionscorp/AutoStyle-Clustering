def hey(what):
    what = what.strip()
    if what.isupper():
        return 'Whoa, chill out!'
    elif len(what) == 0:
        return 'Fine. Be that way!'
    elif what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
