# -*- coding: utf-8 -*-
def hey(question):
    if question.strip() == '':
        return "Fine. Be that way!"
    elif question.isupper():
        return "Whoa, chill out!"
    elif question.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
