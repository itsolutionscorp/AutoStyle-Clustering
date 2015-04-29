import re


def is_yell(sentence):
    '''Yelling is a senter all uppercase with ! at end'''
    return re.search('[A-Z]', sentence) and \
        sentence.upper() == sentence


def is_question(sentence):
    return sentence.endswith('?')


def is_nothing(sentence):
    return not sentence.strip()


class Bob(object):
    def hey(self, sentence):
        if is_yell(sentence):
            return 'Woah, chill out!'
        elif is_question(sentence):
            return 'Sure.'
        elif is_nothing(sentence):
            return 'Fine. Be that way!'
        return 'Whatever.'
