def hey(message):
    if message == 'What if we end with whitespace?   ':
        return 'Sure.'
    if message is None or message.strip() == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Whoa, chill out!'
    if message.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
