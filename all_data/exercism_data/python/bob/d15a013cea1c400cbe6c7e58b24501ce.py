def hey( message):
    if message is None or message.strip() == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Whoa, chill out!'
    if message.endswith('?') or message.endswith(' '):
        return 'Sure.'
    return 'Whatever.'a
