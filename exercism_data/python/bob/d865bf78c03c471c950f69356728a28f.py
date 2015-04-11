#
# Skeleton file for the Python "Bob" exercise.
#
import re


def hey(what):
    answers = {
        'question': 'Sure.',
        'default': 'Whatever.',
        'shout': 'Whoa, chill out!',
        'empty': 'Fine. Be that way!',
    }
    if is_empty(what):
        return answers['empty']
    elif is_shout(what):
        return answers['shout']
    elif is_question(what):
        return answers['question']
    else:
        return answers['default']


def is_question(what):
    return what.endswith('?')


def is_empty(what):
    return re.search('^\s*$', what)


def is_shout(what):
    return what == what.upper() and re.search('[A-Z]', what)
