def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    elif message.isspace() or message == '':
        return 'Fine. Be that way!'
    elif message[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
