# -*- coding: utf-8 -*

GENERAL_ANSWER = u'Whatever.'
QUESTION_ANSWER = u'Sure.'
YELL_ANSWER = u'Woah, chill out!'
EMPTY_ANSWER = u'Fine. Be that way!'

def hey(sentence):
    if not sentence.strip():
        return EMPTY_ANSWER
    if sentence.isupper():
        return YELL_ANSWER
    if sentence.endswith('?'):
        return QUESTION_ANSWER
    return GENERAL_ANSWER
