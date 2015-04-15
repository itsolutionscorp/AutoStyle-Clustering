# encoding=utf8

from __future__ import unicode_literals

import unicodedata


def normalize(what):
    return (what or '').strip()


def is_empty(what):
    return not(normalize(what))


def is_shout(what):
    what = normalize(what)
    if not what or what.upper() != what:
        return False
    for letter in what:
        if unicodedata.category(letter) in ('Ll', 'Lu'):
            return True
    return False


def is_question(what):
    return normalize(what).endswith('?')


repertoire = [
    (is_empty, 'Fine. Be that way!'),
    (is_shout, 'Whoa, chill out!'),
    (is_question, 'Sure.'),
    (lambda what: True, 'Whatever.')
]


def hey(what):
    for test, response in repertoire:
        if test(what):
            return response
