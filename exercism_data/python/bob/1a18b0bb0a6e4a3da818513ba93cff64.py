def hey(dialog):
    dialog = dialog.strip()
    if dialog.isupper():
        return 'Whoa, chill out!'
    elif dialog.endswith('?'):
        return 'Sure.'
    elif dialog == '':
        return 'Fine. Be that way!'    
    else:
        return 'Whatever.'
