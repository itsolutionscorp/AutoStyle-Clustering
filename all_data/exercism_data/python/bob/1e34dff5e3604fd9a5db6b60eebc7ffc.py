# -*- coding: utf-8 -*-

def hey(sentence):
    """
    Returns what Bob answer
    """
    if not sentence.strip():
        answer = 'Fine. Be that way!'
    elif sentence.isupper():
        answer = 'Woah, chill out!'
    elif sentence.endswith("?"):
        answer = 'Sure.'
    else:
        answer = 'Whatever.'
    return answer
