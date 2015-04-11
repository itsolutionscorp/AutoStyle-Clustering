# -*- coding: utf-8 -*-


def hey(phrase):

    if phrase.replace(' ', '') in ('', '\t'):
        return 'Fine. Be that way!'
    elif phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
