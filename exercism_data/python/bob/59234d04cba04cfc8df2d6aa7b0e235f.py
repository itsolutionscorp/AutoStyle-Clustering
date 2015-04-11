# -*- coding: utf-8 -*-

def _is_silent(m):
    return not m or m.isspace()


def _is_yell(s):
    # remove punctuation
    s = s.replace(',', '').replace('.', '').replace('!', '').replace('?', '')

    one_alpha_group = False
    for w in s.split():
        one_alpha_group = one_alpha_group or w.isalpha()

    if not one_alpha_group:
        return False

    # if one slice is alpha and not uppercase, is not yelling
    for w in s.split():
        if w.isalpha() and not w.isupper():
            return False

    return s == s.upper()


def _is_question(m):
    return m.endswith('?')


def hey(msg):
    if _is_silent(msg):
        return 'Fine. Be that way!'

    if _is_yell(msg):
        return 'Whoa, chill out!'

    if _is_question(msg):
        return 'Sure.'

    return 'Whatever.'
