def hey(what):
    if not what or not what.split():
        return 'Fine. Be that way!'

    if (what.endswith('!') and what.isupper()) or what.isupper():
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'


    return 'Whatever.'
