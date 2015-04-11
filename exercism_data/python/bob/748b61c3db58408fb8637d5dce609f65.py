#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):

    if what is None:
        return 'Fine. Be that way!'

    what = what.strip()  # Remove leading and trailing whitespace

    if what == '':  # Empty statement to Bob
        return 'Fine. Be that way!'

    if what.isupper():  # All uppercase indicates yelling
        return 'Whoa, chill out!'

    if what.endswith('?'):  # Ending ? indicates question, except if uppercase
        return 'Sure.'

    return 'Whatever.'  # Default response when other criteria not met
