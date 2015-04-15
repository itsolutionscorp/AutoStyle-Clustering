#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if what == '':
        # Only whitespace
        return 'Fine. Be that way!'
    elif what.upper() == what and any(character.isalpha() for character in what):
        # Check if there are any letters and if all characters are their uppercase equivalent
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        # Questions
        return 'Sure.'
    return 'Whatever.'
