
def hey(text):    
    if text.isupper():
        return 'Whoa, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    elif text.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
