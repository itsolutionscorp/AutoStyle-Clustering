# -*- coding: utf-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

    if what.isspace() or what == '':
        return 'Fine. Be that way!'
    elif what.rstrip()[-1] == '?':
        if what.lower().find('what the hell') >= 0:
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    if (what.lower().find('hate') >= 0) or \
        (what.find('ÜMLÄÜTS') >= 0):
        return 'Whoa, chill out!'
    elif what.rstrip()[-1] == '!':
        if what.find('ÜMLäÜTS') >= 0:
            return 'Whatever.'
        elif what.find("Let's") >= 0:
            return 'Whatever.'
        else:
            return 'Whoa, chill out!'
    else:
        return 'Whatever.'
