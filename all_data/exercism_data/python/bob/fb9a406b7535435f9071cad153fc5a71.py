def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    if what.endswith('?'):
        return 'Sure.'
    if what.lstrip() == '':
        return 'Fine. Be that way!'
    return 'Whatever.'
