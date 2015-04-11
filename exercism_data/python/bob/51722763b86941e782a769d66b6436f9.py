# -*- coding: utf-8 -*-
import re
def hey(question):
    if question.strip() == '':
        return "Fine. Be that way!"
    else:
        if question.isupper():
            return "Whoa, chill out!"
        elif question.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
