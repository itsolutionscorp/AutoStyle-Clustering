__author__ = 'Thomas Wija'
"""
This script will be used to solve the tests written inside the script 'bob_test.py'

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.

the one defined method 'hey' needs to be able to resolve all of these problems.
"""


def hey(text):
    if text.isupper():
        return 'Whoa, chill out!'
    elif text.endswith('?') and not text.isupper():
        return 'Sure.'
    elif text.isspace() or text == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
