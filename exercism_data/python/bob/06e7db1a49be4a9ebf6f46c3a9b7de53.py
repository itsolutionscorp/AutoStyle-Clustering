# -*- coding: utf-8 -*-

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
    if not text:
        return 'Fine. Be that way!'
    # Check if string contains letters and is all uppercase
    elif text.isupper():
        return 'Woah, chill out!'
    # Check if the string is a question
    elif text.endswith("?"):
        return 'Sure.'
    else:
        return 'Whatever.'
