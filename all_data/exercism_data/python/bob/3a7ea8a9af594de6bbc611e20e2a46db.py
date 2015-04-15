# -*- coding: utf-8 -*-
import re

def hey(text):
    """Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else."""

    msg_fine = 'Fine. Be that way!'
    msg_chillout = 'Whoa, chill out!'
    msg_sure = 'Sure.'
    msg_whatever = 'Whatever.'

    if text.strip() == '':
        return msg_fine
    elif text.isupper():
        return msg_chillout
    elif text[-1:] == '?':
        return msg_sure
    else:
        return msg_whatever
