def hey(text):
    if text.isupper():
        return 'Whoa, chill out!'
    if text.endswith('?'):
        return 'Sure.'
    if text.strip() == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
