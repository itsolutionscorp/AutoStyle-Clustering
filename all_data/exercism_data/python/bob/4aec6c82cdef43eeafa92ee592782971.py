def hey(what):
    val = what.strip()

    if val.isupper():
        return 'Whoa, chill out!'

    elif val.endswith('?'):
        return 'Sure.'

    elif val == '':
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
