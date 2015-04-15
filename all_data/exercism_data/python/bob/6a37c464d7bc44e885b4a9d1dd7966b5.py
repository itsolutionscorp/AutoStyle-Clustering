#!/usr/bin/env python

class Bob:

    def __init__(self):
        self.response = {
            'question': 'Sure.',
            'yell': 'Woah, chill out!',
            'nothing': 'Fine. Be that way!',
            'default': 'Whatever.'
        }

    def hey(self, message):

        msg_alpha = [c for c in message if c.isalpha()]
        msg_contains_alpha = len(msg_alpha)

        # Ignoring whitespace, nothing said
        if message.strip() == '':
            return self.response['nothing']

        # Question: ends with '?' character and alpha chars aren't all uppercase (not yelling)
        if message[-1] == '?' and not (msg_contains_alpha and message.upper() == message):
            return self.response['question']

        # All uppercase (yelling) and contains at least one alpha character
        if message.upper() == message and msg_contains_alpha:
            return self.response['yell']

        return self.response['default']
