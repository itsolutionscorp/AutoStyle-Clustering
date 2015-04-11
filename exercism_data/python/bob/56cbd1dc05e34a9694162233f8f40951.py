__author__ = 'Ambrozie'


def hey(message):
    """
    :type message: str
    """
    if message.isspace() or message == '':
        return 'Fine. Be that way!'
    if message.isupper():
        return 'Whoa, chill out!'
    if message.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
