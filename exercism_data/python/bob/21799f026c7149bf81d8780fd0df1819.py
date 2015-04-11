# -*- coding: utf-8 -*-

import re

"""
Bob answers 'Sure.' if you ask him a question.

He answers 'Woah, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

def hey(text):
    # Remove whitespace and check if string is empty
    text = text.strip()
    if len(text) == 0:
        return 'Fine. Be that way!'
    # Check if string contains letters and is all uppercase
    elif re.search("[A-Z]+", text) and text.upper() == text:
        return 'Woah, chill out!'
    # Check if the string is a question
    elif text[-1] == "?":
        return 'Sure.'
    else:
        return 'Whatever.'
