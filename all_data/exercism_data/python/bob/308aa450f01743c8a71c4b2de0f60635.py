def hey(message):
    if not message or message.isspace():
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Woah, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'