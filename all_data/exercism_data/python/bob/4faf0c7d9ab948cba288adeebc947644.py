def hey(what):
    if not what.split():
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
