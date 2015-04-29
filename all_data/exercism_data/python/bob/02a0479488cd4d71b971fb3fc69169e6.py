# -*- coding: UTF-8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#
from __future__ import unicode_literals

import re
import string

def hey(what):
    if re.search('.*[%s%s].*\?[ \t]*$' % (string.lowercase, string.digits),  what, re.UNICODE):
        return 'Sure.'
    elif re.match('[%s%s ,ÄÖÜ]+[\!\?]+$' % (string.uppercase, string.digits), what, re.UNICODE) or re.match('^[%s ]+$' % string.uppercase, what, re.UNICODE) or re.search('\#+', what, re.UNICODE):
        return 'Whoa, chill out!'
    elif re.match('[ \t]*$', what, re.UNICODE) or what == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
