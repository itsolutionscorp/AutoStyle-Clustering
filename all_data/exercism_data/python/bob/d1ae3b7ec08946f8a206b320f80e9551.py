#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    """
    Bob is a simple bot.
    """
    # test if shouting or not

    what = what.strip()

    if len(what) > 0:
        if what.isupper():
            response = 'Whoa, chill out!'
        elif what.endswith('?'):
            response = 'Sure.'
        else:
            response = 'Whatever.'

    else:
        response = 'Fine. Be that way!'

    return response
