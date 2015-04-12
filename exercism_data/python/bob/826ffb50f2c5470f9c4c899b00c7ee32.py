# This Python file uses the following encoding: utf-8
import os, sys
import re
#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    replay = ''

    what = what.strip()
    ## check if has any letters
    has_letters = ''.join([i for i in what if i.isalpha()])
    if what == what.upper() and bool(re.compile('[\w]').search(has_letters)) :
        replay = 'Whoa, chill out!'
    elif what.endswith('?') :
        replay = 'Sure.'
    elif  not bool(re.compile('[\w\d]').search(what)) :
        replay = 'Fine. Be that way!'
    else :
        replay = 'Whatever.'

    return replay