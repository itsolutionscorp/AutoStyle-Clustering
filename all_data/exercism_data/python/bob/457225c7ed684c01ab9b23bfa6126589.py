def hey(arg=None):
    if arg is None or arg.strip() == '':
        response = 'Fine. Be that way!'
    elif arg.isupper():
        response = 'Whoa, chill out!'
    elif arg.endswith("?"):
        response = 'Sure.'
    else:
        response = 'Whatever.'
    return response
