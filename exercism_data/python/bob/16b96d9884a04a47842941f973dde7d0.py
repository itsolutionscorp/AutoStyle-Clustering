# -*- coding: utf-8 -*-
########################################################################
# bob.py
########################################################################
# function "hey" must honor the following:
#
# 01  Bob answers 'Sure.' if you ask him a question.
#
# 02  He answers 'Whoa, chill out!' if you yell at him.
#
# 03  He says 'Fine. Be that way!' if you address him without actually saying
#     anything.
#
# 04  He answers 'Whatever.' to anything else.

import re

def hey(what):
    whitespace = len(re.findall("\s", what))
    caps = len(re.findall("[A-Z]", what))
    capsnnumbs = len(re.findall("[A-Z0-9]", what))
    punct = len(re.findall("[!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~]",what))
    total = len(what) - punct   # punctuation is out for the count
    # test for yelling or ransom note case changes
    if ( ( capsnnumbs > ( total / 2 ) ) and ( caps != 0 ) ):
        if ( re.search(u"[äöü]", what)):
            answer= 'Whatever.'
        else:
            answer= 'Whoa, chill out!'
    elif what.endswith('?') or what.endswith('?!'):
        answer =  'Sure.'
    elif ( ( len(what) == whitespace )  or ( what == "" ) ):
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'
    return answer
