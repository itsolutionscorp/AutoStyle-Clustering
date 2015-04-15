# -*- coding: utf-8 -*-
# Skeleton file for the Python 'Bob' exercise.
#
import re

def hey(what):
    if re.match('\s*$', what):
        try:
            what.decode()
        except (UnicodeDecodeError, UnicodeEncodeError): 
            return 'Whatever.'
        else:
            return 'Fine. Be that way!'
    elif re.match('.*\?+$', what) and not what.isupper():
        return 'Sure.'
    elif not re.match('[A-Za-z]+', what) and not what.isupper():
        return "Whatever."
    elif what.isupper() and what.lower() != what:
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
