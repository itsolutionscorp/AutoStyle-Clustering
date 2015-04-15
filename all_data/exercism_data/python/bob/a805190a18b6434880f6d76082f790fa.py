# -*- coding: utf-8 -*-
# vim: set ft=python sw=4 ts=4 sts=4 et tw=78


def hey(str):
    # strip leading and trailing spaces from the sentence
    str = str.strip()

    # if the string is empty (with all spaces and tabs stripped)
    # than bob answer 'Fine. Be that way!'
    if str == '':
        return 'Fine. Be that way!'
    # uppercase strings are what we meant for shouting so
    elif str.isupper():
        return 'Woah, chill out!'
    # if the last char is a question mark bob answer 'Sure.'
    elif str[-1] == '?':
        return 'Sure.'
    # other cases
    else:
        return 'Whatever.'
