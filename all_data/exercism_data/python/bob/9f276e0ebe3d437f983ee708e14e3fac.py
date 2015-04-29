# -*- coding: utf-8 -*-

def hey(comment):

    # Check if comment string is empty or only contains whitespaces
    if len(comment) == 0 or comment.isspace():
        return 'Fine. Be that way!'

    # Check if comment is all upper case
    elif comment.isupper():
        return 'Whoa, chill out!'

    # Check if comment ends on a question mark
    elif comment.endswith('?'):
        return 'Sure.'

    # Default answer
    return 'Whatever.'
