def hey(string):
    if string.isupper():
        return 'Whoa, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    elif len(string) < 1 or string.isspace():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
