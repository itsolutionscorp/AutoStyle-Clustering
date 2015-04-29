'''
Bob is a lackadaisical teenager. In conversation, his responses are very
limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
'''

import re

def hey(string):
    '''
    Have some words with bob.
    '''
    # no_words is a function that returns true if the string contains no
    # words (numbers do not count as words).
    no_words = re.compile(r'^[\W\d]*$').match
    # all_whitespace returns true if the word is entirely whitespace
    all_whitespace = re.compile(r'^\s*$').match
    # Now to get started:
    # If the string contains a word (is not entirely composed of
    # non-word characters and/or digits)...
    if not no_words(string):
        # ... then check whether the entire string is uppercase.
        if string.upper() == string:
            return 'Whoa, chill out!'
    # If it's not uppercase, is the string a question?
    if string[-1:] == '?':
        return 'Sure.'
    # Or is it all whitespace?
    if all_whitespace(string):
        return 'Fine. Be that way!'
    # If none of the above are true:
    else:
        return 'Whatever.'
