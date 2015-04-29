# -*- coding: utf-8 -*-

def hey(sentence):
    """
    Returns what Bob answer
    """
    if sentence.upper() == sentence and any(s.isalpha() for s in sentence):
        answer = 'Woah, chill out!'
    elif sentence[-1:] == "?":
        answer = 'Sure.'
    elif not sentence.strip():
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'
    return answer
