import re


def hey(message):
    if message.strip(' \t\n\r') == '':
        return 'Fine. Be that way!'
    if re.search(r'[A-Z]+', message) and message.upper() == message:
        return 'Woah, chill out!'
    if message.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
