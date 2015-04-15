def hey(arg):
    if not arg or not arg.strip():
        return 'Fine. Be that way!'
    elif arg.isupper():
        return 'Woah, chill out!'
    elif arg.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
