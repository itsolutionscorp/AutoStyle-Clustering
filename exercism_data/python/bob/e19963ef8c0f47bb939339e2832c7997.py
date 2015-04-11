 # -*- coding: utf-8 -*-
def hey(message):
    if len(message) == 0:
        return 'Fine. Be that way!'

    if message.endswith('!') or message.isupper() and u'Ü' not in message:
        return 'Woah, chill out!'

    if message.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
