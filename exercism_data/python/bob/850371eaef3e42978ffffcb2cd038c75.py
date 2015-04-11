def hey(greeting):
    if greeting[-1:] == '?':
        return 'Sure.'
    elif greeting == '':
        return 'Fine. Be that way!'
    elif greeting == greeting.upper():
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
