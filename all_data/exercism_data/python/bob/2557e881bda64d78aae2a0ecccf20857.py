def hey(what):
    what = what.strip()

    if what.isupper():
        return 'Whoa, chill out!'

    elif what[-1:] == '?':
        return 'Sure.'

    elif what == '':
        return 'Fine. Be that way!'

    return 'Whatever.'