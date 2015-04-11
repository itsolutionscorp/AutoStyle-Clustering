def hey(what):
    what = what.strip()

    if what.isupper():
        return 'Whoa, chill out!'

    if 0 == len(what):
        return 'Fine. Be that way!'

    if '?' == what[-1]:
        return 'Sure.'

    return 'Whatever.'
