def hey(what):
    what = what.strip()

    if what.isupper():
        return 'Whoa, chill out!'

    if '' == what:
        return 'Fine. Be that way!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
