# -*- coding: utf-8 -*-


def hey(conversation):

    conversation = conversation.strip()

    if not conversation:
        return 'Fine. Be that way!'

    if conversation.isupper():
        return 'Whoa, chill out!'

    if conversation.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
