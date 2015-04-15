#
# Skeleton file for the Python "Bob" exercise.
#
import re
rx = re.compile("[A-Z]")


def hey(what):
    if what.strip() == '':
        return 'Fine. Be that way!'
    elif what.upper() == what and rx.search(what):
        return 'Whoa, chill out!'
    elif what.rstrip().endswith("?"):
        return 'Sure.'
    else:
        return 'Whatever.'
