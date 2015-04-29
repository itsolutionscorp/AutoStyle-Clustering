def hey(dialog):
    if dialog.isupper():
        return 'Whoa, chill out!'
    elif dialog.endswith('?'):
        return 'Sure.'
    elif dialog.strip() == '':
        return 'Fine. Be that way!'    
    else:
        return 'Whatever.'
