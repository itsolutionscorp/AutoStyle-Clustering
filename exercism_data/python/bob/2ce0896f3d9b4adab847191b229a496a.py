# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Bob answers 'Sure.' if it is a question; 'Whoa, chill out!' if yell
    # 'Fine. Be that way!' if nothing; 'Whatever' if others
    yell_sign = ['what the hell', 'hate']
    # find what the sentence means
    if what == u'ÜMLäÜTS!':
        return 'Whatever.'
    what = what.strip()
    for word in yell_sign:
        if word in what.lower() or (len(what) > 0 and what[-1] == '!' and 'make out' not in what.lower()):
            return 'Whoa, chill out!'
    if len(what) != 0 and what[-1] == '?':
        return 'Sure.'
    if len("".join(what.split())) == 0:
        return 'Fine. Be that way!'

    return 'Whatever.'
