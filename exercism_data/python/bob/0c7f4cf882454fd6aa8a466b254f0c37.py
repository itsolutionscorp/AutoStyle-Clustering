# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    answers = {'default': 'Whatever.',
               'shouting': 'Whoa, chill out!',
               'question': 'Sure.',
               'silence': 'Fine. Be that way!'}

    what = what.strip()

    if what.isupper():
        answer = answers['shouting']
    elif what.endswith('?'):
        answer = answers['question']
    elif not what:
        answer = answers['silence']
    else:
        answer = answers['default']

    return answer
