def hey(message):
    if message is None or message.strip() == '':
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Whoa, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
