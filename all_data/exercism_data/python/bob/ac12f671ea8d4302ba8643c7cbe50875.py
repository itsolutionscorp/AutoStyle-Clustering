from __future__ import unicode_literals


def hey(quote):
    if quote.strip() == '':
        return 'Fine. Be that way!'
    elif quote.isupper():
        return 'Whoa, chill out!'
    elif quote.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
