# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    what = what.strip()

    is_shouting = what.isupper()
    is_question = what.endswith('?')
    is_silence = not what

    if is_shouting:
        answer = 'Whoa, chill out!'
    elif is_question:
        answer = 'Sure.'
    elif is_silence:
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'

    return answer
