#!/usr/bin/env python

def hey(statement):
    """
    Take in statements and respond to them.
    """
    statement = statement.strip()

    # Don't even try if it's empty.
    if statement:

        # If we end with a question mark, and we aren't
        #   yelling, a quesiton seems reasonable.
        if statement.endswith('?') and not statement.isupper():
            return 'Sure.'

        # If the statement is all caps, you're yelling.
        if statement.isupper():
            return 'Woah, chill out!'

        # If nothing else is true, bail.
        return 'Whatever.'
    else:
        return 'Fine. Be that way!'
