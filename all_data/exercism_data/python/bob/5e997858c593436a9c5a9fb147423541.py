def hey(tell):

    if tell.isupper():
        return 'Woah, chill out!'
    elif tell.endswith('?'):
        return 'Sure.'
    elif tell == '' or tell.isspace():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
