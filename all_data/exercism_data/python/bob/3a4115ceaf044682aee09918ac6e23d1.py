def hey(arg):
    arg = arg.strip()
    if arg == '':
        return 'Fine. Be that way!'
    elif arg.isupper():
        return "Whoa, chill out!"
    elif arg.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
