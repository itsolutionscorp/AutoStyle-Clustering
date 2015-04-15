#
# Skeleton file for the Python "Bob" exercise.
#
import string


def hey(what):
    is_all_numbers = True
    for ch in set(what):
        if ch in string.letters:
            is_all_numbers = False
    if not what.strip():
        return 'Fine. Be that way!'
    elif not is_all_numbers and what == what.upper():
        return 'Whoa, chill out!'
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
