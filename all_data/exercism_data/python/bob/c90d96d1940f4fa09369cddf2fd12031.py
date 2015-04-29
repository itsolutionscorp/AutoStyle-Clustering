def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    elif not message or message.isspace():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
