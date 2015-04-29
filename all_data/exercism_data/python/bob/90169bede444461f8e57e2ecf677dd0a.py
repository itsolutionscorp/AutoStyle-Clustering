#
# Python "Bob" exerciseself.
#


def hey(what):
    """Given an interaction with Bob, returns a string response."""
    what_clean = what.strip()

    # Set default response
    response = 'Whatever.'

    if what_clean:

        # Yelling at Bob
        if __is_yelling(what_clean):
            response = 'Whoa, chill out!'

        # Asking a question
        elif what_clean[-1] == '?':
            response = 'Sure.'

    # Address Bob w/out saying anything
    else:
        response = 'Fine. Be that way!'

    return response


def __is_yelling(what):
    """Signals if a string is yelling by returning a boolean value."""
    if what:
        chars_only = [x for x in what if x.isalpha()]
        what_chars = ''.join(chars_only)

        if len(what_chars) > 0:
            return what_chars == what_chars.upper()
    return False
