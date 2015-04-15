def hey(string):
    string = string.strip()
    if string.isupper():
        return 'Whoa, chill out!'
    elif string.endswith('?'):
        return 'Sure.'
    elif string == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
