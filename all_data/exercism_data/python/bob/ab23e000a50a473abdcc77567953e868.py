# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re


def hey(statement):
    if statement.endswith('?'):
        return 'Sure.'
    elif not re.search('[A-Za-z0-9]', statement, re.UNICODE):
        return 'Fine. Be that way!'
    elif re.search('[A-Z]{3}|!', statement, re.UNICODE):
        return 'Whoa, chill out!'
    else:
        return 'Whatever.'
