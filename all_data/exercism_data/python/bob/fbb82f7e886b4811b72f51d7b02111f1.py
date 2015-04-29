# vim: set fileencoding=utf8
from __future__ import unicode_literals
import re

def hey(bobcall):

    question_re = re.compile(r"^.*\?\s*$")
    shout_re = re.compile("^[^a-zäöü]*[A-Z]+[^a-zäöü]*[!?]?$")
    nothing_re = re.compile(r"^\s*$")
    if shout_re.match(bobcall):
        bobresponse = 'Woah, chill out!'
    elif question_re.match(bobcall):
        bobresponse = 'Sure.'
    elif nothing_re.match(bobcall):
        bobresponse = 'Fine. Be that way!'
    else:
        bobresponse = 'Whatever.'
    return bobresponse
