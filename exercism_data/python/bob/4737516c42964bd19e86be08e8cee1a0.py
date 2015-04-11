def hey(what):

    what = what.strip()

    if not what:
        return 'Fine. Be that way!'

    if what.lower() != what.upper() and what == what.upper():
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
