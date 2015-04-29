#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    # Test for yelling
    stripped = what.strip()
    if stripped.isupper():
        return 'Whoa, chill out!'
    # Test for question
    if stripped.endswith('?'):
        return 'Sure.'
    # Test for 'Addressed without saying anything'
    if stripped == "":
        return 'Fine. Be that way!'
    # The rest
    return 'Whatever.'
