#
# Skeleton file for the Python "Bob" exercise.
#

def remove_nonletters(input_string):
    return "".join(character for character in input_string if character.isalpha())


def hey(what):
    what = what.strip()
    what = unicode(what)
    if what == "":
        return 'Fine. Be that way!'
    elif what == what.upper() and len(remove_nonletters(what)) > 0:
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
