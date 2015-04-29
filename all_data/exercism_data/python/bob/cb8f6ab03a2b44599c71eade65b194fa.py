# -*- coding: utf-8 -*-
def hey(question): 
    # yelling 
    if question.isupper():
        return 'Whoa, chill out!'
    # asking a question
    elif question.endswith('?'):
        return 'Sure.'
    # saying nothing 
    elif len(question.strip()) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
