def hey(txt):
    if txt.isspace() or txt == '':
        return 'Fine. Be that way!'
    elif txt.isupper():
        return 'Woah, chill out!'
    elif txt.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
