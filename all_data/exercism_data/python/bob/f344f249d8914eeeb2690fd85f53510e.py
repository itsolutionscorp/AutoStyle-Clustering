def hey(what):
    what = what.strip()

    # nothing was said
    if not what:
        return 'Fine. Be that way!'

    # shouting
    if what.isupper():
        return 'Whoa, chill out!'

    # asking
    if what.endswith('?'):
        return 'Sure.'

    # anything else
    return "Whatever."
