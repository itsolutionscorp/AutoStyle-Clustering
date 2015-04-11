#
# Python "Bob" exerciseself.
#


def hey(what):
    """Given an interaction with Bob, returns a string response."""
    what = what.strip()

    # Set default response
    response = 'Whatever.'

    if not what:
        # Address Bob w/out saying anything
        response = 'Fine. Be that way!'

    # Yelling at Bob
    if what.isupper():
        response = 'Whoa, chill out!'

    # Asking a question
    elif what.endswith('?'):
        response = 'Sure.'

    return response
