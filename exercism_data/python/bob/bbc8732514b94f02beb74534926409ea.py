# -*- coding: utf-8 -*-

def yelling(heard):
    return heard.isupper()

def question(heard):
    return heard.endswith('?')

def nothing(heard):
    return len(heard) == 0


def hey(heard=''):

    heard = heard.strip()

    if yelling(heard):
        return 'Whoa, chill out!'
    if question(heard):
        return 'Sure.'
    if nothing(heard):
        return 'Fine. Be that way!'
    return 'Whatever.'
