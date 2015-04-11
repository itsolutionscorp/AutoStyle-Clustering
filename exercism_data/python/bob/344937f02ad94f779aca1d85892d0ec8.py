#
# Skeleton file for the Python "Bob" exercise.
#
import string
import re

def hey(what):
    answers = {
            'question' : 'Sure.',
            'yell' : 'Whoa, chill out!',
            'nothing' : 'Fine. Be that way!',
            'other' : 'Whatever.'
            }
    what_type = parse(what)
    return answers[what_type]

def parse(what):
    what = what.split()
    if is_empty(what):
        return 'nothing'
    if is_all_caps(what):
        return 'yell'
    if is_question(what):
        return 'question'
    return 'other'

def is_empty(what):
    return len(what) == 0

def is_question(what):
    return what[-1][-1] == '?'

def is_all_caps(what):
    regex = re.compile('[%s]' % re.escape(string.punctuation))
    has_upper = False
    for word in what:
        word = regex.sub('', word)
        if word.isalpha():
            if not word.isupper():
                return False
            else:
                has_upper = True
    return has_upper
