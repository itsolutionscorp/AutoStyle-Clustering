def hey(text):
    if text.strip() == '':
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Woah, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'