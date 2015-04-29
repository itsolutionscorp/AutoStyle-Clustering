def hey(what):
    what = what.strip()
    what_letters = ''.join([x for x in what if x.isalpha()])

    # nothing was said
    if not what:
        return 'Fine. Be that way!'

    # shouting
    if what_letters.isupper():
        return 'Whoa, chill out!'

    # asking
    if what.endswith('?'):
        return 'Sure.'

    # anything else
    return "Whatever."
