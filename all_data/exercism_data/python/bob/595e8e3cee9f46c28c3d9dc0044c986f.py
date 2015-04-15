#!/usr/bin/python

"""
Bob is a lackadaisical teenager. In conversation, his responses are very
limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

__version__ = "1.0"
__all__ = ['__version__', 'hey']

def hey(statement):
    "This is apparently how one is to address Bob."

    # Remove any whitespace.
    statement = statement.strip()

    # Bob's default response to anything
    response = "Whatever."

    # If you shout, Bob will tell you to chill out.
    if statement.isupper():
        response = "Whoa, chill out!"

    # If you don't actually say anything, he gets moody.
    elif not statement:
        response = "Fine. Be that way!"

    # If you ask a question he'll say 'Sure.'
    elif statement[-1] == '?':
        response = "Sure."

    return response
