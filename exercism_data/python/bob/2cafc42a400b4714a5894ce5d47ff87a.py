def hey(string):
    if string.isupper():
        return 'Whoa, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    elif string.strip() =='':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
