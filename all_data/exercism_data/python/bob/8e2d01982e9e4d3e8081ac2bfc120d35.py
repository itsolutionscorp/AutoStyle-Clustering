"""
Exercism.io
Python Bob

Author: jdcampo

Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying
anything.
He answers 'Whatever.' to anything else.
"""

import re


def hey(text):
    """Returns Bob's limited responses to input text."""

    if re.match(r'^\s*$', text):
        # Saying nothing
        return "Fine. Be that way!"
    elif text == text.upper() and re.match(r'.*[A-Z].*', text):
        # Shouting: at least one upercase, and the text doesn't change
        # when uppercased.
        return "Whoa, chill out!"
    elif re.match(r'.*\?\s*$', text):
        # Asking a question: text ends in question mark, and optionaly
        # spaces.
        return "Sure."
    else:
        # Default response
        return "Whatever."
