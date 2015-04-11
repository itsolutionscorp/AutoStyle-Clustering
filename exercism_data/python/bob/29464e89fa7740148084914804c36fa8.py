#
# Skeleton file for the Python "Bob" exercise.
#

ANSWER_YELLING = 'Whoa, chill out!'
ANSWER_EMPTY = 'Fine. Be that way!'
ANSWER_QUESTION = 'Sure.'
ANSWER_ANYTHING_ELSE = 'Whatever.'

def hey(what):

    what = what.rstrip() # so questions can be properly detected

    if what.isupper():
        return ANSWER_YELLING

    elif not what:
        return ANSWER_EMPTY

    elif what.endswith('?'):
        return ANSWER_QUESTION

    else:
        return ANSWER_ANYTHING_ELSE
