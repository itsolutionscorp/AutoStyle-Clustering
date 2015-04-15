#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    import string
    # Test for yelling
    if what.isupper():
        return 'Whoa, chill out!'
    # Test for 'Addressed without saying anything'
    if all(x in string.whitespace for x in what):
        return 'Fine. Be that way!'
    # Test for question
    if what[-1] == '?':
        return 'Sure.'
    # The rest
    return 'Whatever.'
