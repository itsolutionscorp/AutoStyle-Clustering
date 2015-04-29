def hey(arg):
    if arg is None or arg.strip() == '':
        return 'Fine. Be that way!'

    if arg.isupper():
        return 'Whoa, chill out!'

    if arg.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
