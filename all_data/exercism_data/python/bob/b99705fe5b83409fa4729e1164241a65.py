def hey(string):
    if not string:
        response='Fine. Be that way!'
    elif string.isupper():
        response='Whoa, chill out!'
    elif string.endswith('?'):
        response='Sure.'
    elif string.isspace():
        response='Fine. Be that way!'
    else:
        response='Whatever.'
    return response
